package com.centroxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroxy.entities.JobDescription;

public interface JDRepository extends JpaRepository<JobDescription, String> {
	
	List<JobDescription> findAllByStatus(String Rejected);


}
