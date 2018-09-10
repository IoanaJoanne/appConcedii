package com.ioana.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ioana.demo.auth2.DB.entities.Role;
import com.ioana.demo.auth2.DB.entities.User;
import com.ioana.demo.auth2.DB.repositories.UserRepository;
import com.ioana.demo.auth2.DB.services.MyUserService;
import com.ioana.demo.auth2.passwordAuthorization.CustomUserDetails;


@SpringBootApplication
public class ConcediiApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(ConcediiApplication.class, args);
	}
	
	//THIS IS STEP3=> umblam la AuthenticationManager injectat deja din auth2
	//FOR PASSWORD AUTHORIZATION
	//i mai adaugam o implementare si pt password- pt a verfica credentialele userului
	//pt credentialele clientului adica client_credentials spring deja ofera o implementare
	
	
	
	//STEP8- this default user is added to the h2 db (in memory stored db)
	@Bean
    public CommandLineRunner setupDefaultUser(MyUserService service) {
		//user service apeleaza UserRepository cu metoda save in db => STEP 9 add the USER SERVICE CLASS
        return args -> {
            service.save(new User(
                    "user2", //username
                    "ioana", //password
Arrays.asList(new Role("ROLE_MY_CLIENT"), new Role("ROLE_MY_CLIENT_TYPE_2"))
            ));
        };
        
      
    }
	  @Autowired
  	public void AuthenticationManager(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {
	
  		//=> STEP 4: add user repo interface implemented from jpa- find the user by username
  		//=> STEP 7: add the custom user details class: CustomUserDetails
  		builder.userDetailsService(new UserDetailsService()
  				{
  				
  				

  				@Override
  				public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  					return (UserDetails) new CustomUserDetails (repo.findByUsername(username));
  				}
  				
  				});
  		
  		
  		
  	}
	
	
	
}
