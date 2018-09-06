package com.ioana.demo.models;

import java.util.Date;

public class LeaveRequest {

	private LeaveType type;
	private Date startDate;
	private Date endDate;
	private LeaveStatus status;
	private Employee employee;
	public LeaveRequest(LeaveType type, Date startDate, Date endDate, LeaveStatus status, Employee employee) {
		super();
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.employee = employee;
	}
	public LeaveType getType() {
		return type;
	}
	public void setType(LeaveType type) {
		this.type = type;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public LeaveStatus getStatus() {
		return status;
	}
	public void setStatus(LeaveStatus status) {
		this.status = status;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
}
