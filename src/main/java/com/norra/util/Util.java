package com.norra.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.springframework.http.HttpStatus;
import com.norra.constants.Constants;
import com.norra.enums.AppErrorCodes;
import com.norra.exceptions.CustomAppException;
import com.norra.model.response.AppError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {


	/**
	 * @param scheduleDate
	 * @return
	 */
	public static Date getDateWithoutSeconds(Date scheduleDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(scheduleDate);
		calendar.set(calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date newDate = calendar.getTime();
		return newDate;
	}

	/**
	 * Adds the day to date.
	 *
	 * @param date     the date
	 * @param noOfDays the no of days
	 * @return the date
	 */
	public static Date addDayToDate(Date date, int noOfDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, noOfDays);
		calendar.set(calendar.SECOND, 0);
		Date newDate = calendar.getTime();
		return newDate;
	}

	/**
	 * Adds the minute to date.
	 *
	 * @param date    the date
	 * @param minutes the minutes
	 * @return the date
	 */
	public static Date addMinuteToDate(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MINUTE, minutes);
		calendar.set(calendar.SECOND, 0);
		Date newDate = calendar.getTime();
		return newDate;
	}

	/**
	 * Gets the decimal formated value.
	 *
	 * @param value  the value
	 * @param format the format
	 * @return the decimal formated value
	 */
	public static Double getDecimalFormatedValue(double value, String format) {
		String formatedString = new DecimalFormat(format).format(value);
		return Double.parseDouble(formatedString);
	}

	/**
	 * Gets the decimal formated value.
	 *
	 * @param value  the value
	 * @param format the format
	 * @return the decimal formatted value
	 */
	public static Float getDecimalFormattedValueForFloat(Float value, String format) {
		if(value !=null && format !=null) {
		String formatedString = new DecimalFormat(format).format(value);
		return Float.valueOf(formatedString);
		}else {
			return 0F;
		}
	}

	/**
	 * Gets the end date time of day.
	 *
	 * @param date the date
	 * @return the end of day
	 */
	public static Date getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * Gets the start date time of day.
	 *
	 * @param date the date
	 * @return the start of day
	 */
	public static Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Gets the start date of month.
	 *
	 * @param date the date
	 * @return the start of month
	 */
	public static Date getStartOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * Gets the end date of month.
	 *
	 * @param date the date
	 * @return the end of month
	 */
	public static Date getEndOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}


	/**
	 * Get the day of week from date.
	 *
	 * @param date the date
	 * @return the day of week
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}

	/**
	 * Add the seconds to date.
	 *
	 * @param date    the date
	 * @param seconds the seconds
	 * @return the date
	 */
	public static Date addSecondsToDate(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.SECOND, seconds);
		Date newDate = calendar.getTime();
		return newDate;
	}

	/**
	 * Get the month from date.
	 *
	 * @param date the date
	 * @return the month from date
	 */
	public static int getMonthFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		return month;
	}

	/**
	 * No of days from date range.
	 *
	 * @param startDate the start date
	 * @param endDate   the end date
	 * @return the long
	 */
	public static long getTimeDifferenceInDays(Date startDate, Date endDate) {
		long diff = endDate.getTime() - startDate.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}


	/**
	 * This method is used to return singular/plural words based on count
	 *
	 * @param count    type of Int
	 * @param singular type of String
	 * @return singular/plural words as per count
	 */
	public static String singlePlural(int count, String singular) {
		return count == 1 ? singular : singular.concat("s");
	}

	/**
	 * @param rideDate : ride date of type Date
	 * @param format   : format of type string, to which the date has to be
	 *                 converted
	 * @return returns the formatted date
	 */
	public static String getDateInIST(Date date, String format) {
		if (date != null && format != null) {
			String formatted = null;
			DateFormat formatter = new SimpleDateFormat(format);
			try {
				formatter.setTimeZone(TimeZone.getTimeZone(Constants.TIMEZONE_IST));
				formatted = formatter.format(date);
			} catch (Exception e) {
				log.error("Error formatting date " + e);
			}
			return formatted;
		}
		return null;
	}

	public static Date getOnlyDateFromObject(Date date) {
		try {
			String strDate = Constants.DAY_WITHOUT_TIME_FORMAT.format(date);
			date = Constants.DAY_WITHOUT_TIME_FORMAT.parse(strDate);
		} catch (ParseException e) {
		}
		return date;

	}

}
