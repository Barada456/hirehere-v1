package com.centroxy.entities;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;


import org.springframework.stereotype.Component;


@Component
public class DurationCalculator {
    
	/*
	 * @Autowired static LocalDateTimeConverter dateTimeConverter;
	 */
    
    public static String calculate(Timestamp date) {
    	LocalDateTimeConverter dateTimeConverter=new LocalDateTimeConverter();
    	
		
		LocalDateTime createdDate = dateTimeConverter.convertToEntityAttribute(date);
		LocalDateTime formattedCreatedDate = LocalDateTime.parse(createdDate.toString().replace(" ", "T"));

        LocalDateTime from = formattedCreatedDate;
		
		LocalDateTime to = LocalDateTime.now();

        Duration duration = Duration.between(from, to);
        
        if(duration.getSeconds()>=86400) {
        	return ""+duration.toDays() + " days ago";
        }
        else if(duration.getSeconds()>=3600) {
        	return ""+duration.toHours() + " hours ago";
        }
        else if(duration.getSeconds()>=60) {
        	return ""+duration.toMinutes() + " minutes ago";
        }
        else {
        	return "Just Now";
        }
}}