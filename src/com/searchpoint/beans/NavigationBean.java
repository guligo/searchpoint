package com.searchpoint.beans;

/**
 * Represents backing bean of navigation sequence element.
 * 
 * @author guligo
 */
public class NavigationBean {
	
	public String name;
	public String link;
	
	public NavigationBean(String name, String link) {
		this.name = name;
		this.link = link;
	}
	
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

}
