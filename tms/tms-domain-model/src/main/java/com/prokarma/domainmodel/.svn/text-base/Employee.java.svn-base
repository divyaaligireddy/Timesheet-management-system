package com.prokarma.domainmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Employee extends Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String username;
	private String firstname;
	private String lastname;
	private int projectId;
	private Role role;
	private String country;
	private Date doj;
	private String password;
	private String email;
	private String phoneNumber;
	
	private Set<Timesheet> myTimesheets = new HashSet<Timesheet>();

	public Set<Timesheet> getMyTimesheets() {
		return myTimesheets;
	}

	public void setMyTimesheets(Set<Timesheet> myTimesheets) {
		this.myTimesheets = myTimesheets;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/*public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
*/
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void addNewTimesheet(final Timesheet timesheet) {
		this.myTimesheets.add(timesheet);
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}	

	@Override
	public boolean equals(final Object other) {
		if(other == null) {
			return false;
		}
		
		if (!(other instanceof Employee)) {
			return false;
		}

		Employee e = (Employee) other;

		if (this.username.equals(e.getUsername())) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 32 * this.username.hashCode();
	}
}
