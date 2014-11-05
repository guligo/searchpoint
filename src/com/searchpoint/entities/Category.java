package com.searchpoint.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.searchpoint.dao.PersistentEntity;

/**
 * Represents category.
 * 
 * @author Igors Gulbinskis
 */
@Entity
public class Category implements PersistentEntity {
	
	@Id
	@GeneratedValue
	private Long id;	
	private String name;
	private String imageUrl;
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Set<Item> items;
	@ManyToOne
	private Category parent;
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
	private Set<Category> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
}
