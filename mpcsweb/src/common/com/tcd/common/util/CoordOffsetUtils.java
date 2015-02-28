package com.tcd.common.util;

import java.math.BigDecimal;

import org.gds.offset.CoordOffset;

public class CoordOffsetUtils {
	
	private static CoordOffset coordOffset=null;
	
//	static public void offsetCoord(double x, double y, double[] outXY){
//		String xy=MapManger.getCoordinate(x+","+y);
//		if(xy!=null){
//			String lonlat[]=xy.split(",");
//			outXY[0]=Double.valueOf(lonlat[0]);
//			outXY[1]=Double.valueOf(lonlat[1]);
//		}
//	}
	
	static public void offsetCoord(double x, double y, double[] outXY){
		if(coordOffset==null){
			coordOffset=CoordOffset.getInstance("/var/www/map/packfile.dat", false);
		}
		int rs=coordOffset.offsetCoord( x,  y, outXY);
		if(rs==-1){
			outXY[0]=x;
			outXY[1]=y;
		}
	}
	
	static public void offsetCoord(String x, String y, String [] soutXY){
		if(x==null || y==null){
			return ;
		}
		double outXY[]=new double[2];
		offsetCoord(Double.valueOf(x),Double.valueOf(y),outXY);
		if(soutXY==null){
			soutXY=new String[2];
		}
		soutXY[0]=outXY[0]+"";
		soutXY[1]=outXY[1]+"";
	}
	
	static public void offsetCoord(BigDecimal x, BigDecimal y, BigDecimal [] soutXY){
		if(x==null || y==null){
			return ;
		}
		double outXY[]=new double[2];
		offsetCoord(Double.valueOf(x.toString()),Double.valueOf(y.toString()),outXY);
		if(soutXY==null){
			soutXY=new BigDecimal[2];
		}
		soutXY[0]=new BigDecimal(outXY[0]+"");
		soutXY[1]=new BigDecimal(outXY[1]+"");
	}
	
	public static void main(String args[]){
		double[] outXY=new double[2];
		offsetCoord(113.594819333,37.857819319,outXY);
		System.out.println(outXY[0]+","+outXY[1]);
	}
}
