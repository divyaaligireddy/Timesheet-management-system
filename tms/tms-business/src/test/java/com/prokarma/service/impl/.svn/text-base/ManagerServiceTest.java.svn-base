package com.prokarma.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.prokarma.dao.ManagerDao;
import com.prokarma.dao.impl.ManagerDaoImpl;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Timesheet;

import com.prokarma.service.impl.ManagerServiceImpl;

public class ManagerServiceTest {
	private ManagerDao managerDao;
	private ManagerServiceImpl managerServiceImpl=new ManagerServiceImpl();
	private Employee employee=new Employee();
	private Project project=new Project();
	
	private List<Timesheet> timeList=new ArrayList<Timesheet>();
	private List<Employee> empList=new ArrayList<Employee>();
	
	@Test
	public void testAddEmployee()
	{
		
	
		employee.setUserId(100);
		employee.setUsername("nag");
		employee.setFirstname("nag");
		employee.setLastname("man");
		employee.setCountry("india");
		
		managerDao=Mockito.mock(ManagerDao.class);
		managerServiceImpl.setManagerdao(managerDao);
		//when
		managerServiceImpl.addEmployee(employee);
		//then
		Employee employee1=new Employee();
		employee1.setUserId(employee.getUserId());
		employee1.setUsername(employee.getUsername());
		employee1.setFirstname(employee.getFirstname());
		employee1.setLastname(employee.getLastname());
		employee1.setCountry(employee.getCountry());
		ArgumentCaptor<Employee> captor=ArgumentCaptor.forClass(Employee.class);
		Mockito.verify(managerDao).addEmployee(captor.capture());
		assertEquals(employee.getUserId(), 100);
	}
	
	
	@Test
	public void testEditEmployee()
	{
		
	
		employee.setUserId(100);
		employee.setUsername("nag");
		employee.setFirstname("nag");
		employee.setLastname("man");
		employee.setCountry("india");
		
		managerDao=Mockito.mock(ManagerDao.class);
		managerServiceImpl.setManagerdao(managerDao);
		//when
		managerServiceImpl.editEmployee(employee);
		//then
		Employee employee1=new Employee();
		employee1.setUserId(employee.getUserId());
		employee1.setUsername(employee.getUsername());
		employee1.setFirstname(employee.getFirstname());
		employee1.setLastname(employee.getLastname());
		employee1.setCountry(employee.getCountry());
		ArgumentCaptor<Employee> captor=ArgumentCaptor.forClass(Employee.class);
		Mockito.verify(managerDao).editEmployee(captor.capture());
		assertEquals(employee.getUserId(), 100);
	}
	
	
	
	
	@Test
	public void testAddProject()
	{
		
	
		project.setProjectId(1);
		project.setProjectName("tms");
		project.setCodename("time");
		project.setTechUsed("java");
		
		
		managerDao=Mockito.mock(ManagerDao.class);
		managerServiceImpl.setManagerdao(managerDao);
		//when
		managerServiceImpl.addProject(project);
		//then
		Project project1=new Project();
		project1.setProjectId(project.getProjectId());
		project1.setProjectName(project.getProjectName());
		project1.setCodename(project.getCodename());
		project1.setTechUsed(project.getTechUsed());
		
		ArgumentCaptor<Project> captor=ArgumentCaptor.forClass(Project.class);
		Mockito.verify(managerDao).addProject(captor.capture());
		assertEquals(project.getProjectId(), 1);
	}
	
	@Test
	public void testDeleteProject()
	{
		
	
		project.setProjectId(1);
		project.setProjectName("tms");
		project.setCodename("time");
		project.setTechUsed("java");
		
		
		managerDao=Mockito.mock(ManagerDao.class);
		managerServiceImpl.setManagerdao(managerDao);
		//when
		managerServiceImpl.editProject(project);
		//then
		Project project1=new Project();
		project1.setProjectId(project.getProjectId());
		project1.setProjectName(project.getProjectName());
		project1.setCodename(project.getCodename());
		project1.setTechUsed(project.getTechUsed());
		
		ArgumentCaptor<Project> captor=ArgumentCaptor.forClass(Project.class);
		Mockito.verify(managerDao).editProject(captor.capture());
		assertEquals(project.getProjectId(), 1);
	}
	
	
	
	
	@Test
	public void testDeleteEmployee()
	{
		
	
		employee.setUserId(100);
		employee.setUsername("nag");
		employee.setFirstname("nag");
		employee.setLastname("man");
		employee.setCountry("india");
		
		managerDao=Mockito.mock(ManagerDao.class);
		managerServiceImpl.setManagerdao(managerDao);
		//when
		managerServiceImpl.deleteEmployee(employee);
		//then
		Employee employee1=new Employee();
		employee1.setUserId(employee.getUserId());
		employee1.setUsername(employee.getUsername());
		employee1.setFirstname(employee.getFirstname());
		employee1.setLastname(employee.getLastname());
		employee1.setCountry(employee.getCountry());
		ArgumentCaptor<Employee> captor=ArgumentCaptor.forClass(Employee.class);
		Mockito.verify(managerDao).deleteEmployee(captor.capture());
		assertEquals(employee.getUserId(), 100);
	}
	
	
	@Test
	public void manageTimesheets()
	{
		Timesheet t=new Timesheet();
		t.setManagerId(1);
		managerDao=Mockito.mock(ManagerDaoImpl.class);
		managerServiceImpl.setManagerdao(managerDao);
		
		Mockito.when(managerDao.manageTimesheets(Matchers.anyInt())).thenReturn(timeList);
		Assert.assertEquals(timeList,managerServiceImpl.manageTimesheets(1));
	}
	
	
	
	@Test
	public void getEmployees()
	{
		managerDao=Mockito.mock(ManagerDaoImpl.class);
		managerServiceImpl.setManagerdao(managerDao);
		
		Mockito.when(managerDao.getEmployees()).thenReturn(empList);
		Assert.assertEquals(empList, managerServiceImpl.getEmployees());
	}
	
	
	
	
	
	
	
	

}
