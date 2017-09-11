package com.prokarma.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.prokarma.dao.TimesheetDao;
import com.prokarma.domainmodel.Timesheet;

public class TimesheetDaoImpl extends HibernateDaoSupport implements
		TimesheetDao {

	public void addTimesheet(Timesheet t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

}
