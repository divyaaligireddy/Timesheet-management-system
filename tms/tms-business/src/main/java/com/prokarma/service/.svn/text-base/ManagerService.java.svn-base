package com.prokarma.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Timesheet;

public interface ManagerService {

	@Transactional(propagation = Propagation.REQUIRED)
	public void addEmployee(Employee employee);

	@Transactional(propagation = Propagation.REQUIRED)
	public void editEmployee(Employee employee);

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteEmployee(Employee employee);

	@Transactional(propagation = Propagation.REQUIRED)
	public void addProject(Project project);

	@Transactional(propagation = Propagation.REQUIRED)
	public void editProject(Project project);

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public List<Timesheet> manageTimesheets(int mgrId);

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public List<Employee> getEmployees();

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public List<Project> getProjectDetails();

	@Transactional(propagation = Propagation.REQUIRED)
	public void approveTimesheet(int timesheetId);

	@Transactional(propagation = Propagation.REQUIRED)
	public void declineTimesheet(int timesheetId);

}
