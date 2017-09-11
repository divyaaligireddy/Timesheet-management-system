package com.prokarma.service.impl;

import java.util.List;

import com.prokarma.dao.ManagerDao;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Timesheet;

import com.prokarma.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {

	private ManagerDao managerdao;


	public void addEmployee(Employee employee) {

		managerdao.addEmployee(employee);
	}

	public void editEmployee(Employee employee) {
		managerdao.editEmployee(employee);
	}

	public void deleteEmployee(Employee employee) {
		managerdao.deleteEmployee(employee);
	}

	public void addProject(Project project) {
		managerdao.addProject(project);
	}

	public void editProject(Project project) {
		managerdao.editProject(project);
	}

	public List<Timesheet> manageTimesheets(int mgrId) {
		List<Timesheet> timesheet = managerdao.manageTimesheets(mgrId);
		return timesheet;
	}

	public List<Employee> getEmployees() {
		return managerdao.getEmployees();
	}

	public List<Project> getProjectDetails() {
		return managerdao.getProjectDetails();
	}

	public void approveTimesheet(int timesheetId) {
		managerdao.approveTimesheet(timesheetId);
	}

	public void declineTimesheet(int timesheetId) {
		managerdao.declineTimesheet(timesheetId);
	}
	
	public ManagerDao getManagerdao() {
		return managerdao;
	}

	public void setManagerdao(ManagerDao managerdao) {
		this.managerdao = managerdao;
	}

}
