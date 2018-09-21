package com.ioana.demo.auth2.DB.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//THIS IS STEP 5
@Entity
@Table (name = "Users")
public class User {
	// aveam implements serializable dc vroiam ca ob sa fie serializate intr-un ob de tip json
   //in cazul in care in rest api in requets body primeam ob de tip json
	
	//cele 2 tabele modeleaza relatia user-role dintre tabelele din baza de date
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	
	// fetch = FetchType.EAGER, cascade = CascadeType.ALL pt atributul colectiei 
	
	
	@ManyToOne(cascade = CascadeType.PERSIST) //cascade was necessary here, otherwise error
	@JoinColumn (name = "role_id") //specifies the fk inside the users table- the name of the column
	//the name of the column doesn't have to correspond with the name of the id attribute inside the role class 
	private Role role; //=> THIS IS STEP 6
	
    public User()
    {
    		
    }


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
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

	

	public User(String username, String password, Role role) {
		super();
		
		this.username = username;
		this.password = password;
		this. role = role;
		
	}


	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	
    
}
