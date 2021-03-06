package com.searchpoint.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.searchpoint.services.CategoryService;

/**
 * MVC controller responsible for displaying categories.
 * 
 * @author guligo
 */
@Controller
public class CategoriesPageController {
	
	public final static String CATEGORY_LIST_PAGE = "categories.html";
	
	private final String CATEGORY_LIST_VIEW = "categories";
	
	private final String CATEGORY_LIST_OBJECT = "categories";
		
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = CATEGORY_LIST_PAGE, method = RequestMethod.GET)
	public String showCategoryList(ModelMap model) throws Exception {
		model.put(CATEGORY_LIST_OBJECT, categoryService.getRootCategories());
		return CATEGORY_LIST_VIEW;
	}
	
}
