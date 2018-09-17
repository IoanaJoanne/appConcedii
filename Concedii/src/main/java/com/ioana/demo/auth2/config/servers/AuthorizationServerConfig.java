package com.ioana.demo.auth2.config.servers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

//THIS IS STEP1
@Configuration
@EnableAuthorizationServer


//password as authorizedGrantTypes is not implememnted by default by spring=> we have to give implementation
//=> STEP3
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	
	@Autowired
    private AuthenticationManager authenticationManager;//=> PART OF STEP 1- DEFINE THE AUTHENTICATION MANAGER BEAN
	//IN THE  SPRING SECURITY CLASS WHERE SPRING SECURITY CONFIGS ARE MADE
	
	@Autowired
    TokenStore tokenStore;
	
	@Autowired
    private PasswordEncoder passwordEncoder;//=> PART OF STEP 1- DEFINE THE PASSWORD ENCODER BEAN
	//IN THE  SPRING SECURITY CLASS WHERE SPRING SECURITY CONFIGS ARE MADE
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		  security
          .checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//aici arata ce date verifica sistemul
		

		clients
        .inMemory()
        .withClient("my-client")//the random value i give for client id/uername
        .authorizedGrantTypes("client_credentials", "password")
        //not random values here, they are reserved key words
        //client_credentials means credentials of the client used to obtain he access token- machine to machine authentication
        //ex credentials of Twitter used as a client: the client id and secret
        //password is for user verification: username and password: ex: username john and password kiki
        .authorities("ROLE_MY_CLIENT","ROLE_MY_SUPER_TRUSTED_CLIENT")
        //the .authorities here can be ommited- user is not limited by authority in this case- n-am useri de mai multe tipuri
        //authority value starts with the prefix ROLE_ followed by role value, authority string e scris cu uppercase
       // the values for roles i store them in a roles table in the database care e legata de tabela users pt a stabili relatia user-role
        //i check if the user has any of the following authority: MY_CLIENT and MY_TRUSTED_CLIENT (authotities to be checked :MY_CLIENT and MY_TRUSTED_CLIENT are random values)
        .scopes("read_my_status","write_my_status")
        //.scopes here can be ommited- client is not limited by scope in this case
        //scope is the client authority: ex Twitter as the client can post the users tweet on facebook; scope value could be write_facebook_status in this case
 //role: user has the authority to change its profile info, but Twitter as the client doesn't have the authority to do so
        //while role is the user authority
      //  .resourceIds("my-oauth2-resource")-- comentez asta pt ca in resource server config nu am setat resourceId
        //so .resourceId can be ommited
        //the random value i give for resource id
        .accessTokenValiditySeconds(5000)
        .secret(passwordEncoder.encode("mysecretvalue"));//the random value i give for client password to be checked
		//client id and password will be used for authentication (basic authentication) to obtain the token for a specific user in a post request
		//the client id/useername and secret/password will be the entered credentials for the basic authentication in the post request, the values of the form, the request body, not visible in the url
		//the user id and password-these params together with their value will be visible in the post request url, request made to obtain the token
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
        .authenticationManager(authenticationManager);
		 endpoints.tokenStore(tokenStore);
	}

	
}
