package com.prokarma.domainmodel;

import java.io.Serializable;
import java.util.Date;

public class Timesheet extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	private int timesheetId;
	private Project project;
	private Employee owner;
	private int managerId;
	private Date begin_Date;
	private Date end_Date;
	private String description;
	private int sunday;
	private int monday;
	private int tuesday;
	private int wednesday;
	private int thursday;
	private int friday;
	private int saturday;
	private int totalHours;
	private String status;

	public int getTimesheetId() {
		return timesheetId;
	}

	public void setTimesheetId(int timesheetId) {
		this.timesheetId = timesheetId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public Date getBegin_Date() {
		return begin_Date;
	}

	public void setBegin_Date(Date begin_Date) {
		this.begin_Date = begin_Date;
	}

	public Date getEnd_Date() {
		return end_Date;
	}

	public void setEnd_Date(Date end_Date) {
		this.end_Date = end_Date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSunday() {
		return sunday;
	}

	public void setSunday(int sunday) {
		this.sunday = sunday;
	}

	public int getMonday() {
		return monday;
	}

	public void setMonday(int monday) {
		this.monday = monday;
	}

	public int getTuesday() {
		return tuesday;
	}

	public void setTuesday(int tuesday) {
		this.tuesday = tuesday;
	}

	public int getWednesday() {
		return wednesday;
	}

	public void setWednesday(int wednesday) {
		this.wednesday = wednesday;
	}

	public int getThursday() {
		return thursday;
	}

	public void setThursday(int thursday) {
		this.thursday = thursday;
	}

	public int getFriday() {
		return friday;
	}

	public void setFriday(int friday) {
		this.friday = friday;
	}

	public int getSaturday() {
		return saturday;
	}

	public void setSaturday(int saturday) {
		this.saturday = saturday;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public boolean equals(final Object other) {
		
		if(other == null) {
			return false;
		}
		
		if (!(other instanceof Timesheet)) {
			return false;
		}

		Timesheet t1 = (Timesheet) other;

		if (this.owner.getFirstname().equals(t1.getOwner().getFirstname())
				&& this.project.getCodename().equals(
						t1.getProject().getCodename()) 
						&& t1.getBegin_Date().equals(this.getBegin_Date()) 
						&& t1.getEnd_Date().equals(this.getEnd_Date())) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 32 * this.owner.getFirstname().hashCode()
				+ this.project.getCodename().hashCode()
				+ this.getBegin_Date().hashCode()
				+ this.getEnd_Date().hashCode();
	}
}
