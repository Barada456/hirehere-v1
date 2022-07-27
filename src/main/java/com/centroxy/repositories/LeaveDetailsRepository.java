package com.centroxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroxy.entities.LeaveDetails;

public interface LeaveDetailsRepository extends JpaRepository<LeaveDetails, Integer>{
	

}
