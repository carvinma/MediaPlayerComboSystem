package com.tcd.web.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	HttpServletRequest orgRequest = null;

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest = request;
	}

	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] parameters = super.getParameterValues(name);
		if (parameters == null || parameters.length == 0) {
			return null;
		}
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = xssEncode(parameters[i]);
		}
		return parameters;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return super.getAttributeNames();
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = super.getParameterMap();
		if (map == null || map.size() == 0) {
			return map;
		}
		Set<String> keys = map.keySet();
		String vals[] = null;
		Map<String, String[]> maps = new HashMap<String, String[]>();
		for (String key : keys) {
			vals = map.get(key);
			vals = xssEncode(vals);
			maps.put(key, vals);
		}

		return maps;
	}

	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
	 * getHeaderNames 也可能需要覆盖
	 */
	@Override
	public String getHeader(String name) {

		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	private static String[] xssEncode(String ss[]) {
		if (ss == null || ss.length == 0) {
			return ss;
		}
		String vals[] = new String[ss.length];
		for (int i = 0; i < ss.length; i++) {
			vals[i] = xssEncode(ss[i]);
		}
		return vals;
	}

	/**
	 * 将容易引起xss漏洞的半角字符直接替换成全角字符
	 * 
	 * @param s
	 * @return
	 */
	private static String xssEncode(String s) {
		s = xssEncode2(s); //只过滤明文的< > & \ / " ' = % ； 空格 回车
		 s = StringEscapeUtils.escapeHtml4(s); // apache common 包 过滤 转义 xml字符 中文会变成 unicode
		 s = StringEscapeUtils.escapeEcmaScript(s); //过滤脚本 中文会变成 unicode

		return s;
	}

	private static String xssEncode2(String source) {
		if (source == null) {
			return "";
		}
		String html = "";
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			switch (c) {
			case '<':
				buffer.append("&lt;");
				break;
			case '>':
				buffer.append("&gt;");
				break;
			case '&':
				buffer.append("&amp;");
				break;
			case '"':
				buffer.append("&quot;");
				break;
			case '\'':
				buffer.append("&apos;");
				break;
			case '\\':
			case '/':
			case '=':
			case ';':
			case '%':
			case 10:
			case 13:
				break;
			default:
				buffer.append(c);
			}
		}
		html = buffer.toString();

//		if (html != null) {
//			html = html.replaceAll("\\\\", "");
//		}

		return html;
	}

	/**
	 * 获取最原始的request
	 * 
	 * @return
	 */
	public HttpServletRequest getOrgRequest() {
		return orgRequest;
	}

	/**
	 * 获取最原始的request的静态方法
	 * 
	 * @return
	 */
	public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
		if (req instanceof XssHttpServletRequestWrapper) {
			return ((XssHttpServletRequestWrapper) req).getOrgRequest();
		}

		return req;
	}
}