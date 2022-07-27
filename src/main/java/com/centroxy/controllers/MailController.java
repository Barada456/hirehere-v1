package com.centroxy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.centroxy.entities.Mail;
import com.centroxy.services.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	Mail mail;

	@Autowired
	MailService mailService;
	
	@PostMapping("/sendMail")
	public ResponseEntity<String> sendMail(@RequestParam String email) {
		//System.out.println(email);

		mail.setMailFrom("automatehiring@gmail.com");
		mail.setMailTo(email);
		mail.setMailSubject("Welcome");
		mail.setMailContent("Welcome to HireHere");

		mailService.sendEmail(mail);
		return ResponseEntity.ok("mail sent successfully");
	}

}
