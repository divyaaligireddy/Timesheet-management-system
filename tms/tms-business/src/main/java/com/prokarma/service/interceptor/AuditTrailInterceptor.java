package com.prokarma.service.interceptor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.prokarma.service.UserContext;

public class AuditTrailInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		setValue(currentState, propertyNames, "last_updated_by",
				UserContext.getUserName());
		setValue(currentState, propertyNames, "last_updated_date", new Date());
		return true;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		setValue(state, propertyNames, "created_by", UserContext.getUserName());
		setValue(state, propertyNames, "date_created", new Date());
		setValue(state, propertyNames, "last_updated_by",
				UserContext.getUserName());
		setValue(state, propertyNames, "last_updated_date", new Date());

		return true;
	}

	private void setValue(Object[] currentState, String[] propertyNames,
			String propertyToSet, Object value) {
		int index = new ArrayList<String>(Arrays.asList(propertyNames))
				.indexOf(propertyToSet);
		if (index >= 0) {
			currentState[index] = value;
		}
	}
}