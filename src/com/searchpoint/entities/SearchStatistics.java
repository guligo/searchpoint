package com.searchpoint.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.searchpoint.dao.PersistentEntity;

/**
 * Represents sing search key in the search query.
 * 
 * @author Igors Gulbinskis
 */
@Entity
public class SearchStatistics implements PersistentEntity {

	@Id
	@GeneratedValue
	private Long id;
	private String word;
	private Long count;

	@Override
	public void setId(Long id) {
		this.id = id;	
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
}