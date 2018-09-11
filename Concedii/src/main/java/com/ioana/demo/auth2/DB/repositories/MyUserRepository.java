package com.ioana.demo.auth2.DB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ioana.demo.auth2.DB.entities.User;

//this is STEP 4

@Repository
public interface MyUserRepository extends JpaRepository<User, Long>{
	//Long pt ca id-ul de la user va fi de tip Long atributul
	// => STEP 5 create User class annotated with JPA- will be in the database
	
	//din JpaRepsoitory mosteneste metode precum save
	//metoda specifica acestei interfete, care nu e overriden din JpaRepository e findByUsername
	User findByUsername(String username);//now we can find a user based on its username

}
