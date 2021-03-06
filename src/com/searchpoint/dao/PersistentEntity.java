package com.searchpoint.dao;

/**
 * A common interface for all persistent entities in a system.
 * 
 * @author guligo;
 */
public interface PersistentEntity {
	
	public void setId(Long id);
	
	public Long getId();

}
