package com.prokarma.model.converters;

import com.prokarma.domainmodel.Employee;
import com.prokarma.webmodel.EmployeeUIModel;

public class EmployeeUIModelToEmployeeConverter implements
		Converter<EmployeeUIModel, Employee> {

	public Employee convert(EmployeeUIModel in) {
		return TMSFactory.getEmployee(in);
	}

}
