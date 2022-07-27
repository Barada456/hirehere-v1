package com.centroxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centroxy.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
