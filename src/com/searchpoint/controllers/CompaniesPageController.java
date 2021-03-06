package com.searchpoint.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.searchpoint.entities.Company;
import com.searchpoint.services.CompanyService;

/**
 * MVC controller responsible for displaying list of shops.
 * 
 * @author guligo
 */
@Controller
public class CompaniesPageController {
	
	public final static String COMPANY_LIST_PAGE = "companies.html"; 
	
	private final String COMPANY_LIST_VIEW = "companies";

	private final String SHOPS_OBJECT = "companies";
	private final String KEYS_OBJECT = "keys";

	@Autowired
	private CompanyService companyService;	
	
	@RequestMapping(value = COMPANY_LIST_PAGE, method = RequestMethod.GET)
	public String showCompanyList(ModelMap model) throws Exception {
		List<String> keys = new ArrayList<String>();
		keys.add(CompanyService.NUMBER_KEY);
		for (int i = CompanyService.ASCII_A; i <= CompanyService.ASCII_Z; i++) {
			keys.add(String.valueOf((char) i));
		}		
		
		Map<String, List<Company>> companies = companyService.getCompaniesGroupedByLetter();
		model.put(SHOPS_OBJECT, companies);		
		model.put(KEYS_OBJECT, keys);
		return COMPANY_LIST_VIEW;
	}

}
