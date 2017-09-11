package com.prokarma.web;

import java.util.Locale;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.prokarma.web.timesheet.LoginPage;
import com.prokarma.web.timesheet.MySession;

/**
 * Application object for your web application.
 * 
 * We will use this (empty) class to extend from different implementations in
 * the examples. This way we don't have to change the Spring configuation if we
 * want to use another example.
 */
public class TmsApplication extends WebApplication {

	/**
	 * Constructor
	 */
	public TmsApplication() {
	}

	/**
	 * Init the application
	 */
	@Override
	protected void init() {
		super.init();

		// Define locale to be used when locale can not be determined.
		Locale.setDefault(Locale.ENGLISH);
		getDebugSettings().setAjaxDebugModeEnabled(true);

		addComponentInstantiationListener(new SpringComponentInjector(this));
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends Page> getHomePage() {
		return LoginPage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new MySession(request);
	}

}
