package com.ioana.demo.DB.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table (name = "departments")
public class Department {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@OneToOne (cascade = CascadeType.PERSIST)
	@JoinColumn (name = "manager_id", nullable = false)
	private Employee manager;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public Department(String name, Employee manager) {
		super();
		this.name = name;
		this.manager = manager;
	}
	
	
}
