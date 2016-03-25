package com.example.androidtest.receiver;

import java.util.HashMap;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.androidtest.activity.GeTuiAty;
import com.example.androidtest.callback.VolleyCallBack;
import com.example.androidtest.utils.SPUtils;
import com.example.androidtest.utils.ValueUtils;
import com.example.androidtest.utils.VolleyUtils;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.Tag;
import com.lidroid.xutils.util.LogUtils;

public class PushDemoReceiver extends BroadcastReceiver {
	public static final String TAG = PushDemoReceiver.class.getSimpleName();

	/**
	 * 应用未启动, 个推 service已经被唤醒,保存在该时间段内离线消息(此时 GetuiSdkDemoActivity.tLogView ==
	 * null)
	 */
	public static StringBuilder payloadData = new StringBuilder();

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Log.d("GetuiSdkDemo", "onReceive() action=" + bundle.getInt("action"));

		switch (bundle.getInt(PushConsts.CMD_ACTION)) {
		case PushConsts.GET_MSG_DATA:
			// 获取透传（payload）数据
//			String appid = bundle.getString("appid");
			byte[] payload = bundle.getByteArray("payload");
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");
			LogUtils.i("taskid--->>>" + taskid + "      messageid--->>>" + messageid);

			// smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
			boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
			LogUtils.i("第三方回执接口调用" + (result ? "成功" : "失败"));

			if (payload != null) {
				String data = new String(payload);
				LogUtils.i("receiver payload : " + data);
				// TODO 接收处理透传（payload）数据
				payloadData.append(data);
				payloadData.append("\n");

				if (GeTuiAty.tv != null) {
					GeTuiAty.tv.append(data + "\n");
				}
			}
			break;

		case PushConsts.GET_CLIENTID:
			// 获取ClientID(CID)
			// 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
			// 部分特殊情况下CID可能会发生变化，为确保应用服务端保存的最新的CID，应用程序在每次获取CID广播后，如果发现CID出现变化，需要重新进行一次关联绑定
			String cid = bundle.getString("clientid");
			if (GeTuiAty.tv2 != null) {
				GeTuiAty.tv2.setText(cid);
				LogUtils.i("CID--->>>" + cid);
				int value = SPUtils.getSpInt(context, "bind", "value");
				if (value == 0) {
					userToCid(context, "119", cid);
				}
			}
			if (ValueUtils.isStrNotEmpty(cid)) {
				String[] tags = new String[] { "测试", "abc" };
				Tag[] tagParam = new Tag[tags.length];

				for (int i = 0; i < tags.length; i++) {
					Tag t = new Tag();
					t.setName(tags[i]);
					tagParam[i] = t;
				}
				int i = PushManager.getInstance().setTag(context, tagParam);
				LogUtils.i(i + "");
			}
			break;

		case PushConsts.THIRDPART_FEEDBACK:
			/*
			 * String appid = bundle.getString("appid"); String taskid =
			 * bundle.getString("taskid"); String actionid =
			 * bundle.getString("actionid"); String result =
			 * bundle.getString("result"); long timestamp =
			 * bundle.getLong("timestamp");
			 * 
			 * Log.d("GetuiSdkDemo", "appid = " + appid); Log.d("GetuiSdkDemo",
			 * "taskid = " + taskid); Log.d("GetuiSdkDemo", "actionid = " +
			 * actionid); Log.d("GetuiSdkDemo", "result = " + result);
			 * Log.d("GetuiSdkDemo", "timestamp = " + timestamp);
			 */
			break;
		default:
			break;
		}
	}

	private void userToCid(final Context context, String userid, String cid) {
		HashMap<String, String> parms = new HashMap<String, String>();
		parms.put("userid", userid);
		parms.put("cid", cid);
		VolleyUtils.postStringRequest("http://192.168.40.96/expressService/Mespush/userToCid", parms, false, TAG,
				new VolleyCallBack() {

					@Override
					public void httpSucceed(String content) {
						SPUtils.putSpInt(context, "bind", "value", 1);
						LogUtils.i(content);
					}

					@Override
					public void httpLoading() {
						SPUtils.putSpInt(context, "bind", "value", 1);
						LogUtils.i("正在发送绑定请求...");
					}

					@Override
					public void httpFail(String error) {
						SPUtils.putSpInt(context, "bind", "value", 0);
						LogUtils.i(error);
					}
				});
	}
}
