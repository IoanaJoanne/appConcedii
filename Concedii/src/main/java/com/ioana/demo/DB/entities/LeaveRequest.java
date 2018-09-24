package com.ioana.demo.DB.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "leaves")
public class LeaveRequest {
	@Id
	@GeneratedValue
	private Long id;
	private String type;//holiday, sick leave etc
	private Date startDate;
	private Date endDate;
	private String status;//approved, pending, cancelled
	
	
	
	
	
	public LeaveRequest(String type, Date startDate, Date endDate, String status) {
		super();
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		
	}
	
	
	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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
	
	
	
	
	
}
