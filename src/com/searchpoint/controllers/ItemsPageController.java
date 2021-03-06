package com.searchpoint.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.searchpoint.beans.NavigationBean;
import com.searchpoint.beans.NavigationSequenceBean;
import com.searchpoint.beans.SearchBean;
import com.searchpoint.entities.Category;
import com.searchpoint.entities.Company;
import com.searchpoint.entities.Item;
import com.searchpoint.entities.User;
import com.searchpoint.security.UnauthorizedException;
import com.searchpoint.security.UserAuthenticationController;
import com.searchpoint.services.CategoryService;
import com.searchpoint.services.CompanyService;
import com.searchpoint.services.ItemService;
import com.searchpoint.services.SearchStatisticsService;

/**
 * MVC controller responsible for displaying items.
 * 
 * @author guligo
 */
@Controller
public class ItemsPageController {

	public final static String ITEM_LIST_PAGE = "items.html";
	public final static String ITEM_LIST_FOR_COMPANY_PAGE = "company-items.html";
	public final static String ITEM_SEARCH_RESULT_PAGE = "search.html";
	
	private final String ITEM_LIST_VIEW = "items";
	
	private final String ITEM_LIST_OBJECT = "items";
	private final String NAVIGATION_SEQUENCE_OBJECT = "navigationSequenceBean";
	private final String REQUEST_URI_OBJECT = "requestUri";

	@Autowired
	private ItemService itemService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SearchStatisticsService searchService;
	@Autowired
	private SearchBean searchBean;
	@Autowired
	private NavigationSequenceBean navigationSequenceBean;

	@RequestMapping(value = ITEM_LIST_PAGE, method = RequestMethod.GET)
	public String showItemsList(@RequestParam Long id, ModelMap model) throws Exception {
		/* create item list */
		List<Item> items = itemService.getItems(id);	
		model.put(ITEM_LIST_OBJECT, items);
		
		/* create navigation sequence */
		navigationSequenceBean.getNavigationSequence().clear();
		Category category = categoryService.getCategoryById(id);
		if (category != null) {			
			navigationSequenceBean.getNavigationSequence().add(0, new NavigationBean(category.getName(), "javascript:window.location.reload()"));
			while (category.getParent() != null) {				
				category = category.getParent();
				navigationSequenceBean.getNavigationSequence().add(0, new NavigationBean(category.getName(), ITEM_LIST_PAGE + "?id=" + category.getId()));
			}
			navigationSequenceBean.getNavigationSequence().add(0, new NavigationBean("Catalogue", CategoriesPageController.CATEGORY_LIST_PAGE));
			model.put(NAVIGATION_SEQUENCE_OBJECT, navigationSequenceBean);
			model.put(REQUEST_URI_OBJECT, ITEM_LIST_PAGE);
		}
		return ITEM_LIST_VIEW;
	}
	
	@RequestMapping(value = ITEM_LIST_FOR_COMPANY_PAGE, method = RequestMethod.GET)
	public String showCompanyItemsList(HttpSession session, @RequestParam Long id, ModelMap model) throws Exception {
		/* authorization check */
		User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
		Company company = companyService.getCompanyById(id);
		if (!user.getId().equals(company.getUser().getId())) {
			throw new UnauthorizedException("User is not allowed to see complete list of items for this company");
		}
		/* business logic */
		navigationSequenceBean.getNavigationSequence().clear();
		navigationSequenceBean.getNavigationSequence().add(0, new NavigationBean(companyService.getCompanyById(id).getTitle(), "javascript:window.location.reload()"));
		navigationSequenceBean.getNavigationSequence().add(0, new NavigationBean("My Shops", UserCompaniesPageController.USER_COMPANIES_PAGE));
		
		model.put(ITEM_LIST_OBJECT, itemService.getItemsByCompany(id));
		model.put(NAVIGATION_SEQUENCE_OBJECT, navigationSequenceBean);
		model.put(REQUEST_URI_OBJECT, ITEM_LIST_FOR_COMPANY_PAGE);
		return ITEM_LIST_VIEW;
	}
	
	private final int MIN_LENGTH = 2;
	private final int MAX_LENGTH = 30;
	
	private void validateSearchQuery(SearchBean search, BindingResult result) {
		/* check string */
		if (search.getQuery() == null
			|| "".equals(search.getQuery().trim())) {
			result.rejectValue("query", null, "Field is mandatory");
		} else if (search.getQuery().length() < MIN_LENGTH) {
			result.rejectValue("query", null, String.format("Must be at least %s characters", MIN_LENGTH));
		} else if (search.getQuery().length() > MAX_LENGTH) {
			result.rejectValue("query", null, String.format("Must be less than %s characters", MAX_LENGTH));
		}
		
		/* check min price */
		if (search.getMin() != null && !"".equals(search.getMin().trim())) {
			try {
				Double.parseDouble(search.getMin());
			} catch (NumberFormatException e) {
				result.rejectValue("min", null, "Wrong price");
			}
		}
		
		/* check max price */
		if (search.getMax() != null && !"".equals(search.getMax().trim())) {
			try {
				Double.parseDouble(search.getMax());
			} catch (NumberFormatException e) {
				result.rejectValue("max", null, "Wrong price");
			}
		}
	}
	
	@RequestMapping(value = ITEM_SEARCH_RESULT_PAGE, method = RequestMethod.GET)
	public String submitItemSearchForm(ModelMap model, @ModelAttribute SearchBean searchBean, BindingResult result) throws Exception {
		/* put some data for statistics */
		String keys[] = searchBean.getQuery().split(" ");
		for (String key : keys) {
			searchService.addSearch(key);
		}
		/* business logic */
		validateSearchQuery(searchBean, result);
		if (!result.hasErrors()) {
			/* keep search parameters in form */
			this.searchBean.copy(searchBean);
			
			/* process search query */
			Double max = "".equals(searchBean.getMax().trim()) ? Double.MAX_VALUE : Double.parseDouble(searchBean.getMax());
			Double min = "".equals(searchBean.getMin().trim()) ? 0 : Double.parseDouble(searchBean.getMin());
			model.put(ITEM_LIST_OBJECT, itemService.getItems(searchBean.getQuery(), min, max));
			
			navigationSequenceBean.getNavigationSequence().clear();
			navigationSequenceBean.getNavigationSequence().add(new NavigationBean("Search", HomePageController.HOME_PAGE));
			navigationSequenceBean.getNavigationSequence().add(new NavigationBean("Result", "javascript:window.location.reload()"));
			model.put(NAVIGATION_SEQUENCE_OBJECT, navigationSequenceBean);
			model.put(REQUEST_URI_OBJECT, ITEM_SEARCH_RESULT_PAGE);
			return ITEM_LIST_VIEW;
		}
		return HomePageController.getHomeView();
	}

}
