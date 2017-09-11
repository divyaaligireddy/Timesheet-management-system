package com.prokarma.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.prokarma.dao.EmployeeDao;
import com.prokarma.dao.ProjectDao;
import com.prokarma.dao.TimesheetDao;
import com.prokarma.domainmodel.Timesheet;
import com.prokarma.service.impl.TimesheetServiceImpl;

public class TimesheetServiceTest {
	private TimesheetDao timesheetDao;
	private ProjectDao projectDao;
	private EmployeeDao employeeDao;
	private TimesheetServiceImpl timesheetService = new TimesheetServiceImpl();
	private Timesheet time = new Timesheet();

	@Test
	public void testAddTimesheet() {

		time.setTimesheetId(1);
		time.setDescription("tms");
		time.setStatus("pending");
		time.setMonday(2);

		timesheetDao=Mockito.mock(TimesheetDao.class);
		projectDao = Mockito.mock(ProjectDao.class);
		employeeDao=Mockito.mock(EmployeeDao.class);
		timesheetService.setTimesheetdao(timesheetDao);
		timesheetService.setProjectdao(projectDao);
		timesheetService.setEmployeedao(employeeDao);
		// when
		timesheetService.addTimesheet(time, "nag", "nag");
		// then
		Timesheet time1 = new Timesheet();
		time1.setTimesheetId(time.getTimesheetId());
		time1.setDescription(time.getDescription());
		time1.setStatus(time.getStatus());
		time1.setMonday(time.getMonday());

		ArgumentCaptor<Timesheet> captor = ArgumentCaptor
				.forClass(Timesheet.class);
		Mockito.verify(timesheetDao).addTimesheet(captor.capture());
		assertEquals(time.getTimesheetId(), 1);
	}

}