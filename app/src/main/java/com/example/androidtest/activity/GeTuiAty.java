package com.example.androidtest.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.base.BaseActivity;
import com.example.androidtest.callback.VolleyCallBack;
import com.example.androidtest.utils.VolleyUtils;
import com.igexin.sdk.PushManager;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author
 * @version 2016-3-4上午11:09:58
 * @description
 */

public class GeTuiAty extends BaseActivity {
	protected static final String TAG = GeTuiAty.class.getSimpleName();
	public static TextView tv, tv2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_getui);

		// 初始化个推SDK
		PushManager.getInstance().initialize(this.getApplicationContext());

		tv = (TextView) findViewById(R.id.textView);
		tv2 = (TextView) findViewById(R.id.textView2);

		getResult();
	}

	private void getResult() {
//		String url = "http://api.map.baidu.com/geocoder/v2/";
		String url = null;
		try {
			url = "http://api.map.baidu.com/geocoder/v2/?ak=B3xabPuW0YPaL7PudMoXURop&output=json&address="
					+ URLEncoder.encode("百度大厦", "UTF-8") + "&city=" + URLEncoder.encode("北京市", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("output", "json");
		params.put("ak", "B3xabPuW0YPaL7PudMoXURop");
//		try {
//			params.put("address", URLEncoder.encode("UTF-8", "长宁区仙霞路350号"));
//			params.put("city", URLEncoder.encode("UTF-8", "上海市"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			LogUtils.i(e.toString());
//		}
		params.put("address", "长宁区仙霞路350号");
		params.put("city", "上海市");
		VolleyUtils.getStringRequest(url, params, false, TAG, new VolleyCallBack() {

			@Override
			public void httpSucceed(String content) {
				LogUtils.i("拿到返回结果了--->>>" + content);
			}

			@Override
			public void httpLoading() {
				// TODO Auto-generated method stub

			}

			@Override
			public void httpFail(String error) {
				LogUtils.i("出错了--->>>" + error);
			}
		});
	}
}
