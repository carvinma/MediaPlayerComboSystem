package com.tcd.common.util;

import java.util.List;

import java.util.ArrayList;

public class GantaName {
	public static String toSysLinename(String s) {
		if (s == null)
			return "";
		s = s.replace("Ⅱ", "II");
		s = s.replace("Ⅰ", "I");
		return s;
	}

	public static String toSysname(String s) {
		if (s == null)
			return "";
		s = s.replace("Ⅱ", "II");
		s = s.replace("Ⅰ", "I");
		GantaName gantaname = new GantaName();
		return gantaname.toName(s);
	}

	int d = 0;
	boolean isHao = false;
	Character hao = null;
	List<Character> dchar = new ArrayList<Character>();
	List<Character> list = new ArrayList<Character>();

	public String toName(String name) {
		setName(name);
		return this.getName();
	}

	public void setName(String name) {
		char[] chars = name.toCharArray();
		for (char c : chars) {
			addChar(c);
		}
	}

	public void addChar(char c) {
		if ((c == 35 || c == 21495) && !isHao) {
			// #
			isHao = true;
			hao = c;
		} else if (c > 47 && c < 58) {
			// 0123456789
			dchar.add(c);
			d++;
		} else {
			if (d > 0 && isHao) {
				for (int i = 0; i < 3 - d; i++) {
					list.add('0');
				}
				for (int i = 0; i < d; i++) {
					list.add(dchar.get(i));
				}
				list.add('号');
				d = 0;
				isHao = false;
				hao = null;
				dchar.clear();
			} else {
				for (char cc : dchar) {
					list.add(cc);
				}
				if (hao != null) {
					list.add(hao);
				}
				hao = null;
				dchar.clear();
				d = 0;
				isHao = false;
			}
			list.add(c);
		}

	}

	public String getName() {

		if (d > 0 && isHao) {
			for (int i = 0; i < 3 - d; i++) {
				list.add('0');
			}
			for (int i = 0; i < d; i++) {
				list.add(dchar.get(i));
			}
			list.add('号');

		}
		char[] chars = new char[list.size()];
		for (int i = 0; i < chars.length; i++) {
			chars[i] = list.get(i);
		}
		return new String(chars);
	}

	public static void main(String[] args) {
		// String s = "新建路1#新建路#1新建路1001#新建路91#新建路100#号码";
		// System.out.println(s);
		// System.out.println(GantaName.toSysname(s));
		//
		// String wzmc="10kV滨河Ⅱ回732";
		//
		// String mc = wzmc.replace("10kV", "").trim();
		// System.out.println("去掉10kV-->"+mc);
		// String lineName = mc.replaceAll("[0-9]{3}", "");
		// System.out.println("去掉数字-->"+lineName);
		//
		// System.out.println("code-->"+mc.replace(lineName, ""));
		java.math.BigDecimal a = new java.math.BigDecimal(123);
		System.out.println(a.toString());
	}

}
