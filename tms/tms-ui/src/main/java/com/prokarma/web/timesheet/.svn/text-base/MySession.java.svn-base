package com.prokarma.web.timesheet;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Timesheet;

public class MySession extends WebSession {

	private static final long serialVersionUID = 1L;
	
	private static Employee employee;
	private int userId;
	private String username;
	private String role = "none";
	
	private static Project project;
	private static Timesheet timesheet;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MySession(final Request request) {
		super(request);

	}

	public static Employee getEmployee() {
		return employee;
	}

	@SuppressWarnings("static-access")
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		MySession.project = project;
	}

	public static Timesheet getTimesheet() {
		return timesheet;
	}

	public void setTimesheet(Timesheet timesheet) {
		MySession.timesheet = timesheet;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
