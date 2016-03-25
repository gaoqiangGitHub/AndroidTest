package com.example.androidtest.utils;

import com.example.androidtest.base.AppController;

/**
 * @author
 * @version 2016-1-30上午11:39:46
 * @description
 */

public class CookiesUtils {

	public static String getCookies() {
		return SPUtils.getSpString(AppController.mContext, "Cookie", "cookie");
	}

	public static void setCookies(String cookies) {
		SPUtils.putSpString(AppController.mContext, "Cookie", "cookie", cookies);
	}

}
