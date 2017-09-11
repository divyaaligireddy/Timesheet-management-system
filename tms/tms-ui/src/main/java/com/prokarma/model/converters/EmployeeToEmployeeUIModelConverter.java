package com.prokarma.model.converters;

import com.prokarma.domainmodel.Employee;
import com.prokarma.webmodel.EmployeeUIModel;

public class EmployeeToEmployeeUIModelConverter implements
		Converter<Employee, EmployeeUIModel> {

	public EmployeeUIModel convert(Employee in) {
		return TMSFactory.getEmployeeUIModel(in);

	}

}
