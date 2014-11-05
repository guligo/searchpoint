package com.searchpoint.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchpoint.dao.CommonDAO;
import com.searchpoint.entities.User;

/**
 * Contains methods for operation with users.
 * 
 * @author Igors Gulbinskis
 */
@Component
public class UserService {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private CommonDAO commonDAO;

	public void addUser(User user) {
		commonDAO.save(user);
	}

	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) {
		List<User> users = entityManager.createQuery("from User u where u.email = :email")
			.setParameter("email", email)
			.getResultList();
		if (users == null
			|| users.size() == 0) {
			return null;
		}
		return users.get(0);
	}

}
