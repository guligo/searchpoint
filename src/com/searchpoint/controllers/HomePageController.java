package com.searchpoint.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.searchpoint.beans.SearchBean;
import com.searchpoint.services.SearchStatisticsService;

/**
 * Simple hello world MVC controller.
 * 
 * @author Igors Gulbinskis
 */
@Controller
public class HomePageController {

	// private static final Logger logger = Logger.getLogger(HomePageController.class.getName());
	
	private final static int N = 35;
	
	public final static String HOME_PAGE = "home.html";
	public final static String TAG_CLOUD = "cloud.html";
	
	private final static String HOME_VIEW = "home";
	private final String CLOUD_VIEW = "cloud";	
	private final String SEARCH_LIST = "searches";
	private final String SEARCH_OBJECT = "searchBean";
	
	@Autowired
	private SearchStatisticsService searchService;
	@Autowired
	private SearchBean searchBean;	
	
	public static String getHomeView() {
		return HOME_VIEW;
	}
	
	/**
	 * Show hello world page.
	 */
	@RequestMapping(value = HOME_PAGE, method = RequestMethod.GET)
	public String showHelloWorldPage(ModelMap model) throws Exception {
		model.put(SEARCH_OBJECT, searchBean);
		return getHomeView();
	}
	
	/**
	 * Show tag cloud.
	 */
	@RequestMapping(value = TAG_CLOUD, method = RequestMethod.GET)
	public String showTagColud(ModelMap model) throws Exception {
		model.put(SEARCH_LIST, searchService.getMostPopularSearches(N));
		return CLOUD_VIEW;
	}

}