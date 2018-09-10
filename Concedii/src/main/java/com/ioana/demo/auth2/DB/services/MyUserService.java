package com.ioana.demo.auth2.DB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ioana.demo.auth2.DB.entities.User;
import com.ioana.demo.auth2.DB.repositories.UserRepository;


//THIS IS STEP 9
@Service
public class MyUserService {

	@Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //parola nu e salvata in clar ci e salvata codificat
        repo.save(user);
    }

	public User findByUsername(String username) {
		
		return repo.findByUsername(username);
	}
}
