package com.searchpoint.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchpoint.dao.CommonDAO;

/**
 * Contains database management methods.
 * 
 * @author guligo
 */
@Component
public class DataBaseService {
	
	@Autowired
	private CommonDAO commonDAO;
	
	public void cleanDatabase() {
		commonDAO.cleanupDB();
	}
	
}
