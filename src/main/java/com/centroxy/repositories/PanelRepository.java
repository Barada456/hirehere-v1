package com.centroxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroxy.entities.InterviewerPanel;

public interface PanelRepository  extends JpaRepository<InterviewerPanel, String>{
	
}
