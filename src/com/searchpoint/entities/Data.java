package com.searchpoint.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.searchpoint.dao.PersistentEntity;

/**
 * Represents company data.
 * 
 * @author Igors Gulbinskis
 */
@Entity
public class Data implements PersistentEntity {

	@Id
	@GeneratedValue
	private Long id;
	/* used in data upload form */
	@Transient
	private CommonsMultipartFile file;	
	@Lob
	private byte[] xml;
	private String xmlFileName;
	@Column(nullable = true)
	private Boolean useUrl;
	private String url;
	@OneToOne(fetch = FetchType.LAZY)
	private Company company;
	
	public Data() {
		useUrl = false;
		url = "http://";
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	public Boolean getUseUrl() {
		return useUrl;
	}

	public void setUseUrl(Boolean useUrl) {
		this.useUrl = useUrl;
	}

	public byte[] getXml() {
		return xml;
	}

	public void setXml(byte[] xml) {
		this.xml = xml;
	}		

	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}				

}
