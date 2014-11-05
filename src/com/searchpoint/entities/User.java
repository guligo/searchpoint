package com.searchpoint.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.searchpoint.dao.PersistentEntity;

/**
 * Represents user of a system.
 * 
 * @author Igors Gulbinskis
 */
@Entity
public class User implements PersistentEntity {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true, length = 30)
	private String email;
	@Column(nullable = false, unique = true, length = 30)
	private String password;
	@Transient
	private String confirm;	
	@Transient
	private Boolean agree;
	@Transient
	private String captcha;
	@OneToMany(mappedBy = "user")
	private Set<Company> companies;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAgree() {
		return agree;
	}

	public void setAgree(Boolean agree) {
		this.agree = agree;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

}
