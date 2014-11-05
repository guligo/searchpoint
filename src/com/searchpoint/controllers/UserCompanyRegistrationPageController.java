package com.searchpoint.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.searchpoint.entities.Company;
import com.searchpoint.entities.Status;
import com.searchpoint.entities.User;
import com.searchpoint.security.UnauthorizedException;
import com.searchpoint.security.UserAuthenticationController;
import com.searchpoint.services.CompanyService;
import com.searchpoint.utils.Country;

/**
 * MVC controller responsible for actions on user company registration page.
 * 
 * @author Igors Gulbinskis
 */
@Controller
public class UserCompanyRegistrationPageController {
	
	public final static String USER_COMPANY_ADD_PAGE = "add-user-company.html";
	public final static String USER_COMPANY_EDIT_PAGE = "edit-user-company.html";
	public final static String USER_COMPANY_ADD_ACTION = "submit-add-user-company.html";
	public final static String USER_COMPANY_EDIT_ACTION = "submit-edit-user-company.html";
	
	private final String USER_COMPANY_REGISTRATION_VIEW = "user-company-registration";
	private final String USER_COMPANY_EDIT_VIEW = "user-company-edit";
	private final String USER_COMPANY_EDIT_SUCCESS_VIEW = USER_COMPANY_EDIT_VIEW;
	
	private final String COMPANY_OBJECT = "company";
	private final String COUNTRIES_OBJECT = "countries";
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = USER_COMPANY_ADD_PAGE, method = RequestMethod.GET)
	public String showUserCompanyRegistrationPage(ModelMap model) throws Exception {
		model.put(COUNTRIES_OBJECT, Country.COUNTRY_LIST);
		model.put(COMPANY_OBJECT, new Company());	
		return USER_COMPANY_REGISTRATION_VIEW;
	}
	
	@RequestMapping(value = USER_COMPANY_EDIT_PAGE, method = RequestMethod.GET)
	public String showUserCompanyEditPage(HttpSession session, @RequestParam Long id, ModelMap model) throws Exception {
		/* authorization check */
		User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
		Company company = companyService.getCompanyById(id);
		if (!user.getId().equals(company.getUser().getId())) {
			throw new UnauthorizedException("User tries to access edit form for company which does not belong to him");
		}
		/* business logic */
		model.put(COUNTRIES_OBJECT, Country.COUNTRY_LIST);	
		model.put(COMPANY_OBJECT, companyService.getCompanyById(id));
		return USER_COMPANY_EDIT_VIEW;
	}
	
	private final int MIN_LENGTH = 5;
	private final int MAX_LENGTH = 30;
	
	private final int MIN_URL_LENGTH = 10;
	private final int MAX_URL_LENGTH = 255;
	
	private final int MAX_NUMBER_OF_COMPANIES = 8;
	
	private final int ADD = 1;
	private final int EDIT = 2;
	
	private void validateUserCompanyRegistrationData(HttpSession session, Company company, BindingResult result, int mode) {
		/* validate company title */
		if (mode == ADD) {
			if (company.getTitle() == null
				|| "".equals(company.getTitle().trim())) {
				result.rejectValue("title", null, "Field is mandatory");
			} else if (company.getTitle().length() < MIN_LENGTH) {
				result.rejectValue("title", null, String.format("Must be at least %s characters", MIN_LENGTH));
			} else if (company.getTitle().length() > MAX_LENGTH) {
				result.rejectValue("title", null, String.format("Must be less than %s characters", MAX_LENGTH));
			} else if (companyService.getCompanyByTitle(company.getTitle()) != null) {
				result.rejectValue("title", null, "Already taken");
			} else {
				/* check if number of companies have not exceeded limit */
				User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
				if (companyService.getCompaniesByUserEmail(user.getEmail()).size() >= MAX_NUMBER_OF_COMPANIES) {
					result.rejectValue("title", null, "Limit of companies for one user exceeded");
				}
			}
		}
		
		/* validate homepage url */
		if (company.getHomepageUrl() == null
			|| "".equals(company.getHomepageUrl().trim())) {
			result.rejectValue("homepageUrl", null, "Field is mandatory");
		} else if (company.getHomepageUrl().length() < MIN_URL_LENGTH) {
			result.rejectValue("homepageUrl", null, String.format("Must be at least %s characters", MIN_URL_LENGTH));
		} else if (company.getHomepageUrl().length() > MAX_URL_LENGTH) {
			result.rejectValue("homepageUrl", null, String.format("Must be less than %s characters", MAX_URL_LENGTH));
		} else if (company.getHomepageUrl().startsWith("http://") == false
			&& company.getHomepageUrl().startsWith("https://") == false) {
			result.rejectValue("homepageUrl", null, "Field must be a valid URL");
		}
		
		/* validate logo url */
		if (company.getLogoUrl() == null
			|| "".equals(company.getLogoUrl().trim())) {
			result.rejectValue("logoUrl", null, "Field is mandatory");
		} else if (company.getLogoUrl().length() < MIN_URL_LENGTH) {
			result.rejectValue("logoUrl", null, String.format("Must be at least %s characters", MIN_URL_LENGTH));
		} else if (company.getLogoUrl().length() > MAX_URL_LENGTH) {
			result.rejectValue("logoUrl", null, String.format("Must be less than %s characters", MAX_URL_LENGTH));
		} else if (company.getLogoUrl().startsWith("http://") == false
			&& company.getLogoUrl().startsWith("https://") == false) {
			result.rejectValue("logoUrl", null, "Field must be a valid URL");
		}
	}
	
	@RequestMapping(value = USER_COMPANY_ADD_ACTION, method = RequestMethod.POST)
	public String submitUserCompanyRegistrationForm(HttpSession session, ModelMap model, @ModelAttribute Company company, BindingResult result) throws Exception {
		validateUserCompanyRegistrationData(session, company, result, ADD);
		if (!result.hasErrors()) {
			User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
			company.setUser(user);
			company.setStatus(Status.COMPANY_STATUS_OK);
			companyService.saveCompany(company);						
			return "redirect:" + UserCompaniesPageController.USER_COMPANIES_PAGE;
		}
		model.put(COUNTRIES_OBJECT, Country.COUNTRY_LIST);
		return USER_COMPANY_REGISTRATION_VIEW;
	}
	
	@RequestMapping(value = USER_COMPANY_EDIT_ACTION, method = RequestMethod.POST)
	public String submitUserCompanyEditForm(HttpSession session, ModelMap model, @ModelAttribute Company company, BindingResult result) throws Exception {
		validateUserCompanyRegistrationData(session, company, result, EDIT);
		if (!result.hasErrors()) {
			User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
			company.setUser(user);
			company.setStatus(Status.COMPANY_STATUS_OK);
			companyService.updateCompany(company);						
			return USER_COMPANY_EDIT_SUCCESS_VIEW;
		}
		model.put(COUNTRIES_OBJECT, Country.COUNTRY_LIST);
		return USER_COMPANY_EDIT_VIEW;
	}
	
}
