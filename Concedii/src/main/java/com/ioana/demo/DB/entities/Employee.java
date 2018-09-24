package com.ioana.demo.DB.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "employees")
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String job_title;
	
	//these 2 above are many-to-one relationships
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn (name = "department_id", nullable = false)
	private Department department; 
	
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn (name = "team_id") //by default pune nullable true dc omit
	private Team team;
	
	
	//this is a unidirectional one-to-many relationship
	@OneToMany (cascade = CascadeType.ALL)
	//mai pun si join column ca sa nu mi faca inca un tabel in plus ca la one-to-many
	@JoinColumn (name = "employee_id")
	private List<LeaveRequest> leaves;


	public Employee(String name, String job_title, Department department, Team team, List<LeaveRequest> leaves) {
		super();
		this.name = name;
		this.job_title = job_title;
		this.department = department;
		this.team = team;
		this.leaves = leaves;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LeaveRequest> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<LeaveRequest> leaves) {
		this.leaves = leaves;
	}
	
	

}
