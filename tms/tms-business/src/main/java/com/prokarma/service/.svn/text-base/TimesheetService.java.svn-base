package com.prokarma.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.domainmodel.Timesheet;

public interface TimesheetService {

	@Transactional(propagation = Propagation.REQUIRED)
	public void addTimesheet(Timesheet t, String userName, String projectCode);
}
