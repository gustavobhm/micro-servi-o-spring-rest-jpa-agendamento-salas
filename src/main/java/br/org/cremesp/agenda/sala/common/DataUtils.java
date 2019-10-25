package br.org.cremesp.agenda.sala.common;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;

public final class DataUtils {

	private DataUtils() {
	}

	public static Date newDateWithFormat(String stringData) {
		try {
			return DateUtils.parseDate(stringData, Locale.ENGLISH, "yyyy-MM-dd");
		} catch (ParseException e) {
			return null;
		}

	}

}
