package com.ioana.demo.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

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
	
	 @Bean
	 public TokenStore tokenStore() {
	     return new InMemoryTokenStore();//all access tokens are lost if authorization server is restarted when u use in-memory token store
//in memory token store is preferred during development but not in production
//alternative: user jdbc token store so the tokens are persisted and stored in a real database, so u don't lose them when authorization server is restarted
//To use the JdbcTokenStore you need "spring-jdbc" on the classpath- the spring jdbc maven dependency
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
