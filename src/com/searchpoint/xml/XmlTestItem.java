package com.searchpoint.xml;

import java.io.Serializable;

/**
 * XML format system salidzini.lv. We just use it for testing. This class is not
 * quite Java-style, but do not pay attention to it - it is done for sake of
 * xStream. 
 * 
 * @author Igors Gulbinskis
 */
public class XmlTestItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String link;
	private Double price;
	private String image;
	private String category;
	private String category_full;
	private String category_link;
	private Long in_stock;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory_full() {
		return category_full;
	}

	public void setCategory_full(String category_full) {
		this.category_full = category_full;
	}

	public String getCategory_link() {
		return category_link;
	}

	public void setCategory_link(String category_link) {
		this.category_link = category_link;
	}

	public Long getIn_stock() {
		return in_stock;
	}

	public void setIn_stock(Long in_stock) {
		this.in_stock = in_stock;
	}

}
