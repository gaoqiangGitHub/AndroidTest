package com.example.androidtest.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author
 * @version 2016-1-22下午3:29:18
 * @description 获取屏幕属性工具类
 */

public class ScreenSizeUtils {

	/**
	 * 获取屏幕宽度，单位像素
	 * 
	 * @param context 上下文
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度，单位像素
	 * 
	 * @param context 上下文
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	/**
	 * 屏幕密度（像素比例：0.75/1.0/1.5/2.0/3.0）
	 * 
	 * @param context 上下文
	 * @return
	 */
	public static float getScreenDensity(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.density;
	}

	/**
	 * 屏幕密度（每寸像素：120/160/240/320/480）
	 * 
	 * @param context 上下文
	 * @return
	 */
	public static int getScreenDensityDPI(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.densityDpi;
	}

}
