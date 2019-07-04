package com.app.wallet.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * 格式化日期---yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		return format("yyyyMMdd",date);
	}
	
	/**
	 * 按照制定格式格式化日期
	 * @param pattern 格式
	 * @param date 日期
	 * @return
	 */
	public static String format(String pattern,Date date){
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		return sf.format(date);
	}
	
	/**
	 * 将日期字符串转换成 指定格式
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(String date, String pattern){
		return format(date,pattern,3);
	}
	
	/**
	 * max{1,2,3,4,5,6}->{yyyy,yyyyMM,yyyyMMdd,yyyMMddhh,yyyyMMddhhmm,yyyyMMddhhmmss}
	 * 精度不够的情况下 会有默认值
	 * @param date
	 * @param pattern
	 * @param max 精度
	 * @return
	 */
	public static String format(String date, String pattern,int max){
		try{
			SimpleDateFormat sf = getSimleDateFormat(max);
			Date ndate = sf.parse(simpleDate(date,max));
			sf = null;
			return format(pattern, ndate);
		}catch(Exception e){
			return null;
		}
	}
	
	private static SimpleDateFormat getSimleDateFormat(int max) {
		if(max==1){
			return new SimpleDateFormat("yyyy");
		}else if(max==2){
			return new SimpleDateFormat("yyyyMM");
		}else if(max==4){
			return new SimpleDateFormat("yyyyMMddhh");
		}else if(max==5){
			return new SimpleDateFormat("yyyyMMddhhmm");
		}else if(max==6){
			return new SimpleDateFormat("yyyyMMddhhmmss");
		}else{
			return new SimpleDateFormat("yyyyMMdd");
		}
	}

	/**
	 * 通过身份证号得到年龄
	 * @param idcard
	 * @return
	 */
	public static int getAgeByIdcard(String idcard){
		String birthday = "";
		if(idcard.length()==18){
			birthday = idcard.substring(6, 14);
		}else{
			birthday = "19"+idcard.substring(6,12);
		}
		return getAge(birthday);
	}
	
	private static int getAgeNor(String birthday){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		int ny = ca.get(Calendar.YEAR);
		int nm = ca.get(Calendar.MONTH)+1;
		int nd = ca.get(Calendar.DAY_OF_MONTH);
		int oy = Integer.parseInt(birthday.substring(0,4));
		int om = Integer.parseInt(birthday.substring(4,6));
		int od = Integer.parseInt(birthday.substring(6,8));
		int year = ny - oy; 
		int month = nm - om;
		int day = nd - od;
		if(day < 0){
			--month;
		}
		if(month<0){
			--year;
		}
		return year;
	}
	
	/**
	 * 通过出生日期字符串得到年龄（格式不限，但是只能精确到日期）
	 * @param birthday
	 * @return
	 */
	public static int getAge(String birthday){
		birthday = simpleDate(birthday);
		return getAgeNor(birthday);
	}
	
	private static String simpleDate(String date){
		return simpleDate(date,3);
	}
	
	private static String simpleDate(String date,int max){
		date = date.replaceAll("[^\\d]", "&");
		String[] str = date.split("&");
		String ns = "";
		int count = 0;
		for(String s:str){
			if(count==max){
				break;
			}
			if(StringUtils.isNotEmpty(s)){
				count++;
				if(s.length()==1){
					s = "0"+s;
				}
				ns += s;
			}
		}
		return ns.substring(0, length(max));
	}
	
	private static int length(int max){
		return 2*(max+1);
	}
	
	public static void main(String[] strs){
		String date = "20050503102045";
		String ns = format(date, "yyyyMMdd hh",4);
		System.out.println(ns);
	}
}
