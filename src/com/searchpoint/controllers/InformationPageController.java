package com.searchpoint.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * MVC controller responsible for operations on information page.
 * 
 * @author guligo
 */
@Controller
public class InformationPageController {

	public final static String INFO_PAGE = "info.html";

	private final String INFO_VIEW = "info";

	@RequestMapping(value = INFO_PAGE, method = RequestMethod.GET)
	public String showInfoPage() throws Exception {
		return INFO_VIEW;
	}

}