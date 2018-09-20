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

	/**
	 * 
	 */
	private static final long serialVersionUID = 2526893992938111348L;
	//se afla in legatura cu datele care sunt verificate la autorizarea userului prin mecanismul de password,
	//adica: user credentials: username and password si autoritatea userului
	//authorities asociate unui user
	//custom user details preia de la cls role doar atributele care sunt strict folosite de spring security, nu e interesat de alte atribute precum (date of birth, age, email etc) care pot aparea in cls user
	//=> atributele cls CustomUserDetails vor fi:
	
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public CustomUserDetails(User user)
	{//pe baza rolurilor din bd, ii stabilesc autotitatile
		//pe baza credentialelor din bd, ii stabilesc credentialele
			
		this.username = user.getUsername();
		this.password = user.getPassword();
		
		//dc cumva in bd nu erau trecute cu masjuscule, le transform acuma
			String name = user.getRole().getName();
			name = name.toUpperCase();
			//dc cumva in bd nu am trecut rolurile sa inceapa cu ROLE_, le transform acum
			 if (!name.startsWith("ROLE_"))
	                name = "ROLE_" + name;
			
			//am facut transformarile pt ca 
			//role.getName tre sa fie de  forma ROLE_MY_ClIENT_TYPE_1 (argumentul constructorului tre intotdeauna sa fie uppercase si sa inceapa cu  prefix ROLE_)
	
			 List<SimpleGrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(name));
		this.authorities = auths;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// trebuie sa ma ca atribut al cls o lista de autoritati ciar dc un user eu am stabilt sa aiba un sg rol pt ca colectia de autoritati - metoda get o mosteneste din user details
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

	@Override
	public String toString() {
		return "CustomUserDetails [username=" + username + ", password=" + password + ", authorities=" + authorities
				+ "]";
	}
	
}
