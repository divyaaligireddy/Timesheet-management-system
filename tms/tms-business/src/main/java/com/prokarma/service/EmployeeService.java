package com.prokarma.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Timesheet;

public interface EmployeeService {

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public Employee validateUser(Employee user);

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public Employee getEmployeeById(int user_id);

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public List<Timesheet> viewTimesheet(int id, String username);

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public List<Employee> getEmployees();

	@Transactional(propagation = Propagation.NEVER, readOnly = true)
	public Employee getEmployee(String username);
}
