package com.ioana.demo.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ioana.demo.models.Employee;
import com.ioana.demo.models.LeaveRequest;
import com.ioana.demo.models.LeaveStatus;
import com.ioana.demo.models.LeaveType;

@RestController
public class MyRESTController {

	@Autowired
    private TokenStore tokenStore;
	
	public static List<LeaveRequest> lista;
	static {
		lista = new ArrayList<>();

		Employee e1 = new Employee("kiki");
		Employee e2 = new Employee("miki");

		LeaveRequest l1 = new LeaveRequest(LeaveType.holiday, new Date("02/09/2018"), new Date("12/09/2018"),
				LeaveStatus.approved, e1);
		LeaveRequest l2 = new LeaveRequest(LeaveType.sick_leave, new Date("03/09/2018"), new Date("05/09/2018"),
				LeaveStatus.approved, e2);

		lista.add(l1);
		lista.add(l2);

	}

	

	// spring REST
	@RequestMapping(value = "/companyLeaves", method = RequestMethod.GET)
	public List<LeaveRequest> showLeavesCompanyWide() {
		
		return lista;

	}

	@RequestMapping(value = "/account/employee/{username}", method = RequestMethod.GET)
	public String employeeAccPanel(@PathVariable("username") String username) {
		return "Welcome employee " + username + " !";
	}

	@RequestMapping(value = "/account/manager/{username}", method = RequestMethod.GET)
	public String managerAccPanel(@PathVariable("username") String username) {
		return "Welcome manager " + username + " !";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test message: this rest url is not secured with security mechanism";
	}
	@RequestMapping(value = "/notSignedIn", method = RequestMethod.GET)
	public String displayMessage() {
		return "You need to authenticate first to access the content of this page.";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String signoutOfAcc(HttpServletRequest req, HttpServletResponse res) {
		
		//tokenu tre sters si din backend din tokenStore
	//	 tokenStore.removeAccessToken(tokenStore.readAccessToken("accessToken"));
		
		
	//	verific dc mi intra pe cazul /login?logout, care ma intereseaza, vreau sa verific dc parametrul logout exista pe request si este singurul parametru
//si nu are nicio valoare- e un string empty
		System.out.println(req.getParameterMap().containsKey("logout"));
		System.out.println(req.getParameterMap().size());
		System.out.println(req.getParameterMap().get("logout")[0]);
	if (req.getParameterMap().containsKey("logout") && req.getParameterMap().size() ==1 && req.getParameterMap().get("logout")[0] == "")
	
		return "You have been successfully logged out of your account";
	
	else return ""; //return an empty String, but not a null value ca sa nu crape back-endu cu NullPointerException
	}
	
	@RequestMapping(value = "/retrieveCurrentUser", method = RequestMethod.GET)
	public String showUser(Principal user, Authentication auth) {
//principal in spring security is the current logged in user
	//alternativa la  Principal sau Authentication
		System.out.println("cu security context username-ul este: "+SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("tot cu security context sau cu authentication.getPrincipal(), returneaza o implementare a userDetails, adica un obiect de tip CustomUserDetails: "+SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
//SecurityContextHolder.getContext().getAuthentication() returneaza tot un obiect de tip Authentication
			
		System.out.println("with Principal username is: "+user.getName());
		//authentication e alternativa la principal
	//	System.out.println("with authentication credentials are: "+auth.getCredentials().toString());-- not working, nu afiseaza nimic
		System.out.println("with authentication username is: "+auth.getName());
		System.out.println("with authentiation authorities are: "+auth.getAuthorities());
		return user.getName();
	}

}
