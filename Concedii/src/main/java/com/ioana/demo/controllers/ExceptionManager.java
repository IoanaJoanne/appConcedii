package com.ioana.demo.controllers;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionManager {

	@ExceptionHandler (value = InternalAuthenticationServiceException.class)
	public String userAuthenticationError()
	{
		
		return "error in Authenticating the user";
	}
}
