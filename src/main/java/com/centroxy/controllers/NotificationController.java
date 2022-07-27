package com.centroxy.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.centroxy.entities.Notification;
import com.centroxy.repositories.NotificationRepository;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	NotificationRepository notificationRepository;

	@GetMapping("/details")
	public Object notificationDetails(@RequestParam String id) {


		Notification notification = notificationRepository.findById(id).get();
		notification.setIsRead(true);
		
		notificationRepository.save(notification);
		

		if (notification.getProjectDetails()!=null) {
			
			return notification.getProjectDetails();
		} else if (notification.getJd()!=null) {
			return notification.getJd();
		}

		else {
			return notification.getEmp();
		}

	}

}
