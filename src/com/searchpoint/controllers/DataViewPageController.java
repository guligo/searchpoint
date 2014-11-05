package com.searchpoint.controllers;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.searchpoint.entities.Data;
import com.searchpoint.entities.User;
import com.searchpoint.security.UnauthorizedException;
import com.searchpoint.security.UserAuthenticationController;
import com.searchpoint.services.DataService;

/**
 * MVC controller responsible for displaying various data which are stored in
 * database.
 * 
 * @author Igors Gulbinskis
 */
@Controller
public class DataViewPageController {

	public final static String DATA_VIEW_PAGE = "show-data.html";

	@Autowired
	private DataService dataService;

	@RequestMapping(value = DATA_VIEW_PAGE, method = RequestMethod.GET)
	public String showData(HttpSession session, @RequestParam Long id, HttpServletResponse response) throws Exception {
		/* authorization check */
		User user = (User) session.getAttribute(UserAuthenticationController.SESSION_USER_ATTRIBUTE);
		Data data = dataService.getDataById(id);
		if (!user.getId().equals(data.getCompany().getUser().getId())) {
			throw new UnauthorizedException("User tries to submit data for company which does not belong to him");
		}
		/* business logic */
		data = dataService.getDataById(id);
		response.setContentType("text/xml");
		response.getOutputStream().write(data.getXml());
		response.setStatus(HttpURLConnection.HTTP_OK);
		return null;
	}

}
