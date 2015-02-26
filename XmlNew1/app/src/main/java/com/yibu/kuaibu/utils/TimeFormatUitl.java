package com.yibu.kuaibu.utils;

import java.io.File;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFormatUitl {

	// ʱ�����
	//
	// 1.1�������ڣ�50��ǰ
	//
	// 2.1Сʱ���ڣ�50����ǰ
	//
	// 3.24Сʱ���ڣ�3Сʱǰ��3Сʱ��4Сʱ֮��ȡ3Сʱ��
	//
	// 4.24Сʱ֮ǰ��ֱ����ʾ���ڣ�1-16

	/**
	 * ��ȷ�����ӡ�Сʱ ��ʽ��ʱ�� �磺12Сʱǰ
	 * 
	 * @param timestr
	 *            ��
	 */
	public static String formatTimeInDetail(String timestr) {
		String timeText = null;
		if (timestr == null)
			return "";
		long time = new Long(timestr);
		Date dt = new Date();
		long nowSec = dt.getTime() / 1000;
		long timediff = nowSec - time;
		if (timediff < 60) {
			// С��1������ʾ ���ոա�
			timeText = timediff + "��ǰ";
			if (timediff <= 0) {
				timeText = 2 + "��ǰ";
			}
		} else if (timediff >= 60 && timediff < 60 * 60) {
			// С��1Сʱ ��ʾ�����ӡ�
			timeText = String.valueOf((int) timediff / 60) + "����ǰ";
		} else if (timediff >= 60 * 60 && timediff < 24 * 60 * 60) {
			// С��24Сʱ������ʾ��ʱ��
			timeText = String.valueOf((int) timediff / (60 * 60)) + "Сʱǰ";
		} else {
			timeText = getPostsTime(timestr);
		}

		return timeText;

	}

	/**
	 * ���һ�����ڣ����������ڼ����ַ�
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// ��ת��Ϊʱ��
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour�д�ľ������ڼ��ˣ��䷶Χ 1~7
		// 1=������ 7=��������������
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * ����ʱ���ʽ�ַ�ת��Ϊʱ�� yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static String getFileTimeName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuffer sb = new StringBuffer();
		sb.append(YhdConstant.IMGDIR);
		sb.append("IMG");
		sb.append("_");

		sb.append(sdf.format(Calendar.getInstance().getTime()));
		sb.append(".jpg");
		File dir = new File(YhdConstant.DIR);
		if (!dir.exists()) {
			dir.mkdir();
		}
		File dirFile = new File(YhdConstant.IMGDIR);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}
		return sb.toString();

	}

	// ��������ʱ��ת��
	public static String formatTimeInPostsDetail(String timestr) {
		String timeText = "";
		if (timestr == null)
			return "";
		long time = new Long(timestr);
		Calendar calendar = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String re_StrTime = sdf.format(new Date(time * 1000L));
		if (sdf.format(calendar.getTime()).equals(re_StrTime)) {
			timeText = "����" + getPostsDitalTime2(timestr);
			return timeText;
		}
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		if (sdf.format(calendar.getTime()).equals(re_StrTime)) {
			timeText = "����" + getPostsDitalTime2(timestr);
			return timeText;
		}
		timeText = getPostsDitalTime(timestr);

		/*
		 * if(timediff<0 && timediff>-24*60*60){ timeText =
		 * getPostsDitalTime(timestr); }
		 */
		return timeText;
	}

	/**
	 * ת��ʱ���ʽ
	 * 
	 * @param timestr
	 *            ��
	 * @return
	 */
	public static String getPostsTime(String timestr) {
		long time = new Long(timestr);
		Date date = new Date(time * 1000);
		SimpleDateFormat createTimeSdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat createTimeSdf2 = new SimpleDateFormat("MM-dd");
		String str = createTimeSdf1.format(date);
		String str2 = createTimeSdf2.format(date);
		int nowyear = Integer.valueOf(createTimeSdf1.format(new Date())
				.substring(0, 4));

		String stryear = str.substring(0, 4);
		int intyear = Integer.parseInt(stryear);

		if (nowyear > intyear) {
			return str;
		} else {
			return str2;
		}
	}

	public static String getPostsDitalTime(String timestr) {
		long time = new Long(timestr);
		Date date = new Date(time * 1000);
		SimpleDateFormat createTimeSdf1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		SimpleDateFormat createTimeSdf2 = new SimpleDateFormat("MM-dd HH:mm");
		// SimpleDateFormat createTimeSdf3 = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm");
		String str = createTimeSdf1.format(date);
		String str2 = createTimeSdf2.format(date);
		// String str3 = createTimeSdf2.format(date);
		int nowyear = Integer.valueOf(createTimeSdf1.format(new Date())
				.substring(0, 4));

		String stryear = str.substring(0, 4);
		int intyear = Integer.parseInt(stryear);

		if (nowyear > intyear) {
			return str;
		} else {
			return str2;
		}

	}

	public static String getPostsDitalTime2(String timestr) {
		long time = new Long(timestr);
		Date date = new Date(time * 1000);
		SimpleDateFormat createTimeSdf1 = new SimpleDateFormat("HH:mm");
		String str = createTimeSdf1.format(date);
		return str;
	}

	/**
	 * ��ʽ��ʱ�� �磺12Сʱǰ
	 * 
	 * @param timestr
	 *            ��
	 */
	public static String fmttoCN(String timestr) {
		String timeText = null;
		if (null == timestr || "".equals(timestr)) {
			return "";
		}
		long time = new Long(timestr);
		Date dt = new Date();
		long nowSec = dt.getTime() / 1000;
		long timediff = nowSec - time;
		if (timediff < 60) {
			// С��1������ʾ ���ոա�
			timeText = "�ո�";
		} else if (timediff >= 60 && timediff < 60 * 60) {
			// С��1Сʱ ��ʾ�����ӡ�
			timeText = String.valueOf((int) timediff / 60) + "����ǰ";
		} else if (timediff >= 60 * 60 && timediff < 24 * 60 * 60) {
			// С��24Сʱ������ʾ��ʱ��
			timeText = String.valueOf((int) timediff / (60 * 60)) + "Сʱǰ";
		} else if (timediff >= 24 * 60 * 60 && timediff < 30 * 24 * 60 * 60) {
			// С��1���£�����ʾ���졯
			timeText = String.valueOf((int) timediff / (24 * 60 * 60)) + "��ǰ";
		} else if (timediff >= 30 * 24 * 60 * 60
				&& timediff < 12 * 30 * 24 * 60 * 60) {
			// С��1�꣬����ʾ���¡�
			timeText = String.valueOf((int) timediff / (30 * 24 * 60 * 60))
					+ "����ǰ";
		} else if (timediff >= 12 * 30 * 24 * 60 * 60) {
			// ����1����ʾ���ꡯ
			timeText = String
					.valueOf((int) timediff / (12 * 30 * 24 * 60 * 60)) + "��ǰ";
		}

		return timeText;

	}

	/**
	 * ��ʽ������ʱ��
	 * 
	 * @param ms
	 * @return
	 */
	public static String fmtMusciTime(int ms) {
		ms /= 1000; // ��
		int minute = ms / 60; // ��
		int second = ms % 60; // ��������Է�֮�󣬻�ʣ������
		return String.format("%02d:%02d", minute, second);
	}

	/**
	 * ����2��ʱ��� ��ȷ����
	 * 
	 * @return
	 */
	public static long difftime(String startTime, String endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long differ_day = 0;
		try {

			Date start = sdf.parse(startTime);
			Date end = sdf.parse(endTime);
			float temp = ((float) end.getTime() - start.getTime()) / 1000 / 60
					/ 60 / 24;
			if (temp > 0 && temp < 1) {
				differ_day = 1;
			} else if (temp >= 1) {
				differ_day = Math.round(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return differ_day;
	}

	public static String formatTime(String mills) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(1000 * new Long(mills)));
	}

	public static long formatTimetoMill(String timestr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long mills = 0;
		try {
			mills = sdf.parse(timestr).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mills;
	}

	/*
	 * ת������
	 */
	public static long formatTimetoSeconds(String timestr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long seconds = 0;
		try {
			seconds = sdf.parse(timestr).getTime() / 1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seconds;
	}



	/**
	 * dataת��string
	 */
	public static String getTimestr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/*
	 * ���뻻��YYYY-MM-dd
	 */
	public static String getTimestr(long seconds) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(seconds * 1000));
	}



	/**
	 * @param �ַ�ת����Date
	 * @return
	 */
	public static Date formatdate(String timestr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(timestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;

	}

	/**
	 * 
	 * @param 2012-11-8 ת����2012��11��8��
	 * @return
	 */
	public static String formatToChinese(String timestr) {
		String str = "";
		if (timestr.indexOf("-") != -1) {
			String[] s = timestr.split("-");
			if (s.length == 3) {
				str = s[0] + "��" + s[1] + "��" + s[2] + "��";
			}
		}
		return str;

	}

	/**
	 * 
	 * @param 2012-11-8 ת����2012��11��8��
	 * @return
	 */
	public static String formatToChineseMonth(String timestr) {

		String year = timestr.substring(0, 4);
		if (Integer.valueOf(year) < 2011) {

			return year + "��";
		}
		String str = "";
		if (timestr.indexOf("-") != -1) {
			String[] s = timestr.split("-");
			if (s.length == 3) {
				str = s[0] + "��" + s[1] + "��";
			}
		}
		return str;

	}


	
	
	
}
