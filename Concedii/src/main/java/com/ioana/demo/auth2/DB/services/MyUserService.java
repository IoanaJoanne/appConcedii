package com.ioana.demo.auth2.DB.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ioana.demo.auth2.DB.entities.Role;
import com.ioana.demo.auth2.DB.entities.User;
import com.ioana.demo.auth2.DB.repositories.MyRoleRepository;
import com.ioana.demo.auth2.DB.repositories.MyUserRepository;


//THIS IS STEP 9
@Service
public class MyUserService {

	@Autowired
    private MyUserRepository user_repo;
	
	@Autowired
    private MyRoleRepository role_repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user){
    	
    	Role role = null;
    	if (role_repo.count() !=0 )
    	{
    		role  = role_repo.findByName("ROLE_EMPLOYEE");
    		
    	}
    	else {
    		
    		role  = new Role("ROLE_EMPLOYEE");
    	}
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        //parola nu e salvata in clar ci e salvata codificat
        user_repo.save(user);
    }

	public User findByUsername(String username) {
		
		return user_repo.findByUsername(username);
	}
}
