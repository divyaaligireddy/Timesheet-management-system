package com.prokarma.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prokarma.dao.ProjectDao;
import com.prokarma.domainmodel.Employee;
import com.prokarma.domainmodel.Project;

public class ProjectDaoImpl extends HibernateDaoSupport implements ProjectDao {

	public void deleteProject(Project project) {

		try {
			Query timesheetDelete = getSession().createQuery(
					"delete Timesheet t where t.project.projectId="
							+ project.getProjectId());

			timesheetDelete.executeUpdate();

			Query qr = getSession().createQuery(
					"delete Project p where p.projectId="
							+ project.getProjectId());

			qr.executeUpdate();

		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getEmployees(Project project) {

		List<String> assgndemp = new ArrayList<String>();
		Criteria cr = getSession().createCriteria(Employee.class);
		cr.add(Restrictions.eq("projectId", project.getProjectId()));
		List<Employee> list = cr.list();
		for (Employee emp : list) {
			assgndemp.add(emp.getUsername());
		}
		return assgndemp;
	}

	@SuppressWarnings("unchecked")
	public Project getProject(String codename) {

		Criteria cr = getSession().createCriteria(Project.class);
		cr.add(Restrictions.eq("codename", codename));
		List<Project> projects = cr.list();

		return projects.get(0);
	}
	
	public String getProjectCode(int projectId) {
		Project project = (Project) getHibernateTemplate().get(Project.class,
				projectId);

		return project.getCodename();
	}


	public void updateProject(final Project p) {
		getHibernateTemplate().update(p);
	}

}
