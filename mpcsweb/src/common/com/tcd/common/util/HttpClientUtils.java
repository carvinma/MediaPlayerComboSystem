package com.tcd.common.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

public class HttpClientUtils {
	private static final Logger logger = Logger.getLogger(HttpClientUtils.class);
	@SuppressWarnings("deprecation")
	private static HttpClient hc = new DefaultHttpClient(new ThreadSafeClientConnManager());

	public static void main(String[] args) throws JDOMException, IOException {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sid", "15001"));// 设置服务编码
		params.add(new BasicNameValuePair("resType", "xml"));// 设置返回数据的类型
		params.add(new BasicNameValuePair("key",
				"747c5a39c51306e4076c6c1f526411d1d55b2e47f76b2ea6b45eff3bc8e4399145cc17a0601f5c8d"));//
		// 设置key
		params.add(new BasicNameValuePair("encode", "UTF-8"));// 设置返回数据编码格式
		
		params.add(new BasicNameValuePair("xys", "116.281,39.8881;116.3744111,39.9120711"));// 查询范围
		String url = "http://guardian.tcd-service.com:8081/coordinate/simple";
		String body = post(url, params);
		System.out.println(body);

		// List<String> list = new ArrayList<String>();
		// // 创建一个新的字符串
		// StringReader read = new StringReader(body);
		// // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		// InputSource source = new InputSource(read);
		// // 创建一个新的SAXBuilder
		// SAXBuilder sb = new SAXBuilder();
		// // 通过输入源构造一个Document
		// Document doc = sb.build(source);
		// // 取的根元素
		// Element root = doc.getRootElement();
		// for (Element element : root.getChildren()) {
		// if ("list".equals(element.getAttributeValue("type"))) {
		// for (Element c : element.getChildren()) {
		// String ss = "";
		// for (Element e : c.getChildren()) {
		// if ("numberzoom".equals(e.getName()) && "0".equals(e.getValue())) {
		// list.add(ss);
		// System.out.println(ss);
		// } else {
		// if (ss.length() > 0) {
		// ss = ss + "," + e.getValue();
		// } else {
		// ss = ss + e.getValue();
		// }
		// }
		// }
		// }
		// }
		// }

	}

	/**
	 * Get请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String get(String url, List<NameValuePair> params) {

		String body = null;
		String str = null;
		HttpGet httpget = null;
		try {
			// 设置参数
			str = url + "?" + EntityUtils.toString(new UrlEncodedFormEntity(params));
			// Get请求
			httpget = new HttpGet(str);

			logger.info(str);
			// 发送请求
			HttpResponse httpresponse = hc.execute(httpget);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity);
			EntityUtils.consume(entity);

		} catch (Exception e) {
			// e.printStackTrace();
		}

		return body;

	}

	/**
	 * // Post请求
	 * 
	 * @param url
	 * @param params
	 * @return String
	 */
	public static String post(String url, List<NameValuePair> params) {

		String body = null;

		try {
			// Post请求
			HttpPost httppost = new HttpPost(url);
			// 设置参数
			httppost.setEntity(new UrlEncodedFormEntity(params));
			// 发送请求
			HttpResponse httpresponse = hc.execute(httppost);
			// 获取返回数据
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity);

			logger.info(httppost.getURI() + "?" + EntityUtils.toString(new UrlEncodedFormEntity(params)));
			// System.out.println(body);
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return body;
	}

	public static Map<String, String> xmlElements(String xmlDoc) {
		// 创建一个新的字符串
		StringReader read = new StringReader(xmlDoc);
		// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 通过输入源构造一个Document
			Document doc = sb.build(source);
			// 取的根元素
			Element root = doc.getRootElement();

			getRoot(map, root, root);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	private static void getRoot(Map<String, String> map, Element perElmt, Element xet) {
		if (xet.getChildren().size() > 0) {
			for (Element element : xet.getChildren()) {
				getRoot(map, xet, element);
			}
		} else {
			// 返回结果集的第一条
			if (map.get(perElmt.getName() + "." + xet.getName()) == null) {
				map.put(perElmt.getName() + "." + xet.getName(), xet.getText());
			}

		}
	}

}
