package com.searchpoint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.searchpoint.dao.CommonDAO;
import com.searchpoint.entities.Item;

/**
 * Contains methods for operation with items.
 * 
 * @author guligo
 */
@Component
public class ItemService {
	
	@Autowired
	private CommonDAO commonDAO;

	public void saveItem(Item item) {
		commonDAO.save(item);
	}
	
	public void saveItems(List<Item> items) {
		commonDAO.saveAll(items);
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> getItems(Long categoryId) {
		/* currently we have only two levels of categories */
		return commonDAO.getEntityManager().createQuery("from Item i where i.category.id = :categoryId or " +
			"i.category.id in (select c.id from Category c where c.parent.id = :categoryId)")
			.setParameter("categoryId", categoryId)
			.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> getItems(Long categoryId, int start, int count) {
		return commonDAO.getEntityManager().createQuery("from Item i where i.category.id = :categoryId")
			.setParameter("categoryId", categoryId)
			.setFirstResult(start)
			.setMaxResults(count)
			.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> getItemsByCompany(Long companyId) {
		return commonDAO.getEntityManager().createQuery("from Item i where i.company.id = :companyId")
			.setParameter("companyId", companyId)
			.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> getMostPopularItems(int count) {
		return commonDAO.getEntityManager().createQuery("from Item i order by i.hits desc")
			.setMaxResults(count)
			.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> getItems(String query, Double min, Double max) {
		return commonDAO.getEntityManager().createQuery("from Item i where i.name like :name and i.price >= :min and i.price <= :max")
			.setParameter("name", "%" + query + "%")
			.setParameter("min", min)
			.setParameter("max", max)
			.getResultList();
	}
	
	@Transactional
	public void deleteItemsByCompanyId(Long companyId) {
		commonDAO.getEntityManager().createQuery("delete from Item i where i.company.id = :companyId")
			.setParameter("companyId", companyId)
			.executeUpdate();
	}
	
}
