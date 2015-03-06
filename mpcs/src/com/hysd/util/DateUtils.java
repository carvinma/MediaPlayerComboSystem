package com.hysd.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**时间工具类*/
public class DateUtils {
	

	/**
	 * 将字符形式的时间，转换为Date对象
	 * @param str
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String str, String pattern) {
		java.text.DateFormat format = new java.text.SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
		}
		return date;
	}

	/**
	 * 格式化 date
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateFormat(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat dateFm = new SimpleDateFormat(pattern); // 格式化当前系统日期
		return dateFm.format(date);
	}

	/**
	 * 将时间增加指定分钟数，minute负数为减
	 * 
	 * @param date
	 *            时间
	 * @param addMinute
	 *            要增加的分钟值
	 */
	public static Date dateAddMinute(Date date, int minute) {
		if (date == null) {
			return null;
		}
		return new Date(date.getTime() + minute * 60 * 1000);
	}

	/**
	 * 得到指定日期的一天的开始时刻00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDate(Date date) {
		String temp = dateFormat(date, "yyyy-MM-dd");
		temp += " 00:00:00";

		try {
			return parseDate(temp, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 得到指定日期的一天的结束时刻
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndDate(Date date) {
		return new Date(getStartDate(date).getTime()+24*60*60*1000-1);
	}
	
	/**
	 * 得到一年的结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndYear(Date date) {
		if(date==null){
			return null;
		}
		 // 加减日期所用   
		GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(getStartYear(date));
		gc.add(GregorianCalendar.YEAR, 1);
		return new Date(gc.getTimeInMillis()-1);

	}
	
	/**
	 * 得到一年的开始时间
	 * @param date
	 * @return
	 */
	public static Date getStartYear(Date date){
		if(date==null){
			return null;
		}
		 // 获得当前日期   
		String temp = dateFormat(date, "yyyy");
		temp += "-01-01 00:00:00";

		try {
			return parseDate(temp, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			return date;
		}
		
	}
	
	
	/**
	 * 得到一个月的开始时间
	 * @param date
	 * @return
	 */
	public static Date getStartMonth(Date date){
		if(date==null){
			return null;
		}
		 // 获得当前日期   
		String temp = dateFormat(date, "yyyy-MM");
		temp += "-01 00:00:00";

		try {
			return parseDate(temp, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			return date;
		}
	
	}
	
	/**
	 * 得到指定日期的当前月的结束时刻
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndMonth(Date date) {
		if(date==null){
			return null;
		}
		 // 加减日期所用   
		GregorianCalendar gc = new GregorianCalendar(); 
		gc.setTime(getStartMonth(date));
		gc.add(GregorianCalendar.MONTH, 1);
		return new Date(gc.getTimeInMillis()-1);

	}

	
	/**
	 * 得打一周的开始时间
	 * @param date
	 * @return
	 */
	public static Date getStarWeek(Date date){

		Calendar   calendar   =   Calendar.getInstance();   //获取日历 

		calendar.setTimeInMillis(date.getTime());
		calendar.setFirstDayOfWeek(2);
		  //获取是一年的第几周 
        calendar.set(Calendar.DAY_OF_WEEK,   Calendar.MONDAY);   //将日历翻到这周的周一 
       
        return getStartDate(calendar.getTime());

	}
	
	/**
	 * 得到一周的结束时间
	 * @param date
	 * @return
	 */
	public static Date getEndWeek(Date date){

		Calendar   calendar   =   Calendar.getInstance();   //获取日历 

		calendar.setTimeInMillis(date.getTime());
		calendar.setFirstDayOfWeek(2);
		
        calendar.add(Calendar.WEEK_OF_YEAR,   1);   //使用add进行增减操作，在“一年的第几周”这一属性在当前值的基础上+1，也就是下一周，上一周则是-1 
        calendar.set(Calendar.DAY_OF_WEEK,   Calendar.MONDAY);//将日历翻到这周的周一 

        return new Date(getStartDate(calendar.getTime()).getTime()-1);
	}
	
	public static void main(String args[]){
		Date d=parseDate("24:00", "HH:mm");
		System.out.println(dateFormat(d));
	}
	
	public static String dateFormat(Object date){
		return dateFormat((Date)date,"yyyy-MM-dd HH:mm:ss");
	}
	
	public static int getWeekOfYear(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date); //月份需要注意下，是0~11，所以需要减1
		cal.setFirstDayOfWeek(2);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
}
