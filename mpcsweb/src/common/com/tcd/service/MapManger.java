package com.tcd.service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.tcd.common.cache.CacheUtils;
import com.tcd.common.util.HttpClientUtils;

public class MapManger {

	/** 查询类型URL */
	private static String typeUrl = null;
	/** key */
	private static String key = "747c5a39c51306e4076c6c1f526411d1d55b2e47f76b2ea6b45eff3bc8e4399145cc17a0601f5c8d";
	/** 服务编码 */
	private static String sid = null;
	/** 地图服务器地址 */
	private static String serverHost = "http://guardian.tcd-service.com:8081/";

	/**
	 * 根据坐标返回地理位置
	 * 
	 * @param xy
	 * @return String
	 */
	public static String getAddress(String xy) {
		typeUrl = "rgeocode/simple";
		sid = "7001";

		List<NameValuePair> params = setParams();
		params.add(new BasicNameValuePair("range", "1000"));// 设置服务编码
		params.add(new BasicNameValuePair("roadnum", "1"));// 查询范围
		params.add(new BasicNameValuePair("poinum", "0"));
		params.add(new BasicNameValuePair("crossnum", "0"));
		params.add(new BasicNameValuePair("crossnum", "0"));
		params.add(new BasicNameValuePair("region", xy));

		Map<String, String> map = HttpClientUtils.xmlElements(HttpClientUtils.post(serverHost + typeUrl, params));
		StringBuffer buffer = new StringBuffer();
		buffer.append(map.get("province.name"));
		buffer.append(map.get("city.name"));
		buffer.append(map.get("district.name"));
		if(map.get("road.name")!=null&&!map.get("road.name").equals("null")){
			buffer.append(map.get("road.name"));
		}
		buffer.append("附近");
		return buffer.toString();
	}

	/**
	 * 根据坐标返回城市
	 * 
	 * @param xy
	 * @return String
	 */
	public static String getAddressCity(String xy) {
		typeUrl = "rgeocode/simple";
		sid = "7001";

		List<NameValuePair> params = setParams();
		params.add(new BasicNameValuePair("range", "1000"));// 设置服务编码
		params.add(new BasicNameValuePair("roadnum", "1"));// 查询范围
		params.add(new BasicNameValuePair("poinum", "0"));
		params.add(new BasicNameValuePair("crossnum", "0"));
		params.add(new BasicNameValuePair("crossnum", "0"));
		params.add(new BasicNameValuePair("region", xy));

		Map<String, String> map = HttpClientUtils.xmlElements(HttpClientUtils.get(serverHost + typeUrl, params));
		if (map.get("city.name") != null && map.get("city.name").trim().length() > 0)
			return map.get("city.name");
		return map.get("province.name");

	}

	/**
	 * 设置参数
	 * 
	 * @return List<NameValuePair>
	 */
	private static List<NameValuePair> setParams() {

		// 用户Key
		//key = CacheUtils.getValueByCode("SYS_MAP_KEY");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sid", sid));
		params.add(new BasicNameValuePair("resType", "xml"));// 设置返回数据的类型
		params.add(new BasicNameValuePair("key", key));// 设置key
		params.add(new BasicNameValuePair("encode", "UTF-8"));// 设置返回数据编码格式
		

		//serverHost = CacheUtils.getValueByCode("SYS_BASE_MAP_URL");
		// 取得地图服务器地址

		return params;
	}

	/**
	 * 点到线 获取距离(精确到米)
	 * 
	 * @param from
	 * @param to
	 * @return String
	 */
	public static String getDistance(String from, String to) {

		typeUrl = "gts/simple";
		sid = "4002";
		List<NameValuePair> params = setParams();
		params.add(new BasicNameValuePair("uid", "1"));// 地理坐标
		params.add(new BasicNameValuePair("acoor", from));// 查询范围
		params.add(new BasicNameValuePair("bcoor", to));
		

		Map<String, String> map = HttpClientUtils.xmlElements(HttpClientUtils.post(serverHost + typeUrl, params));

		String mm = map.get("results.distance");
		if (mm == null) {
			return "";
		}
		BigDecimal b = new BigDecimal(mm);

		return b.multiply(new BigDecimal(1000)).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 取得距离(精确到米)
	 * 
	 * @param xys(支持13个途径点)
	 * @return String
	 */
	public static String totalMileage(String xys) {

		typeUrl = "route/simple";
		sid = "8002";
		List<NameValuePair> params = setParams();
		params.add(new BasicNameValuePair("routeType", "2"));// 地理坐标
		params.add(new BasicNameValuePair("xys", xys));

		Map<String, String> map = HttpClientUtils.xmlElements(HttpClientUtils.post(serverHost + typeUrl, params));
		String mm = map.get("searchresult.distance");
		if (mm == null) {
			return "";
		}
		if (mm.indexOf(".") < 0) {
			return mm;
		}
		return mm.substring(0, mm.indexOf("."));
	}

	/**
	 * 
	 * @param xys(坐标点对的x、y值使用","分隔，坐标之间使用","隔开)
	 * @param zoom(地图缩放级别)
	 * @return List<String>(返回的数据 x,y)
	 */
	public static List<String> xysFilter(String xys, String zoom) {
		List<String> list = new ArrayList<String>();
		typeUrl = "coordinate/simple";
		sid = "15003";
		List<NameValuePair> params = setParams();
		params.add(new BasicNameValuePair("get", "binary"));
		params.add(new BasicNameValuePair("zoom", zoom));// 地理坐标
		params.add(new BasicNameValuePair("xys", xys));

		StringReader read = new StringReader(HttpClientUtils.post(serverHost + typeUrl, params));

		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();

		try {
			// 通过输入源构造一个Document
			Document doc = sb.build(source);
			// 取的根元素
			Element root = doc.getRootElement();

			Element element = root.getChildren().get(4);
			if ("list".equals(element.getAttributeValue("type"))) {
				for (Element c : element.getChildren()) {
					String ss = "";
					for (Element e : c.getChildren()) {
						if ("numberzoom".equals(e.getName()) && "0".equals(e.getValue())) {
							list.add(ss);
						} else {
							if (ss.length() > 0) {
								ss = ss + "," + e.getValue();
							} else {
								ss = ss + e.getValue();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}

		return list;
	}

	/**
	 * 导航路线
	 * 
	 * @param xys坐标对集合 例:x1,y1;x2,y2; 支持13个点
	 * @param per抽稀参数
	 * @return String 坐标对集合
	 */
	public static String getCoor(String xys, String per) {
		// List<String> list = new ArrayList<String>();
		typeUrl = "route/simple";
		sid = "8000";
		List<NameValuePair> params = setParams();
		params.add(new BasicNameValuePair("per", per));
		params.add(new BasicNameValuePair("routeType", "2"));// 距离优先
		params.add(new BasicNameValuePair("xys", xys));
		
		StringReader read = new StringReader(HttpClientUtils.post(serverHost + typeUrl, params));

		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();

		StringBuffer buffer = new StringBuffer();
		try {
			// 通过输入源构造一个Document
			Document doc = sb.build(source);
			// 取的根元素
			Element root = doc.getRootElement();

			Element element = root.getChildren().get(5);
			if ("list".equals(element.getAttributeValue("type"))) {
				for (Element c : element.getChildren()) {
					for (Element e : c.getChildren()) {
						if (e.getName().equals("coor"))
							buffer.append(e.getValue()).append(";");
					}
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} 
		//System.out.println(buffer.toString().length());
		return buffer.toString();
	}

	/**
	 * 修正坐标偏移
	 * 
	 * @param xys(例:116.281,39.8881;116.3744111,39.9120711)
	 * @return String
	 */
	public static String getCoordinate(String xys) {
		typeUrl = "coordinate/simple";
		sid = "15001";
		List<NameValuePair> params = setParams();
		params.add(new BasicNameValuePair("xys", xys));
		Map<String, String> map = HttpClientUtils.xmlElements(HttpClientUtils.get(serverHost + typeUrl, params));
		String mm = map.get("searchresult.xys");
		return mm;
	}

	public static void main(String[] args) {
		// System.out.println(MapManger.getDistance("116.343498,40.036289","116.39455722222222,39.930315"));
		System.out.println(MapManger.getAddress("118.46706108,40.13348538"));
		//System.out.println(MapManger.totalMileage("109.08576791,34.2756912;109.08582598,34.27562612;109.08577458,34.27566272;109.08571618,34.27570634;109.08576791,34.2756912;109.08582813,34.2756889"));
		//System.out.println(MapManger.getCoor("116.3160,39.96497;117.180445,39.2634875;", "10"));
		//System.out.println(MapManger.getCoordinate("116.281,39.8881;116.3744111,39.9120711"));
	}
}
