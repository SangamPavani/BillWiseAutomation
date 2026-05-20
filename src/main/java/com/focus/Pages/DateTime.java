package com.focus.Pages;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateTime {

	public static void main(String[] args) {
	
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat todayDate = new SimpleDateFormat("dd/MM/yyyy");
		
		String currentDate = todayDate.format(cal.getTime());
		LocalTime currentTime = LocalTime.now();
		        
		        System.out.println("Current Date: " + currentDate);
		        System.out.println("Current Time: " + currentTime);
		        System.out.println("Current Time: " + cal.getTime());

	}

}
