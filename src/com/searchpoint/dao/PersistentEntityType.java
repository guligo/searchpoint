package com.searchpoint.dao;

import com.searchpoint.entities.Category;
import com.searchpoint.entities.Company;
import com.searchpoint.entities.Item;
import com.searchpoint.entities.User;

/**
 * This is a registry of persistent objects in a system to delete everything from database and
 * to create instances of persistent objects.
 * 
 * @author guligo
 */
public enum PersistentEntityType {

	CATEGORY(Category.class, null),
	COMPANY(Company.class, null),
	ITEM(Item.class, null),
	USER(User.class, null);

	private Class<? extends PersistentEntity> clazz;
	private Class<? extends PersistentEntity>[] subClasses;

	PersistentEntityType(Class<? extends PersistentEntity> clazz, Class<? extends PersistentEntity>[] subClasses){
		this.clazz = clazz;
		this.subClasses = subClasses;
	}

	public Class<? extends PersistentEntity> getObjectClass(){
		return this.clazz;
	}		

	public Class<? extends PersistentEntity>[] getSubClasses() {
		return subClasses;
	}

}

