package com.centroxy.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.centroxy.entities.ProjectDetails;

public interface ProjectRepository extends JpaRepository<ProjectDetails, Integer> {
	public Page<ProjectDetails> findAll(Pageable pageable);
	
}
