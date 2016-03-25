package com.example.androidtest.utils;

import android.content.Context;

/**
 * @author
 * @version 2015-12-23下午8:27:21
 * @description 工具类
 */

public class Utils {

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * 
	 * @param context 上下文
	 * @param pxValue 像素值
	 * @return dp
	 */
	public static int px2dip(Context context, int pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
	 * 
	 * @param context 上下文
	 * @param px 像素值
	 * @return sp
	 */
	public static int px2sp(Context context, int pxValue) {
		final float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / scaledDensity);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * 
	 * @param context 上下文
	 * @param dipValue dp值
	 * @return px
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/** 最后一次点击时间 */
	private static long lastClickTime;

	/**
	 * 防止控件被快速重复点击
	 * 
	 * @return true为快速重复点击,反之
	 */
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (timeD > 0 && timeD < 800) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	/**
	 * 根据资源ID获取对应的字符串
	 * 
	 * @param context 上下文
	 * @param resId 资源ID
	 * @return
	 */
	public static String getString(Context context, int resId) {
		return null == context ? "" : context.getString(resId);
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * 
	 * @param s 字符串
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

}
