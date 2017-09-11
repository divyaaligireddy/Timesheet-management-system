package com.prokarma.dao;

import java.util.List;

import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Timesheet;

public interface EmployeeDao {

	public Employee validateUser(Employee user);

	public Employee getEmployeeById(int user_id);

	public List<Timesheet> viewTimesheet(int id, String username);

	public List<Employee> getEmployees();

	public Employee getEmployee(String username);

}
