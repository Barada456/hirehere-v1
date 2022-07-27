package com.centroxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroxy.entities.Interview;

public interface InterviewRepository extends JpaRepository <Interview,String> {
	List<Interview> findByPanelId(String id);
	//@Query("SELECT i FROM  Interview i  WHERE  i.status=?1 and  (SELECT id  FROM InterviewerPanel p WHERE p.id= ?2) ")
	//List<Interview> findByStatusAndPanel(String scheduled, String interviewerPanel);
}
