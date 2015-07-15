package com.searchpoint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchpoint.dao.CommonDAO;
import com.searchpoint.entities.User;

/**
 * Contains methods for operation with users.
 * 
 * @author guligo
 */
@Component
public class UserService {

	@Autowired
	private CommonDAO commonDAO;

	public void addUser(User user) {
		commonDAO.save(user);
	}

	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) {
		List<User> users = commonDAO.getEntityManager().createQuery("from User u where u.email = :email")
			.setParameter("email", email)
			.getResultList();
		if (users == null
			|| users.size() == 0) {
			return null;
		}
		return users.get(0);
	}

}
