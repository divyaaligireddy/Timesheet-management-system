package com.prokarma.hibernate.custom.usertypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import com.prokarma.domainmodel.Role;

public class EmployeeType implements UserType {


	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	/* "default" implementations */

    @SuppressWarnings("rawtypes")
	public Class returnedClass() { return Enum.class; }
    
    public boolean equals(Object x, Object y) { return x == y; }
   
    public Object deepCopy(Object value) { return value; }
    
    
    public boolean isMutable() { return false; }
    
	public int hashCode(Object x) throws HibernateException {
		assert (x != null);
		return x.hashCode();
	}


	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		return rs.wasNull() ? null : Role.valueOf(value);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
        } else {
        	st.setString(index, value.toString());
        }
	}

}
