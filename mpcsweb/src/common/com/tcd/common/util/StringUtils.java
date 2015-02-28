package com.tcd.common.util;

/**
 * 字符串工具类，显示通话时间使用
 * 
 * @author 郑卫东
 * 
 */
public class StringUtils {

	static public void main(String args[]) {
		System.out.println(showTime(60 * 60 * 24));
	}

	/**
	 * 将秒数格式化
	 * 
	 * @param timeStr
	 * @param level
	 * @return
	 */
	public static String showTime(Integer time) {
		TimeStr timestr = new TimeStr(time, "");
		for (int i = 3; i < 7; i++) {
			timestr = showTime2(timestr, i);
		}
		return timestr.str;
	}

	private static TimeStr showTime2(TimeStr timeStr, int level) {
		Integer time = timeStr.time;
		int value = getLeveLValue(level);

		if (value == 0) {
			timeStr.str = timeStr.str + time + getLeveLName(level);
			return timeStr;
		}
		if (time >= value) {
			timeStr.str = timeStr.str + time / value + getLeveLName(level);
			timeStr.time = new Integer(time % value);
		} else {
			if (timeStr.str.length() != 0) {
				timeStr.str = timeStr.str + 0 + getLeveLName(level);
			}
		}

		return timeStr;
	}

	private static int getLeveLValue(int level) {
		switch (level) {
		case 3:
			return 60 * 60 * 24;
		case 4:
			return 60 * 60;
		case 5:
			return 60;
		case 6:
			return 0;
		}
		return 1;
	}

	private static String getLeveLName(int level) {
		switch (level) {
		case 3:
			return "天";
		case 4:
			return "小时";
		case 5:
			return "分钟";
		case 6:
			return "秒";
		}
		return null;
	}

	/**
	 * 判断 from<=value<=to
	 * @param value
	 * @param from
	 * @param to
	 * @return boolean
	 */
	public static boolean betweenAnd(String value, String from, String to) {
		if (value.compareTo(from) >= 0 && value.compareTo(to) <=0)
			return true;
		return false;
	}

}

class TimeStr {
	int time;
	String str;

	public TimeStr(int time, String str) {
		this.time = time;
		this.str = str;
	}
}
