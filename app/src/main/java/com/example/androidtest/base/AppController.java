package com.example.androidtest.base;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.androidtest.loader.LoadImage;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author
 * @version 2016-1-25下午5:41:52
 * @description 自定义Application
 */

public class AppController extends Application {

	private static final String TAG = AppController.class.getSimpleName();

	/** Volley网络请求队列 */
	private RequestQueue mRequestQueue;

	/** 全局的上下文 */
	public static Context mContext;

	private static AppController mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		mInstance = this;

		// 设置打印Log的TAG及是否开启Log输出
		LogUtils.customTagPrefix = "Andy";
		LogUtils.allowI = true;

		LoadImage.init(getApplicationContext());// 初始化ImageLoader
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
			// 使用Volley网络请求库加载图片
//			Glide.get(this).register(GlideUrl.class, InputStream.class, new VolleyUrlLoader.Factory(mRequestQueue));
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public void cancelRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

}
