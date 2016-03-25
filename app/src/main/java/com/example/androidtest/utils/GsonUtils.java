package com.example.androidtest.utils;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * @author
 * @version 2016-2-23下午6:27:08
 * @description Json解析工具类
 */

public class GsonUtils {

	/**
	 * 生成Json数据
	 * 
	 * @param object 对象
	 * @return Json
	 */
	public static String toJson(Object object) {
		String result = null;
		try {
			result = (new Gson()).toJson(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 解析JsonObject
	 * 
	 * @param json Josn数据
	 * @param cls 解析的对应类
	 * @return 对象
	 */
	public static <T> T json2Obj(String json, Class<T> cls) {
		T result = null;
		try {
			Gson gson = new Gson();
			result = (T) gson.fromJson(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 解析JsonArray
	 * 
	 * @param json Json数据
	 * @param type 例如：Type listType = new
	 *        TypeToken<LinkedList<T>>(){}.getType();
	 *        需要将T替换成具体的类，否则返回LinkedHashMap对象
	 * @return 集合
	 */
	public static <T> List<T> json2List(String json, Type type) {
		List<T> result = null;
		try {
			Gson gson = new Gson();
			result = gson.fromJson(json, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 解析JsonArray
	 * 
	 * @param json Json数据
	 * @param cls 具体类
	 * @return 集合
	 */
	public static <T> List<T> json2List(String json, Class<T> cls) {
		LinkedList<T> list = new LinkedList<>();
		try {
			Type type = new TypeToken<LinkedList<JsonObject>>() {
			}.getType();
			LinkedList<JsonObject> jsonObjs = new Gson().fromJson(json, type);
			for (JsonObject jsonObj : jsonObjs) {
				list.add(new Gson().fromJson(jsonObj, cls));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
