package com.searchpoint.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.searchpoint.controllers.HomePageController;
import com.searchpoint.controllers.UserCompaniesPageController;
import com.searchpoint.entities.User;
import com.searchpoint.services.UserService;

/**
 * MVC controller responsible for login and logout operations.
 * 
 * @author guligo
 */
@Controller
public class UserAuthenticationController {

	public final static String SESSION_USER_ATTRIBUTE = "user";
	public final static String LOGIN_PAGE = "login.html";
	public final static String LOGIN_ACTION = "submit-login.html";
	public final static String LOGOUT_ACTION = "logout.html";
	
	private final String LOGIN_VIEW = "login";

	private final String USER_OBJECT = "user";

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = LOGIN_PAGE, method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) throws Exception {
		model.put(USER_OBJECT, new User());
		return LOGIN_VIEW;
	}	
	
	private void validateUserLoginData(User user, BindingResult result) {
		/* check username */
		if (user.getEmail() == null
			|| "".equals(user.getEmail().trim())) {
			result.rejectValue("email", null, "Field is mandatory");
		}
		
		/* check password */
		if (user.getPassword() == null
			|| "".equals(user.getPassword().trim())) {
			result.rejectValue("password", null, "Field is mandatory");
		}
		
		/* authenticate user with given username and password */
		if (!result.hasErrors()) {
			User check = userService.getUserByEmail(user.getEmail());
			if (check == null || !check.getPassword().equals(user.getPassword())) {
				result.rejectValue("email", null, "Wrong credentials");
			}
		}
	}
	
	@RequestMapping(value = LOGIN_ACTION, method = RequestMethod.POST)
	public String submitLoginForm(HttpSession session, @ModelAttribute User user, BindingResult result) throws Exception {
		validateUserLoginData(user, result);
		if (!result.hasErrors()) {
			User entity = userService.getUserByEmail(user.getEmail());
			session.setAttribute(SESSION_USER_ATTRIBUTE, entity);
			return "redirect:" + UserCompaniesPageController.USER_COMPANIES_PAGE;
		}
		return LOGIN_VIEW;
	}

	@RequestMapping(value = LOGOUT_ACTION, method = RequestMethod.GET)
	public String doLogout(HttpSession session, ModelMap model) throws Exception {
		session.removeAttribute(SESSION_USER_ATTRIBUTE);
		return "redirect:" + HomePageController.HOME_PAGE;
	}

}