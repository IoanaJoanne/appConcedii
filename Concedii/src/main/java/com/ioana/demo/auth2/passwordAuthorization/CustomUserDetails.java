package com.ioana.demo.auth2.passwordAuthorization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ioana.demo.auth2.DB.entities.Role;
import com.ioana.demo.auth2.DB.entities.User;

//THIS IS STEP 7
public class CustomUserDetails implements UserDetails{

	//se afla in legatura cu datele care sunt verificate la autorizarea userului prin mecanismul de password,
	//adica: user credentials: username and password si autoritatea userului
	//authorities asociate unui user: fiecare valoare individuala e formata din ROLE_ROLEVALUE,
	//ROLE_VALUE fiind defapt role din bd asociat userului=> legatura cu cls Role si User
	//username si password (user credentials) sunt cele stocate in bd pt user => legatura cu cls User
	
	//=> atributele cls CustomUserDetails vor fi:
	
	private String username;
	private String password;
	private Collection <? extends GrantedAuthority> authorities;
	
	
	public CustomUserDetails(User user)
	{//pe baza rolurilor din bd, ii stabilesc autotitatile
		//pe baza credentialelor din bd, ii stabilesc credentialele
			
		this.username = user.getUsername();
		this.password = user.getPassword();
		
		List<GrantedAuthority> auths = new ArrayList<>();
		for (Role role : user.getRoles())
		{
			
			auths.add(new SimpleGrantedAuthority(role.getName()));
		}
		this.authorities = auths;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.authorities;
	}

	@Override
	public String getPassword() {
		
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
}
