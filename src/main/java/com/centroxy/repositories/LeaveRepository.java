package com.centroxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroxy.entities.Leave;

public interface LeaveRepository extends  JpaRepository<Leave, Integer>{

}