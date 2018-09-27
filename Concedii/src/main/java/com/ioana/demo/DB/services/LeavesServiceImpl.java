package com.ioana.demo.DB.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ioana.demo.DB.entities.LeaveRequest;

import com.ioana.demo.DB.repositories.LeavesRepository;


@Service ("leavesService")
public class LeavesServiceImpl implements LeavesService{

	
	@Autowired
	private LeavesRepository leavesRepo;
	
	
	@Override
	public List<LeaveRequest> findAll() {
		return leavesRepo.findAll();
		
	
	}

}
