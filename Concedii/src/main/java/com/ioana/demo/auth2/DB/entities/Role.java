package com.ioana.demo.auth2.DB.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//THIS IS STEP 6
@Entity
public class Role implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	public Role()
	{
		
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
