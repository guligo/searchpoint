package com.searchpoint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchpoint.dao.CommonDAO;
import com.searchpoint.entities.Category;

/**
 * Contains methods for operation with categories.
 * 
 * @author guligo
 */
@Component
public class CategoryService {
	
	@Autowired
	private CommonDAO commonDAO;
	
	public void addCategory(Category category) {
		commonDAO.save(category);
	}
	
	public Category getCategoryById(Long categoryId) {
		return commonDAO.getById(Category.class, categoryId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getRootCategories() {
		return commonDAO.getEntityManager().createQuery("from Category c where c.parent is null order by c.name")
			.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getCategoriesByParentId(Long parentId) {
		return commonDAO.getEntityManager().createQuery("from Category c where c.parent.id = :parentId order by c.name")
			.setParameter("parentId", parentId)
			.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getCategories() {
		return commonDAO.getEntityManager().createQuery("from Category c order by c.name")
			.getResultList();
	}
	
}
