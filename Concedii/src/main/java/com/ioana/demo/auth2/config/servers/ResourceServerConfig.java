package com.ioana.demo.auth2.config.servers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//THIS IS STEP2

@Configuration
@EnableResourceServer

//here in this class you can also set the value the resourceId that the authorization server will check - you can ommit this
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	//here you restrict access to the urls with the authorizeRequests

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        .headers()
            .frameOptions()
            .disable()
            .and()
        .authorizeRequests()
        .antMatchers("/start").permitAll()
                   .anyRequest().authenticated();
	}
	

}
