package com.ioana.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyMVCController {
	// spring MVC
		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public String displayRegisterForm() {

			return "register";
		}

		@RequestMapping(value = "/CompanyCalendar", method = RequestMethod.GET)
		public String displayFrontPage() {

			return "CompanyCalendar";
		}

}
