package com.ioana.demo.DB.services;

import java.util.List;

import com.ioana.demo.DB.entities.LeaveRequest;

public interface LeavesService {
	List<LeaveRequest> findAll();
}
