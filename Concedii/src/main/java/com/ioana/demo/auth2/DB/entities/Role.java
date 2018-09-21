package com.ioana.demo.auth2.DB.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//THIS IS STEP 6
@Entity
@Table (name = "Roles")
public class Role {
	// aveam implements serializable dc vroiam ca ob sa fie serializate intr-un ob de tip json
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	//cascade was not necessary here but i added it anyway
	//role is the name of the attribute in the other class
	private List <User> users;//the mapped by property here, together with the many-to-one annotation in the other class, make this one-to-many relationship bidirectional
	
	public Role()
	{
		users  = new ArrayList<>();// empty arrraylist
		
	}

	public Role(String name) {
		super();
		this.name = name;
		
	}



	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
