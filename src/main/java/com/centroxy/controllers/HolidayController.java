package com.centroxy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.centroxy.entities.Holiday;
import com.centroxy.repositories.HolidayRepository;


@Controller
@RequestMapping("/holiday")
public class HolidayController {
	@Autowired
	private HolidayRepository holidayRepo;
	
	@PostMapping("/addHoliday")
	public ResponseEntity<String> addHoliday(@ModelAttribute Holiday holiday) {
		holidayRepo.save(holiday);
		return ResponseEntity.ok("Holiday Added");
	}
}
