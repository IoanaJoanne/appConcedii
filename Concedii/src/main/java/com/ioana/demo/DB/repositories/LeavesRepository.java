package com.ioana.demo.DB.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ioana.demo.DB.entities.LeaveRequest;

@Repository ("leavesRepo")
// sau (value = "leavesRepo")
public interface LeavesRepository extends JpaRepository<LeaveRequest, Long> {
List<LeaveRequest> findAll();

}
