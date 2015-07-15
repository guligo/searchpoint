package com.searchpoint.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.searchpoint.dao.CommonDAO;
import com.searchpoint.entities.Data;

/**
 * Contains company data related functions.
 * 
 * @author guligo
 */
@Component
public class DataService {
	
	@Autowired
	private CommonDAO commonDAO;	

	public void saveData(Data data) {
		commonDAO.save(data);
	}
	
	public Data getDataById(Long id) {
		return (Data) commonDAO.getEntityManager().createQuery("from Data d join fetch d.company where d.id = :dataId")
			.setParameter("dataId", id)
			.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public Data getDataByCompanyId(Long companyId) {		
		List<Data> data = commonDAO.getEntityManager().createQuery("from Data d where d.company.id = :companyId")
			.setParameter("companyId", companyId)
			.getResultList();
		if (data == null
			|| data.size() == 0) {
			return new Data();
		}
		return data.get(0);
	}

}
