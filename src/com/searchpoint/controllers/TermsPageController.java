package com.searchpoint.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * MVC controller responsible for operations on terms of use page.
 * 
 * @author Igors Gulbinskis
 */

// XXX: Unused! Because terms of use will be an javascript pop-up.
// @Controller
public class TermsPageController {
	
	public final static String TERMS_PAGE = "terms.html";
	
	private final String TERMS_VIEW = "terms";

	@RequestMapping(value = TERMS_PAGE, method = RequestMethod.GET)
	public String showTermsPage() throws Exception {
		return TERMS_VIEW;
	}

}
