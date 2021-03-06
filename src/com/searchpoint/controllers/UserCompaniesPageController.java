package com.searchpoint.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.searchpoint.entities.Company;
import com.searchpoint.entities.User;
import com.searchpoint.security.UnauthorizedException;
import com.searchpoint.security.UserAuthenticationController;
import com.searchpoint.services.CompanyService;

/**
 * MVC controller responsible for actions on user companies page.
 * 
 * @author guligo
 */
@Controller
public class UserCompaniesPageController {
	
	public final static String USER_COMPANIES_PAGE = "user-companies.html";
	public final static String USER_COMPANY_DELETE_ACTION = "delete-user-company.html";
	
	private final String USER_COMPANIES_VIEW = "user-companies";
	
	private final String COMPANIES_OBJECT = "companies";
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = USER_COMPANIES_PAGE, method = RequestMethod.GET)
	public String showUserCompaniesPage(HttpSession session, ModelMap model) throws Exception {
		User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
		List<Company> companies = companyService.getCompaniesByUserEmail(user.getEmail());
		model.put(COMPANIES_OBJECT, companies);
		return USER_COMPANIES_VIEW;
	}
	
	@RequestMapping(value = USER_COMPANY_DELETE_ACTION, method = RequestMethod.GET)
	public String deleteUserCompany(HttpSession session, ModelMap model, @RequestParam Long id) throws Exception {
		/* authorization check */
		User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
		Company company = companyService.getCompanyById(id);
		if (!user.getId().equals(company.getUser().getId())) {
			throw new UnauthorizedException("User tries to delete company which does not belong to him");
		}
		/* business logic */
		companyService.deleteCompany(id);
		return "redirect:" + USER_COMPANIES_PAGE;
	}
	
}
