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
        .antMatchers("/test","/newUser",  "/",  "/h2/**", "/js/**", "/css/**", "/favicon.ico").permitAll()
        
       //test si newUser e rest api ; ce nu e rest api: / care e pt view(sa-mi para index page in browser), h2 pt bd si celelalte pt cu js, css si favocon pt a accesa resursele statice
       
       //pt url ul componentelor care fac parte din pg de index din view nu mi trebuie sa dau permit, merg automat, pt ca parent-ul din care fac parte adica pg de index e permisa ca am setat /
       /* cand bag / url ul mi se incarca pg index.html pt ca spring boot automat o considera welcome page*/
                   .anyRequest().authenticated();
		
	}
	
	
	   
}
