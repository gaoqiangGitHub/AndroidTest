package com.example.androidtest.callback;

/**
 * @author
 * @version 2016-1-30上午11:06:45
 * @description Volley回调
 */

public interface VolleyCallBack {

	/** 请求中 */
	void httpLoading();

	/** 请求成功 */
	void httpSucceed(String content);

	/** 请求失败 */
	void httpFail(String error);
}
