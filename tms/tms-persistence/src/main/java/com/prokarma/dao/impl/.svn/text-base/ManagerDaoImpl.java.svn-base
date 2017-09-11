package com.prokarma.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prokarma.dao.ManagerDao;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;
import com.prokarma.domainmodel.Timesheet;

public class ManagerDaoImpl extends HibernateDaoSupport implements ManagerDao {

	public void addEmployee(Employee employee) {

		getHibernateTemplate().save(employee);
	}

	public void editEmployee(Employee employee) {

		getHibernateTemplate().saveOrUpdate(employee);
	}

	public void deleteEmployee(Employee employee) {

		getHibernateTemplate().delete(employee);
	}

	public void addProject(Project project) {

		getHibernateTemplate().save(project);

	}

	public void editProject(Project project) {

		getHibernateTemplate().saveOrUpdate(project);
	}

	@SuppressWarnings("unchecked")
	public List<Timesheet> manageTimesheets(int mgrId) {

		DetachedCriteria searchCriteria = DetachedCriteria
				.forClass(Timesheet.class);

		searchCriteria.add(Expression.eq("managerId", mgrId));
		searchCriteria.add(Expression.ne("status", "Declined"));
		searchCriteria.createCriteria("owner").add(
				Restrictions.ne("userId", mgrId));
		List<Timesheet> list = (List<Timesheet>) getHibernateTemplate()
				.findByCriteria(searchCriteria);

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {

		DetachedCriteria searchCriteria = DetachedCriteria
				.forClass(Employee.class);

		List<Employee> list = getHibernateTemplate().findByCriteria(
				searchCriteria);

		return list;

	}

	@SuppressWarnings("unchecked")
	public List<Project> getProjectDetails() {
		List<Project> list = new ArrayList<Project>();
		String query = "from Project";
		list = getHibernateTemplate().find(query);
		return list;
	}

	public void approveTimesheet(int timesheetId) {
		Timesheet timesheet = (Timesheet) getHibernateTemplate().get(
				Timesheet.class, timesheetId);
		timesheet.setStatus("Approved");
		getHibernateTemplate().update(timesheet);

	}

	public void declineTimesheet(int timesheetId) {
		Timesheet timesheet = (Timesheet) getHibernateTemplate().get(
				Timesheet.class, timesheetId);
		timesheet.setStatus("Declined");
		getHibernateTemplate().update(timesheet);

	}

}
