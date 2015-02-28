package com.tcd.common.util;

public class DistanceUtil {

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}
	/**
	 * 返回两个坐标是否在距离内
	 * @param lon1 经度
	 * @param lat1 纬度
	 * @param lon2 经度
	 * @param lat2 纬度
	 * @param distance 两个坐标的距离
	 * @return
	 */
	public static boolean getDistance(double lon1, double lat1, double lon2,
			double lat2,double distance) {
		return getDistance2(lon1, lat1, lon2, lat2)>distance;
	}
	/**
	 * 返回两个坐标的距离
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return
	 */
	public static double getDistance2(double lon1, double lat1, double lon2,
			double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lon1) - rad(lon2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		//s = Math.round(s * 10000) / 10000;
		return s;
	}
	public static void main(String[] args) {
		//近侧: 270.69 km  远侧: 39804.35
	//	double distance1 = getDistance2(117.0333, 30.5167, 117.35, 32.9333);
		//近侧: 263.9km 远侧: 39811.14
	//	double distance2 = getDistance2(117.0333, 30.5167, 117.4, 32.8667);
		//近侧: 253.38km远侧: 39821.66
	//	double distance3 = getDistance2(113.5667, 37.85, 113.3, 40.1167);
	}
	
}
