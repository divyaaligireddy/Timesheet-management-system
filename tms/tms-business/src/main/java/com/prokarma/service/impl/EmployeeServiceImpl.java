package com.prokarma.service.impl;

import java.util.List;

import com.prokarma.dao.EmployeeDao;
import com.prokarma.dao.exceptions.UserNotFoundException;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Timesheet;
import com.prokarma.service.EmployeeService;
import com.prokarma.service.business.exceptions.TmsBusinessException;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeedao;


	public List<Timesheet> viewTimesheet(int id, String username) {
		return (List<Timesheet>) employeedao.viewTimesheet(id,username);
	}

	public List<Employee> getEmployees() {
		return employeedao.getEmployees();
	}

	public Employee getEmployee(String username) {
		return employeedao.getEmployee(username);
	}

	public Employee validateUser(Employee employee) {
		Employee e = null;
		try {
			e = employeedao.validateUser(employee);
		} catch (final UserNotFoundException ex) {
			throw new TmsBusinessException("user with user name not found:"
					+ employee.getUsername(), ex);
		}

		return e;
	}

	public Employee getEmployeeById(int user_id) {
		return employeedao.getEmployeeById(user_id);
	}
	
	public EmployeeDao getEmployeedao() {
		return employeedao;
	}

	public void setEmployeedao(EmployeeDao employeedao) {
		this.employeedao = employeedao;
	}


}
