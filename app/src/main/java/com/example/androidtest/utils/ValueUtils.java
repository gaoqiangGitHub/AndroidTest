package com.example.androidtest.utils;

import java.util.List;

/**
 * @author
 * @version 2016-1-22下午3:58:24
 * @description 数据判断工具类
 */

public class ValueUtils {

	public static boolean isStrEmpty(String value) {
		if (null == value || "".equals(value.trim())) {
			return true;
		} else {
			value = value.replaceAll(" ", "").trim();
			if (null == value || "".equals(value.trim())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isStrNotEmpty(String value) {
		if (null == value || "".equals(value.trim())) {
			return false;
		} else {
			value = value.replaceAll(" ", "").trim();
			if (null == value || "".equals(value.trim())) {
				return false;
			}
		}
		return true;
	}

	public static boolean isListEmpty(List<?> list) {
		return null == list || list.size() == 0;
	}

	public static boolean isListNotEmpty(List<?> list) {
		return null != list && list.size() > 0;
	}

	public static boolean isNotEmpty(Object object) {
		return null != object;
	}

	public static boolean isEmpty(Object object) {
		return null == object;
	}

}
