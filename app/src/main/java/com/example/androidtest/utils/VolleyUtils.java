package com.example.androidtest.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.androidtest.base.AppController;
import com.example.androidtest.callback.VolleyCallBack;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author
 * @version 2016-1-30上午10:35:38
 * @description 网络请求通信工具类（Volley）
 */

public class VolleyUtils {

	/** 设置超时时间 */
	private static final int TIME_OUT = 10 * 1000;

	/**
	 * 请求是否缓存
	 * 
	 * @param url 请求链接
	 * @param isCache 是否缓存加载请求
	 * @param listener 请求结果监听
	 * @return 返回true：从缓存中取数据，若无缓存或者缓存读取失败则发送网络请求；返回false：则直接发送网络请求
	 */
	public static boolean requestWithCache(String url, boolean isCache, VolleyCallBack listener) {
		if (isCache) {
			Cache cache = AppController.getInstance().getRequestQueue().getCache();
			Entry entry = cache.get(url);
			if (entry != null) {
				try {
					String data = new String(entry.data, "UTF-8");
					LogUtils.i("获取内容成功");
					listener.httpSucceed(data);
					return true;
				} catch (UnsupportedEncodingException e) {
					LogUtils.i("获取内容失败，请重试" + e.toString());
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * post请求
	 * 
	 * @param url 请求链接
	 * @param parms 请求参数
	 * @param isCache 是否缓存加载请求
	 * @param TAG 标记请求
	 * @param listener 请求结果监听
	 */
	public static void postStringRequest(final String url, final Map<String, String> parms, boolean isCache,
			String TAG, final VolleyCallBack listener) {
		listener.httpLoading();
		if (!requestWithCache(url, isCache, listener)) {
			LogUtils.i("发送网络请求");
			try {
				StringRequest stringRequest = new StringRequest(Method.POST, url, new Response.Listener<String>() {

					@Override
					public void onResponse(String string) {// 请求成功
						listener.httpSucceed(string);
					}
				}, new Response.ErrorListener() {// 请求失败

							@Override
							public void onErrorResponse(VolleyError volleyError) {
								String error = volleyError.toString();
								String errorString = null;
								if (error.contains("TimeoutError"))
									errorString = "请求超时，请重试";
								else if (error.contains("ServerError"))
									errorString = "服务响应出错";
								else if (error.contains("NullPointerException"))
									errorString = "NullPointerException";
								else
									errorString = "网络中断，请检查网络是否可用";
								listener.httpFail(errorString);
							}
						}) {

					@Override
					protected Response<String> parseNetworkResponse(NetworkResponse response) {
						try {
							Map<String, String> responseHeaders = response.headers;
							String cookies = responseHeaders.get("Set-Cookie");
							if (ValueUtils.isStrNotEmpty(cookies)) {
								CookiesUtils.setCookies(cookies);
							}
							String dataString = new String(response.data, "UTF-8");
							return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
						} catch (UnsupportedEncodingException e) {
							return Response.error(new ParseError(e));
						}
					}

					/** 添加请求头 */
					@Override
					public Map<String, String> getHeaders() throws AuthFailureError {
						HashMap<String, String> headers = new HashMap<String, String>();
						headers.put("Cookie", CookiesUtils.getCookies());
						return headers;
					}

					@Override
					protected Map<String, String> getParams() throws AuthFailureError {// post参数
						return parms;
					}

					// 返回一个优先级，优先级分为：Normal, Low, Immediate, High
//					@Override
//					public Priority getPriority() {
//						return Priority.NORMAL;
//					}
				};
				// 设置是否开启缓存
				stringRequest.setShouldCache(isCache);
				// 设置请求设置标签
				stringRequest.setTag(TAG);
				// 设置超时连接及最大重试次数
				stringRequest.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, 0,
						DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
				// 将请求放入队列中
				AppController.getInstance().addToRequestQueue(stringRequest, TAG);
			} catch (Exception e) {
				listener.httpFail("请求失败");
			}
		}
	}

	public static void getStringRequest(final String url, final Map<String, String> parms, boolean isCache, String TAG,
			final VolleyCallBack listener) {
		listener.httpLoading();
		if (!requestWithCache(url, isCache, listener)) {
			LogUtils.i("发送网络请求");
			try {
				StringRequest stringRequest = new StringRequest(Method.GET, url, new Response.Listener<String>() {

					@Override
					public void onResponse(String string) {// 请求成功
						listener.httpSucceed(string);
					}
				}, new Response.ErrorListener() {// 请求失败

							@Override
							public void onErrorResponse(VolleyError volleyError) {
								String error = volleyError.toString();
								String errorString = null;
								if (error.contains("TimeoutError"))
									errorString = "请求超时，请重试";
								else if (error.contains("ServerError"))
									errorString = "服务响应出错";
								else if (error.contains("NullPointerException"))
									errorString = "NullPointerException";
								else
									errorString = "网络中断，请检查网络是否可用";
								listener.httpFail(errorString);
							}
						}) {

					@Override
					protected Response<String> parseNetworkResponse(NetworkResponse response) {
						try {
							Map<String, String> responseHeaders = response.headers;
							String cookies = responseHeaders.get("Set-Cookie");
							if (ValueUtils.isStrNotEmpty(cookies)) {
								CookiesUtils.setCookies(cookies);
							}
							String dataString = new String(response.data, "UTF-8");
							return Response.success(dataString, HttpHeaderParser.parseCacheHeaders(response));
						} catch (UnsupportedEncodingException e) {
							return Response.error(new ParseError(e));
						}
					}

					/** 添加请求头 */
					@Override
					public Map<String, String> getHeaders() throws AuthFailureError {
						HashMap<String, String> headers = new HashMap<String, String>();
						headers.put("Cookie", CookiesUtils.getCookies());
						return headers;
					}

//					@Override
//					protected Map<String, String> getParams() throws AuthFailureError {// post参数
//						return parms;
//					}

					// 返回一个优先级，优先级分为：Normal, Low, Immediate, High
//					@Override
//					public Priority getPriority() {
//						return Priority.NORMAL;
//					}
				};
				// 设置是否开启缓存
				stringRequest.setShouldCache(isCache);
				// 设置请求设置标签
				stringRequest.setTag(TAG);
				// 设置超时连接及最大重试次数
				stringRequest.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, 0,
						DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
				// 将请求放入队列中
				AppController.getInstance().addToRequestQueue(stringRequest, TAG);
			} catch (Exception e) {
				listener.httpFail("请求失败");
			}
		}
	}
}
