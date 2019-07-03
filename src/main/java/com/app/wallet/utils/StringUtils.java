package com.app.wallet.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {
    public static String BASIC_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String BASIC_NUM = "012345678901234567890123456789";

    public static String shuffileStr(){
        return shuffileStr(20);
    }

    public static String shuffileStr(int num){
        List<Character> ls = BASIC_STR.chars().mapToObj( c -> (char)c).collect(Collectors.toList());
        Collections.shuffle(ls);
        StringBuffer sb = new StringBuffer();
        ls.forEach((item)->{
            sb.append(item);
        });
        return sb.toString().substring(0, num);
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() > 0;
    }

    /**
     * 判断字符串是否为null或去空格后长度为0
     * @param s 字符串
     * @return true，为null或去空格后长度为0
     */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }
    /**
     * 判断字符串数组是否为null或去空格后长度为0
     * @param s 字符串
     * @return true，为null或去空格后长度为0
     */
    public static boolean isEmpty(String[] s) {
        return s == null || s.length== 0;
    }
//
//    public static void main(String[] strs){
//
//        System.out.println(shuffileStr());
//    }
}
