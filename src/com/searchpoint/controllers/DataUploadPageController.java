package com.searchpoint.controllers;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.searchpoint.entities.Category;
import com.searchpoint.entities.Company;
import com.searchpoint.entities.Currency;
import com.searchpoint.entities.Data;
import com.searchpoint.entities.Item;
import com.searchpoint.entities.User;
import com.searchpoint.security.UnauthorizedException;
import com.searchpoint.security.UserAuthenticationController;
import com.searchpoint.services.CategoryService;
import com.searchpoint.services.CompanyService;
import com.searchpoint.services.DataService;
import com.searchpoint.services.ItemService;
import com.searchpoint.xml.XmlTestItem;
import com.searchpoint.xml.XmlParser;

/**
 * MVC controller responsible for company data upload page actions.
 * 
 * @author guligo
 */
@Controller
public class DataUploadPageController {
	
	private static final Logger logger = Logger.getLogger(DataUploadPageController.class.getName());
	
	public final static String DATA_UPLOAD_PAGE = "data-upload.html";
	public final static String DATA_UPLOAD_SUBMIT_ACTION = "submit-data-upload.html";
	
	private final String DATA_UPLOAD_VIEW = "data-upload";
	
	private final String DATA_OBJECT = "data";
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DataService dataService;
	@Autowired
	private XmlParser xmlParser;	
	
	@RequestMapping(value = DATA_UPLOAD_PAGE, method = RequestMethod.GET)
	public String showDataUploadPage(HttpSession session, @RequestParam Long id, ModelMap model) throws Exception {
		/* authorization check */
		User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
		Company company = companyService.getCompanyById(id);
		if (!user.getId().equals(company.getUser().getId())) {
			throw new UnauthorizedException("User makes unauthorized access to data upload form");
		}
		/* business logic */
		model.put(DATA_OBJECT, dataService.getDataByCompanyId(id));
		return DATA_UPLOAD_VIEW;
	}

	private final int MIN_URL_LENGTH = 10;
	private final int MAX_URL_LENGTH = 255;
	
	private void validateCompanyData(Data data, BindingResult result) {
		if (data.getUseUrl() == false) {
			if (data.getFile() == null
				|| data.getFile().getSize() == 0) {
				result.rejectValue("file", null, "Please upload file");
			} else {
				try {
					xmlParser.parseTestXml(data.getFile().getInputStream());
				} catch (Exception e) {
					result.rejectValue("file", null, "Make sure XML file is correct");
				}
			}
		} else {
			if (data.getUrl() == null || "".equals(data.getUrl().trim())) {
				result.rejectValue("url", null, "Field is mandatory");
			} else if (data.getUrl().length() < MIN_URL_LENGTH) {
				result.rejectValue("url", null, String.format("Must be at least %s characters", MIN_URL_LENGTH));
			} else if (data.getUrl().length() > MAX_URL_LENGTH) {
				result.rejectValue("url", null, String.format("Must be less than %s characters", MAX_URL_LENGTH));
			} else if (data.getUrl().startsWith("http://") == false
				&& data.getUrl().startsWith("https://") == false) {
				result.rejectValue("url", null, "Field must be a valid URL");
			} else {				
				try {
					URL url = new URL(data.getUrl());
					URLConnection connection = url.openConnection();
					xmlParser.parseTestXml(connection.getInputStream());
				} catch (Exception e) {
					result.rejectValue("url", null, "Make sure XML is correct");					
				}
			}			
		}
	}
	
	@RequestMapping(value = DATA_UPLOAD_SUBMIT_ACTION, method = RequestMethod.POST)
	public String submitDataUploadForm(HttpSession session, @RequestParam Long id, @ModelAttribute Data data, BindingResult result) throws Exception {
		/* authorization check */
		User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
		Company company = companyService.getCompanyById(id);
		if (!user.getId().equals(company.getUser().getId())) {
			throw new UnauthorizedException("User tries to submit data for company which does not belong to him");
		}
		/* business logic */
		validateCompanyData(data, result);
		if (!result.hasErrors()) {
			List<XmlTestItem> xmlTestItems = null;
			if (data.getUseUrl() == false) {
				try {
					xmlTestItems = xmlParser.parseTestXml(data.getFile().getInputStream());
					data.setXmlFileName(data.getFile().getOriginalFilename());
					data.setXml(data.getFile().getBytes());					
				} catch (Exception e) {
					logger.log(Level.WARNING, "Error on parsing data file", e);
				}
			} else {
				try {
					URL url = new URL(data.getUrl());
					URLConnection connection = url.openConnection();
					xmlTestItems = xmlParser.parseTestXml(connection.getInputStream());
				} catch (Exception e) {
					logger.log(Level.WARNING, "Error on parsing data URL content", e);
				}
			}
			
			company = companyService.getCompanyById(id);
			if (xmlTestItems != null) {
				/* delete all previous items */
				itemService.deleteItemsByCompanyId(id);
				
				Category category;				
				/* this will give noticeable speed improvement */
				Map<Long, Category> cache = new HashMap<Long, Category>();				
				for (XmlTestItem xmlTestItem : xmlTestItems) { 
					Item item = new Item();
					item.setName(xmlTestItem.getName());
					item.setPrice(xmlTestItem.getPrice());
					item.setCurrency(Currency.LVL);
					item.setImageUrl(xmlTestItem.getImage());
					item.setItemUrl(xmlTestItem.getLink());
					item.setInStock(xmlTestItem.getIn_stock());
					
					/* TODO: add some logic here */
					Long categoryId = (long) 148;
					category = cache.get(categoryId);
					if (category == null) {
						category = categoryService.getCategoryById(categoryId);
						cache.put(categoryId, category);
					}
					item.setCategory(category);
					item.setCompany(company);
					itemService.saveItem(item);
				}
				logger.log(Level.INFO, "Data for " + xmlTestItems.size() + " new items");
			}
			data.setCompany(company);
			dataService.saveData(data);								
			return "redirect: " + UserCompaniesPageController.USER_COMPANIES_PAGE;
		}
		if (data.getId() != null) {
			Data tmp = dataService.getDataByCompanyId(id);
			data.setXmlFileName(tmp.getXmlFileName());
			data.setXml(tmp.getXml());			
		}
		return DATA_UPLOAD_VIEW;
	}

}
