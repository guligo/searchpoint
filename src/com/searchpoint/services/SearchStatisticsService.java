package com.searchpoint.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchpoint.dao.CommonDAO;
import com.searchpoint.entities.SearchStatistics;

/**
 * Contains company data related functions.
 * 
 * @author Igors Gulbinskis
 */
@Component
public class SearchStatisticsService {
	
	@Autowired
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private CommonDAO commonDAO;
	
	@SuppressWarnings("unchecked")
	public SearchStatistics getSearch(String key) {		
		List<SearchStatistics> result = entityManager.createQuery("from SearchStatistics s where s.word = :word")
			.setParameter("word", key)
			.getResultList();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SearchStatistics> getMostPopularSearches(int count) {
		return entityManager.createQuery("from SearchStatistics s order by s.count desc")
			.setMaxResults(count)
			.getResultList();
	}
	
	public void addSearch(String key) {
		SearchStatistics search = getSearch(key);
		if (search == null) {
			search = new SearchStatistics();
			search.setWord(key);
			search.setCount(new Long(1));
			commonDAO.save(search);
		} else {
			search.setCount(search.getCount() + 1);
			commonDAO.save(search);
		}
	}

}