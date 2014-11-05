package com.searchpoint.beans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Represents backing bean of navigation sequence.
 * 
 * @author Igors Gulbinskis
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NavigationSequenceBean {
	
	public List<NavigationBean> navigationSequence;

	public NavigationSequenceBean() {
		navigationSequence = new ArrayList<NavigationBean>();
	}
	
	public List<NavigationBean> getNavigationSequence() {
		return navigationSequence;
	}

	public void setNavigationSequence(List<NavigationBean> navigationSequence) {
		this.navigationSequence = navigationSequence;
	}	
	
}
