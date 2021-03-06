package com.searchpoint.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.searchpoint.dao.PersistentEntity;

/**
 * Represents company.
 * 
 * @author guligo
 */
@Entity
public class Company implements PersistentEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true, length = 30)
	private String title;
	@Column(nullable = false, unique = true, length = 255)
	private String homepageUrl;
	@Column(nullable = false, unique = false, length = 255)
	private String logoUrl;
	@Column(nullable = false)
	private Status status;
	@ManyToOne
	private User user;
	@OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
	private Set<Item> items;
	@OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
	private Data data;
	/* describes how many user hits got this item */
	private BigDecimal hits;
	
	public Company() {
		homepageUrl = "http://";
		logoUrl = "http://";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getHomepageUrl() {
		return homepageUrl;
	}

	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public BigDecimal getHits() {
		return hits;
	}

	public void setHits(BigDecimal hits) {
		this.hits = hits;
	}

}
