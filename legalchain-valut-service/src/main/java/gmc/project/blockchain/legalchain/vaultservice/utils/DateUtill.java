package gmc.project.blockchain.legalchain.vaultservice.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtill {
	
	private DateUtill() {}
	
	public static LocalDate parse(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dateString, formatter);
		return date;
	}
	
	public static LocalDateTime parseDT(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm:ss");
		LocalDateTime date = LocalDateTime.parse(dateString, formatter);
		return date;
	}

}
