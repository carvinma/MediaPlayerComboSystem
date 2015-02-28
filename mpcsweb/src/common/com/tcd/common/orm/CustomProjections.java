package com.tcd.common.orm;

import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.type.Type;


/**
 * hibernate Having 进行查询
 * @author 郑卫东
 *
 */
public class CustomProjections {
	
	public static Projection groupByHaving(String column, Type type,
			String having) {

		String[] columns = new String[1];
		Type[] types = new Type[1];

		columns[0] = column;
		types[0] = type;

		return groupByHaving(columns, null, types, having);
	}

	/**
	 * 得到属性
	 * @param column
	 * @param alias
	 * @param type
	 * @param having
	 * @return
	 */
	public static Projection groupByHaving(String column, String alias,
			Type type, String having) {

		String[] columns = new String[1];
		String[] aliases = new String[1];
		Type[] types = new Type[1];

		columns[0] = column;
		aliases[0] = alias;
		types[0] = type;

		return groupByHaving(columns, aliases, types, having);
	}

	/**
	 * 得到属性
	 * @param columns
	 * @param types
	 * @param having
	 * @return
	 */
	public static Projection groupByHaving(String[] columns, Type[] types,
			String having) {

		return groupByHaving(columns, null, types, having);
	}

	/**
	 * 得到属性
	 * @param columns
	 * @param aliases
	 * @param types
	 * @param having
	 * @return
	 */
	public static Projection groupByHaving(String[] columns, String[] aliases,
			Type[] types, String having) {

		if (aliases != null && columns.length != aliases.length) {
			return null;
		}
		if (columns.length != types.length) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		StringBuffer sbgroup = new StringBuffer();
		for (int i = 0; i < columns.length; i++) {
			sb.append(columns[i]);
			sbgroup.append(columns[i]);
			if (aliases != null) {
				sb.append(" as " + aliases[i]);
			}
			if (i < columns.length - 1) {
				sb.append(",");
				sbgroup.append(",");
			}
		}
		
		

		return Projections.sqlGroupProjection(sb.toString(), sbgroup.toString()
				+ " having " + having, aliases, types);
	}
	
	/**
	 * 得到属性
	 * @param sb
	 * @param sbgroup
	 * @param aliases
	 * @param types
	 * @param having
	 * @return
	 */
	public static Projection groupByHaving(String sb,String sbgroup, String[] aliases,
			Type[] types, String having) {
		
		return Projections.sqlGroupProjection(sb.toString(), sbgroup.toString()
				+ " having " + having, aliases, types);
	}

}
