package com.prokarma.service.impl;


import com.prokarma.dao.EmployeeDao;
import com.prokarma.dao.ProjectDao;
import com.prokarma.dao.TimesheetDao;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Timesheet;

import com.prokarma.service.TimesheetService;

public class TimesheetServiceImpl implements TimesheetService {

	private TimesheetDao timesheetDao;

	private ProjectDao projectDao;

	private EmployeeDao employeeDao;


	public void addTimesheet(Timesheet t, String userName, String projectCode) {
		System.out.println("username in service:" + userName);
		Project p = projectDao.getProject(projectCode);
		Employee e = employeeDao.getEmployee(userName);
		t.setProject(p);
		t.setOwner(e);
		timesheetDao.addTimesheet(t);
	}

	public void setProjectdao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setEmployeedao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}
	
	public TimesheetDao getTimesheetdao() {
		return timesheetDao;
	}

	public void setTimesheetdao(TimesheetDao timesheetDao) {
		this.timesheetDao = timesheetDao;
	}

}
