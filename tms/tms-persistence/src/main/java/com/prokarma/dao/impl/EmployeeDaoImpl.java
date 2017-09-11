package com.prokarma.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prokarma.dao.EmployeeDao;
import com.prokarma.dao.exceptions.UserNotFoundException;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Timesheet;

public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	public Employee validateUser(Employee user) {
		DetachedCriteria searchCriteria = DetachedCriteria
				.forClass(Employee.class);
		searchCriteria.add(Expression.eq("username", user.getUsername()));
		searchCriteria.add(Expression.eq("password", user.getPassword()));
		List<Employee> values = (List<Employee>) getHibernateTemplate()
				.findByCriteria(searchCriteria);

		if (values.isEmpty()) {
			throw new UserNotFoundException("Invalid credentials");
		}

		return values.get(0);
	}

	public Employee getEmployeeById(int user_id) {
		Employee emp = new Employee();

		emp = (Employee) getHibernateTemplate().get(Employee.class, user_id);

		return emp;
	}

	@SuppressWarnings("unchecked")
	public List<Timesheet> viewTimesheet(int id, String username) {
		String sql = "select empid,created_by,begin_date,end_date,status from tms_timesheet where empid=? and created_by=?";
		List<Timesheet> timesheet = (List<Timesheet>) getJdbcTemplate().query(
				sql, new Object[] { id, username }, new TimesheetRowMapper());
		System.out.println(timesheet.size());
		return timesheet;

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {

		List<Employee> list = new ArrayList<Employee>();
		String query = "from Employee";
		list = getHibernateTemplate().find(query);
		return list;

	}

	@SuppressWarnings("unchecked")
	public Employee getEmployee(String username) {
		Employee employee = new Employee();
		DetachedCriteria searchCriteria = DetachedCriteria
				.forClass(Employee.class);
		searchCriteria.add(Expression.eq("username", username));
		List<Employee> values = (List<Employee>) getHibernateTemplate()
				.findByCriteria(searchCriteria);

		for (Employee emp : values) {
			employee = emp;
		}

		return employee;
	}

	public void setDataSource(DataSource dataSource) {
		/**
		 * FIXME: Inject this through spring. do not create using new.
		 */
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
}
