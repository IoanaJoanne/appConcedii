package com.ioana.demo.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	 	@Override
	 	public void configure(WebSecurity web) throws Exception {
	 		web.ignoring()
	 		// Spring Security should completely ignore URLs starting with /resources/
	 				.antMatchers("/start");
	 	}

	 	@Override
	 	protected void configure(HttpSecurity http) throws Exception {
	 		http.authorizeRequests()
	 		.antMatchers("/account/employee/**").hasRole("USER").and()
	 				
	 				.formLogin();
	 		http.authorizeRequests()
	 		.antMatchers("/companyLeaves").hasRole("USER").and()
	 				
	 				.formLogin();
	 		http.authorizeRequests()
	 		.antMatchers("/companyLeaves").hasRole("ADMIN").and()
	 				
	 				.formLogin();
	 		http.authorizeRequests()
	 		.antMatchers("/account/manager/**").hasRole("ADMIN").and()
	 				
	 				.formLogin();
	 	}

	 	@Override
	 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 		PasswordEncoder encoder =
	 			     PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 		
	 		// enable in memory based authentication with a user named "user" and "admin"
	 		auth.inMemoryAuthentication().withUser("myemployee").password(encoder.encode("ioana")).roles("USER")
	 				.and().withUser("mymanager").password(encoder.encode("ioana")).roles("ADMIN");
	 	}



}
