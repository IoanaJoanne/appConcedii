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
        .antMatchers("/test", "/notSignedIn", "/signin", "/register", "/CompanyCalendar").permitAll()
        //view-urile populate cu date din rest api, adica url urile mvc nu s restrictionate
        //pt ca rest api-urile apelate de ele sunt restrictionate: ori afiseaza datele din rest api ori afiseaza mesajul tre sa te loghezi
        //singurul rest api nerestrictionat e cel de test si notSignedIn
        //aici am pus toate url urile rest si mvc  care vreau sa mi fie permise
       
                   .anyRequest().authenticated();
	}
	

}
