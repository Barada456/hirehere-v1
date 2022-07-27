package com.centroxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.centroxy.entities.Holiday;


public interface HolidayRepository extends JpaRepository<Holiday, Integer> {

}
