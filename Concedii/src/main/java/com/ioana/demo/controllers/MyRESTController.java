package com.ioana.demo.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

<<<<<<< HEAD
import org.springframework.security.core.context.SecurityContextHolder;
=======
import org.springframework.security.core.Authentication;
>>>>>>> 0b4e5637a59408d376c8f62da9c125a76ddc6a64
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
	public List<LeaveRequest> showLeavesCompanyWide(Principal user, Authentication auth) {
		System.out.println(user.getName());
		System.out.println(auth.getCredentials());
		System.out.println(auth.getName());
		System.out.println(auth.getAuthorities());
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
	@RequestMapping(value = "/retrieveCurrentUser", method = RequestMethod.GET)
	public String showUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
				
	}

}
