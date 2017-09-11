package com.prokarma.model.converters;

import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Role;
import com.prokarma.domainmodel.Timesheet;
import com.prokarma.webmodel.EmployeeUIModel;
import com.prokarma.webmodel.ProjectUIModel;
import com.prokarma.webmodel.TimesheetUIModel;

public class TMSFactory {

	public static EmployeeUIModel getEmployeeUIModel(Employee in){
		EmployeeUIModel emp = new EmployeeUIModel();
		emp.setCountry(in.getCountry());
		emp.setDoj(in.getDoj());
		emp.setEmail(in.getEmail());
		emp.setFirstname(in.getFirstname());
		emp.setLastname(in.getLastname());
		emp.setPassword(in.getPassword());
		emp.setPhoneNumber(in.getPhoneNumber());
		emp.setRole(in.getRole().name());
		emp.setUsername(in.getUsername());
		return emp;
	}
	public static Employee getEmployee(EmployeeUIModel in){
		Employee emp = new Employee();
		emp.setCountry(in.getCountry());
		emp.setDoj(in.getDoj());
		emp.setEmail(in.getEmail());
		emp.setFirstname(in.getFirstname());
		emp.setLastname(in.getLastname());
		emp.setPassword(in.getPassword());
		emp.setPhoneNumber(in.getPhoneNumber());
		emp.setRole(Role.valueOf(in.getRole()));
		emp.setUsername(in.getUsername());
		return emp;
	}
	public static ProjectUIModel getProjectUIModel(Project proj){
		ProjectUIModel projUIModel = new ProjectUIModel();
		projUIModel.setCodename(proj.getCodename());
		projUIModel.setDescription(proj.getDescription());
		projUIModel.setEnd_Date(proj.getEnd_Date());
		projUIModel.setStart_Date(proj.getStart_Date());
		projUIModel.setProjectName(proj.getProjectName());
		projUIModel.setStatus(proj.getStatus());
		projUIModel.setTechUsed(proj.getTechUsed());
		projUIModel.setManagerId(proj.getManagerId());
		projUIModel.setAssignedEmployees(proj.getEmployeeNames());
		return projUIModel;
		
	}
	public static Project getProject(ProjectUIModel projUIModel){
		Project proj = new Project();
		proj.setCodename(projUIModel.getCodename());
		proj.setTechUsed(projUIModel.getTechUsed());
		proj.setDescription(projUIModel.getDescription());
		proj.setEnd_Date(projUIModel.getEnd_Date());
		proj.setStart_Date(projUIModel.getStart_Date());
		proj.setProjectName(projUIModel.getProjectName());
		proj.setStatus(projUIModel.getStatus());
		proj.setManagerId(projUIModel.getManagerId());
		return proj;
		
	}
	public static TimesheetUIModel getTimesheetUIModel(Timesheet in){
		TimesheetUIModel t = new TimesheetUIModel();
		t.setBegin_Date(in.getBegin_Date());
		t.setEnd_Date(in.getEnd_Date());
		t.setDescription(in.getDescription());
		t.setManagerId(in.getManagerId());
		t.setMonday(in.getMonday());
		t.setTuesday(in.getTuesday());
		t.setWednesday(in.getWednesday());
		t.setThursday(in.getThursday());
		t.setFriday(in.getFriday());
		t.setSaturday(in.getSaturday());
		t.setSunday(in.getSunday());
		t.setStatus(in.getStatus());
		t.setTotalHours(in.getTotalHours());
		return t;

	}
	public static Timesheet getTimesheet(TimesheetUIModel in){
		Timesheet t = new Timesheet();
		t.setBegin_Date(in.getBegin_Date());
		t.setEnd_Date(in.getEnd_Date());
		t.setDescription(in.getDescription());
		t.setManagerId(in.getManagerId());
		t.setMonday(in.getMonday());
		t.setTuesday(in.getTuesday());
		t.setWednesday(in.getWednesday());
		t.setThursday(in.getThursday());
		t.setFriday(in.getFriday());
		t.setSaturday(in.getSaturday());
		t.setSunday(in.getSunday());
		t.setStatus(in.getStatus());
		t.setTotalHours(in.getTotalHours());
		return t;

		
	}
	public static EmployeeUIModelToEmployeeConverter getEmployeeUIModelToEmployeeConverter(){
		return new EmployeeUIModelToEmployeeConverter();
	}
	public static EmployeeToEmployeeUIModelConverter getEmployeeToEmployeeUIModelConverter(){
		return new EmployeeToEmployeeUIModelConverter();
	}
	public static ProjectUIModelToProjectConverter getProjectUIModelToProjectConverter(){
		return new ProjectUIModelToProjectConverter();
	}
	public static ProjectToProjectUIModelConverter getProjectToProjectUIModelConverter(){
		return new ProjectToProjectUIModelConverter();
	}
	public static TimesheetUIModelToTimesheetConverter getTimesheetUIModelToTimesheetConverter(){
		return new TimesheetUIModelToTimesheetConverter();
	}
	public static TimesheetToTimesheetUIModelConveter gerTimesheetToTimesheetUIModelConveter(){
		return new TimesheetToTimesheetUIModelConveter();
	}
}
