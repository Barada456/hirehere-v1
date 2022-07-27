package com.centroxy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.centroxy.entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, String>{

	List<Notification> findByOrderByDateTimeDesc();
	
	@Query(value="select count(*) from notification where  `date_time` >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) ", nativeQuery = true)
	int countNoOfNotifications();
	

}
