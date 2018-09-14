package com.ioana.demo.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	//beanurile astea nu le pot configura in orice configuartion class pt ca 
	//doar asta exitinde web security si mosteneste metodele authenticationmanagerbean si userdetails service
	//deci pot apela super.authenticationmanagerbean etc
	
	//dc in cadrul aceleaisi cls de configurat beanuri  am autowired un element(sau am un constructor care are ca parametru elem respectiv) si acelasi element e definit ca bean- imi da eroare la spring
	//Requested bean is currently in creation: Is there an unresolvable circular reference?

	
	
	@Bean
    
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	//THIS IS ALSO PART OF STEP 1- WITHOUT THIS THE APP GIVES A BUILD ERROR AND FAILS TO START
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        PasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder;
	    }
	 @Bean
	 public UserDetailsService userDetailsService() {
	     return super.userDetailsService();
	 }
	
	
	 
	 //STEP 10- define the rules for urls
	 @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http
	            .authorizeRequests()
	           
	                .antMatchers("/oauth/token").permitAll()
	                //aici pun doar chestia cu token
	                .anyRequest().authenticated()
	                .and()
	            .httpBasic()
	            
	                .and()
	               
	            .csrf().disable();
	 }
	
}
