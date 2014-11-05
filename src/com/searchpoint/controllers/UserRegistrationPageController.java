package com.searchpoint.controllers;

import javax.servlet.http.HttpServletRequest;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.searchpoint.entities.User;
import com.searchpoint.security.UserAuthenticationController;
import com.searchpoint.services.UserService;

/**
 * MVC controller responsible for actions on registration page.
 * 
 * @author Igors Gulbinskis
 */
@Controller
public class UserRegistrationPageController {
	
	private final String USER_REGISTRATION_PAGE = "user-registration.html";
	private final String USER_REGISTRATION_ACTION = "submit-user-registration.html";
	
	private final String USER_REGISTRATION_VIEW = "user-registration";
	
	private final String USER_OBJECT = "user";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = USER_REGISTRATION_PAGE, method = RequestMethod.GET)
	public String showUserRegistrationPage(ModelMap model) throws Exception {
		model.put(USER_OBJECT, new User());
		return USER_REGISTRATION_VIEW;
	}
	
	private final int MIN_LENGTH = 5;
	private final int MAX_LENGTH = 30;
	
	private void validateUserRegistrationData(HttpServletRequest request, User user, BindingResult result) {		
		/* check password */
		if (user.getPassword() == null
			|| "".equals(user.getPassword().trim())) {
			result.rejectValue("password", null, "Field is mandatory");
		} else if (user.getPassword().length() < MIN_LENGTH) {
			result.rejectValue("password", null, String.format("Must be at least %s characters", MIN_LENGTH));
		} else if (user.getPassword().length() > MAX_LENGTH) {
			result.rejectValue("password", null, String.format("Must be less than %s characters", MAX_LENGTH));
		} else if (!user.getPassword().equals(user.getConfirm())) {
			result.rejectValue("password", null, "Passwords do not match");
		}
		
		/* check email */
		if (user.getEmail() == null
			|| "".equals(user.getEmail().trim())) {
			result.rejectValue("email", null, "Field is mandatory");
		} else if (user.getEmail().length() < MIN_LENGTH) {
			result.rejectValue("email", null, String.format("Must be at least %s characters", MIN_LENGTH));
		} else if (user.getEmail().length() > MAX_LENGTH) {
			result.rejectValue("email", null, String.format("Must be less than %s characters", MAX_LENGTH));
		} else if (userService.getUserByEmail(user.getEmail()) != null) {
			result.rejectValue("email", null, "Already taken");
		}
		
		/* check if user has agreed with the terms of use */
		if (user.getAgree() == null || user.getAgree() == false) {
			result.rejectValue("agree", null, "Please read \"Terms of Use\"");
		}
		
		/* check captcha */
		// if (!result.hasErrors()) {
		if (true) {
			String remoteAddr = request.getRemoteAddr();
			String challenge = request.getParameter("recaptcha_challenge_field");
	        String uresponse = request.getParameter("recaptcha_response_field");
			
	        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	        reCaptcha.setPrivateKey("6LepCcUSAAAAANy8pusO7G-yLkVGh0EtsweKt1WE");		        
	        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
	
	        if (!reCaptchaResponse.isValid()) {
	        	result.rejectValue("captcha", null, "You failed human verification");
	        }
		}
	}
	
	@RequestMapping(value = USER_REGISTRATION_ACTION, method = RequestMethod.POST)
	public String submitUserRegistrationForm(HttpServletRequest request, @ModelAttribute User user, BindingResult result) throws Exception {
		validateUserRegistrationData(request, user, result);		
		if (!result.hasErrors()) {
			userService.addUser(user);			
			return "redirect: " + UserAuthenticationController.LOGIN_PAGE;
		}
		return USER_REGISTRATION_VIEW;
	}
	
}
