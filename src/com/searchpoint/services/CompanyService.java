package com.searchpoint.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.searchpoint.dao.CommonDAO;
import com.searchpoint.entities.Company;

/**
 * Contains methods for operation with companies.
 * 
 * @author guligo
 */
@Component
public class CompanyService {

	@Autowired
	private CommonDAO commonDAO;

	public void saveCompany(Company company) {
		commonDAO.save(company);
	}

	public void updateCompany(Company company) {
		commonDAO.save(company);
	}
	
	@Transactional
	public void deleteCompany(Long id) {
		Company company = getCompanyById(id);
		commonDAO.delete(company);
	}

	@SuppressWarnings("unchecked")
	public Company getCompanyByTitle(String title) {		
		List<Company> companies = commonDAO.getEntityManager().createQuery("from Company c where c.title = :title")
			.setParameter("title", title)
			.getResultList();
		if (companies == null
			|| companies.size() == 0) {
			return null;
		}
		return companies.get(0);
	}

	public Company getCompanyById(Long id) {		
		return commonDAO.getById(Company.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Company> getCompaniesByUserEmail(String email) {
		return commonDAO.getEntityManager().createQuery("from Company c where c.user.email = :email")
			.setParameter("email", email)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Company> getCompanies() {
		return commonDAO.getEntityManager().createQuery("from Company")
			.getResultList();
	}		
	
	@SuppressWarnings("unchecked")
	public List<Company> getMostPopularCompanies(int count) {
		return commonDAO.getEntityManager().createQuery("from Company c order by c.hits desc")
			.setMaxResults(count)
			.getResultList();
	}
	
	public static final int ASCII_A = 65;
	public static final int ASCII_Z = 90;	
	public static final int DIFF = 32;
	public static final String NUMBER_KEY = "0 - 9";
	
	@SuppressWarnings("unchecked")
	public Map<String, List<Company>> getCompaniesGroupedByLetter() {
		Map<String, List<Company>> result = new HashMap<String, List<Company>>();
		for (int i = 0; i <= 9; i++) {
			List<Company> companies = (List<Company>) commonDAO.getEntityManager().createQuery("from Company c where c.title like :title")
				.setParameter("title", String.valueOf((char) (i)) + "%")
				.getResultList();
			if (result.get(NUMBER_KEY) == null) {
				result.put(NUMBER_KEY, new ArrayList<Company>());
			}
			result.get(NUMBER_KEY).addAll(companies);
		}
		for (int i = ASCII_A; i <= ASCII_Z; i++) {
			List<Company> companies = (List<Company>) commonDAO.getEntityManager().createQuery("from Company c where c.title like :title1 or c.title like :title2")
				.setParameter("title1", String.valueOf((char) (i)) + "%")
				.setParameter("title2", String.valueOf((char) (i + DIFF)) + "%")
				.getResultList();
			result.put(String.valueOf((char) (i)), companies);
		}
		return result;
	}
	
}
