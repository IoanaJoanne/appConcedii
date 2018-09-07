package com.ioana.demo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ioana.demo.models.Employee;
import com.ioana.demo.models.LeaveRequest;
import com.ioana.demo.models.LeaveStatus;
import com.ioana.demo.models.LeaveType;

@RestController
public class FirstRESTController {

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

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String login() {
		return "home page";
	}

}
