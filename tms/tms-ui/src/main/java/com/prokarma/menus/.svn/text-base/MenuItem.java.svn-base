package com.prokarma.menus;

import java.io.Serializable;

import com.prokarma.web.timesheet.BasePage;

public class MenuItem implements Serializable {

	private static final long serialVersionUID = -7355522623489814196L;
	private String title;
	private Class<? extends BasePage> pageClass;

	public MenuItem(String title, Class<? extends BasePage> pageClass) {
		this.title = title;
		this.pageClass = pageClass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Class<? extends BasePage> getPageClass() {
		return pageClass;
	}

	public void setPageClass(Class<? extends BasePage> pageClass) {
		this.pageClass = pageClass;
	}
}
