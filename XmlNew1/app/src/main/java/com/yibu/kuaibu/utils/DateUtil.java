package com.yibu.kuaibu.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd";

	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
	private static SimpleDateFormat sdf1 = new SimpleDateFormat(
			SIMPLE_DATE_PATTERN);

	/**
	 * 返回yyyy-MM-dd HH:mm:ss SimpleDateFormat
	 * @return
	 */
	public static SimpleDateFormat getSimpleDateFormat() {
		return sdf;
	}

	/**
	 * 返回当前时间 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentTime() {
		return sdf.format(new Date());
	}

	/**
	 * 返回当前时间 yyyy-MM-dd HH:mm:ss
	 * @param currentTime
	 * @return
	 */
	public static String getTimeByCurrentTime(long currentTime) {

		return sdf.format(new Date(currentTime));
	}

	/**
	 * 返回当前时间 yyyy-MM-dd
	 * @param currentTime
	 * @return
	 */
	public static String getTimeToDayByCurrentTime(long currentTime) {

		return sdf1.format(new Date(currentTime));
	}

}
