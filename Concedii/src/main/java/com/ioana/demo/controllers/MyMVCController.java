package com.ioana.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyMVCController {
	// spring MVC

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayHomePage() {

		return "homePage";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String displayRegisterForm() {

		return "signinPage";
	}
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String displayLogoutPage() {

		return "signoutPage";
	}

	@RequestMapping(value = "/companyCalendar", method = RequestMethod.GET)
	public String displayCompanyCalendarPage() {

		return "CompanyCalendar";
	}

}
