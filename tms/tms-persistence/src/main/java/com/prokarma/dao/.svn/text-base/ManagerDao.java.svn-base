package com.prokarma.dao;

import java.util.List;

import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Timesheet;

public interface ManagerDao {

	public void addEmployee(Employee employee);

	public void editEmployee(Employee employee);

	public void deleteEmployee(Employee employee);

	public void addProject(Project project);

	public void editProject(Project project);

	public List<Timesheet> manageTimesheets(int mgrId);

	public List<Employee> getEmployees();

	public List<Project> getProjectDetails();

	public void approveTimesheet(int timesheetId);

	public void declineTimesheet(int timesheetId);

}
