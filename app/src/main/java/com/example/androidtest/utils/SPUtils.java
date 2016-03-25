package com.example.androidtest.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import org.apache.commons.codec.binary.Base64;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author
 * @version 2016-1-22下午4:08:38
 * @description 操作SharedPreferences的工具类
 */

public class SPUtils {

	/**
	 * @param context 上下文，来区别哪一个activity调用的
	 * @param spName 使用的SharedPreferences的名字
	 * @param key SharedPreferences的字段
	 */

	/** 取出spName中key字段对应的string类型的值 */
	public static String getSpString(Context context, String spName, String key) {
		SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		String s = sp.getString(key, "");// 如果该字段没对应值,则取出空字符串
		return s;
	}

	/** 取出spName中key字段对应的int类型的值 */
	public static int getSpInt(Context context, String spName, String key) {
		SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		int i = sp.getInt(key, 0);// 如果该字段没对应值,则取出0
		return i;
	}

	/** 取出spName中key字段对应的boolean类型的值 */
	public static boolean getSpBoolean(Context context, String spName, String key) {
		SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		boolean b = sp.getBoolean(key, false);// 如果该字段没对应值,则默认为false
		return b;
	}

	/** 取出spName中key字段对应的long类型的值 */
	public static long getSpLong(Context context, String spName, String key) {
		SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		long l = sp.getLong(key, 0);// 如果该字段没对应值,则取出0
		return l;
	}

	/** 保存string类型的value到spName中的key字段 */
	public static void putSpString(Context context, String spName, String key, String value) {
		SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit();
	}

	/** 保存int类型的value到spName中的key字段 */
	public static void putSpInt(Context context, String spName, String key, int value) {
		SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		sp.edit().putInt(key, value).commit();
	}

	/** 保存boolean类型的value到spName中的key字段 */
	public static void putSpBoolean(Context context, String spName, String key, boolean value) {
		SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit();
	}

	/** 保存long类型的value到spName中的key字段 */
	public static void putSpLong(Context context, String spName, String key, long value) {
		SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		sp.edit().putLong(key, value).commit();
	}

	/** 清除SharedPreferences所有数据 */
	public static void clear(Context context, String spName) {
		SharedPreferences sp = (SharedPreferences) context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		sp.edit().clear().commit();
	}

	/**
	 * 将对象保存在SharedPreferences中
	 * 
	 * @param context 上下文
	 * @param spName 使用的SharedPreferences的名字
	 * @param key SharedPreferences的字段
	 * @param object 对象，该对象需实现Serializable接口
	 */
	public static void putObject(Context context, String spName, String key, Object object) {
		String value = Code(object);
		context.getSharedPreferences(spName, Context.MODE_PRIVATE).edit().putString(key, value).commit();
	}

	/**
	 * 将对象从SharedPreferences中取出来
	 * 
	 * @param context 上下文
	 * @param spName 使用的SharedPreferences的名字
	 * @param key SharedPreferences的字段
	 * @return 对象
	 */
	public static Object getObject(Context context, String spName, String key) {
		String code = context.getSharedPreferences(spName, Context.MODE_PRIVATE).getString(key, null);
		return Decode(code);
	}

	/**
	 * 编码
	 * 
	 * @param object 对象
	 * @return
	 */
	private static String Code(Object object) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		String code = null;
		try {
			ObjectOutputStream oos = new ObjectOutputStream(stream);
			oos.writeObject(object);
			code = new String(Base64.encodeBase64(stream.toByteArray()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}

	/**
	 * 解码
	 * 
	 * @param code 解码数据
	 * @return
	 */
	private static Object Decode(String code) {
		if (code == null)
			return null;
		Object object = null;
		byte[] base64 = Base64.decodeBase64(code.getBytes());
		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
		try {
			ObjectInputStream bis = new ObjectInputStream(bais);
			object = bis.readObject();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return object;
	}

}
