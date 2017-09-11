package com.prokarma.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Timesheet;

public class TimesheetRowMapper implements
		org.springframework.jdbc.core.RowMapper {
	public Timesheet mapRow(ResultSet rs, int rowNum) throws SQLException {
		Timesheet time = new Timesheet();
		Employee employee = new Employee();
		employee.setUserId(rs.getInt(1));
		employee.setUsername(rs.getString(2));
		time.setOwner(employee);
		time.setBegin_Date(rs.getDate(3));
		time.setEnd_Date(rs.getDate(4));
		time.setStatus(rs.getString(5));
		return time;

	}

}
