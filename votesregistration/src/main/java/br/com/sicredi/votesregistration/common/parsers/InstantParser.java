package br.com.sicredi.votesregistration.common.parsers;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class InstantParser {

	public static Instant toInstant(final String timeStr) {
		if (timeStr == null) {
			return Calendar.getInstance().toInstant();
		}
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
	    OffsetDateTime dateTime = OffsetDateTime.parse(timeStr, formatter);
	    return dateTime.toInstant();
	}
}
