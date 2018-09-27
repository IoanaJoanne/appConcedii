package com.ioana.demo.DB.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "teams")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	@OneToOne (cascade = CascadeType.PERSIST)
	@JoinColumn (name = "project_id", nullable = false)
	private Project project;
	
	@OneToOne (cascade = CascadeType.PERSIST)
	@JoinColumn (name = "team_leader_id")
	private Employee team_leader;
	
	public Team(String name, Project project, Employee team_leader) {
		super();
		this.name = name;
		this.project = project;
		this. team_leader = team_leader;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Employee getTeam_leader() {
		return team_leader;
	}
	public void setTeam_leader(Employee team_leader) {
		this.team_leader = team_leader;
	}
	
	
	
	
}
