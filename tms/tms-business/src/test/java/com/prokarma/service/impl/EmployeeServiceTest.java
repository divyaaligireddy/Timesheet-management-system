package com.prokarma.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.prokarma.dao.EmployeeDao;
import com.prokarma.dao.impl.EmployeeDaoImpl;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Timesheet;

public class EmployeeServiceTest {
	private EmployeeDao employeeDao;
	private EmployeeServiceImpl project=new EmployeeServiceImpl();
	private List<Timesheet> time=new ArrayList<Timesheet>();
	private List<Employee> emp=new ArrayList<Employee>();
	private Employee employee=new Employee();
	
	@Before
	public void setUp() {
		employee.setUsername("uname");
	}
	
	@Test
	public void testForViewTimesheet()
	{
		employee.setUserId(1);
		employeeDao=Mockito.mock(EmployeeDaoImpl.class);
		project.setEmployeedao(employeeDao);
		Mockito.when(employeeDao.viewTimesheet(Matchers.anyInt(),Matchers.anyString())).thenReturn(time);
		Assert.assertEquals(time, project.viewTimesheet(employee.getUserId(),employee.getUsername()));
	}
	
	
	
	@Test
	public void testGetEmployee()
	{
		employee.setUsername("nag");
		employeeDao=Mockito.mock(EmployeeDaoImpl.class);
		project.setEmployeedao(employeeDao);
		Mockito.when(employeeDao.getEmployee(Matchers.anyString())).thenReturn(employee);
		Assert.assertEquals(employee, project.getEmployee(employee.getUsername()));
	}
	
	
	
	@Test
	public void testGetEmployees()
	{
		employeeDao=Mockito.mock(EmployeeDaoImpl.class);
		project.setEmployeedao(employeeDao);
		Mockito.when(employeeDao.getEmployees()).thenReturn(emp);
		Assert.assertEquals(emp, project.getEmployees());
	}
	
	@Test
	public void testGetEmployeeById()
	{
		employeeDao=Mockito.mock(EmployeeDaoImpl.class);
		project.setEmployeedao(employeeDao);
		Mockito.when(employeeDao.getEmployeeById(Matchers.anyInt())).thenReturn(employee);
		Assert.assertEquals(employee, project.getEmployeeById(employee.getUserId()));
	}
	
	@Test
	public void validateUser()
	{
		employeeDao=Mockito.mock(EmployeeDaoImpl.class);
		project.setEmployeedao(employeeDao);
		Mockito.when(employeeDao.validateUser(employee)).thenReturn(employee);
		Assert.assertEquals(project.validateUser(employee),employee);
	}
	
	
	

}
