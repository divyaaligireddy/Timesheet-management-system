package com.prokarma.domainmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Project extends Base implements Serializable {

	private static final long serialVersionUID = 1L;
	private int projectId;
	private String projectName;
	private String codename;
	private String techUsed;
	private String description;
	private Date start_Date;
	private Date end_Date;
	private String status;
	private int managerId;

	private Set<Timesheet> timesheets = new HashSet<Timesheet>();

	private Set<Employee> employees = new HashSet<Employee>();


	public Set<Timesheet> getTimesheets() {
		return timesheets;
	}

	public void setTimesheets(Set<Timesheet> timesheets) {
		this.timesheets = timesheets;
	}

	public void addTimesheet(final Timesheet t) {
		this.timesheets.add(t);
	}
	
	public Employee getManager() {
		
		for (Employee emp : employees) {
			if (emp.getRole().equals(Role.Manager)) {
				return emp;
			}
		}
		
		return null;
	}

	public void setManager(Employee manager) {
	
		if (!manager.getRole().equals(Role.Manager)) {
			throw new RuntimeException("employee is not a manager");
		}

		for (Employee emp : employees) {
			if (emp.getRole().equals(Role.Manager)) {
				throw new RuntimeException(
						"there is already manager associated with this project:[current project manager="
								+ emp.getFirstname() + "]");
			}
		}
		this.employees.add(manager);
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(final Employee emp) {
		this.employees.add(emp);
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getTechUsed() {
		return techUsed;
	}

	public void setTechUsed(String techUsed) {
		this.techUsed = techUsed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_Date() {
		return start_Date;
	}

	public void setStart_Date(Date start_Date) {
		this.start_Date = start_Date;
	}

	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}
	
	public List<String> getEmployeeNames() {
		List<String> employeeNames = new ArrayList<String>();
		for(Employee e: employees) {
			employeeNames.add(e.getUsername());
		}
		return employeeNames;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	@Override
	public boolean equals(final Object other) {
		
		if(other == null) {
			return false;
		}
		
		if (!(other instanceof Project)) {
			return false;
		}

		Project p = (Project) other;

		if (this.codename.equals(p.getCodename())) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 32 * this.codename.hashCode();
	}
}
