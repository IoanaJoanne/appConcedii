package com.ioana.demo.auth2.DB.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//THIS IS STEP 5
@Entity
public class User implements Serializable{
	//cele 2 tabele modeleaza relatia user-role dintre tabelele din baza de date
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//fara fetch si cascade nu merge
	//a user can have multiple roles
	//a specific role is assigned to only one user
	private List<Role> roles; //=> THIS IS STEP 6
	
    public User()
    {
    		
    }


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User(String username, String password, List<Role> roles) {
		super();
		
		this.username = username;
		this.password = password;
		this.roles = roles;
	}


	
    
}
