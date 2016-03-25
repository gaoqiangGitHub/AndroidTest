package com.example.androidtest.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.OnEntityListener;
import com.baidu.trace.OnStartTraceListener;
import com.baidu.trace.OnStopTraceListener;
import com.baidu.trace.OnTrackListener;
import com.baidu.trace.Trace;
import com.baidu.trace.TraceLocation;
import com.example.androidtest.R;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author
 * @version 2016-1-25下午6:00:33
 * @description
 */

public class TraceAty extends Activity {
	/** 轨迹服务客户端 */
	protected LBSTraceClient client;

	/** 鹰眼服务ID */
	protected long serviceId = 109458;

	/** entity标识 */
	protected String entityName = "刘小亮1111111112222";

	/** 轨迹服务类型（0 : 不上传位置数据，也不接收报警信息； 1 : 不上传位置数据，但接收报警信息；2 : 上传位置数据，且接收报警信息） */
	private int traceType = 2;

	/** 轨迹服务 */
	protected Trace trace;

	/** Entity监听器（查询实时位置） */
	protected OnEntityListener entityListener = null;

	/** Track监听器（查询历史轨迹） */
	protected OnTrackListener trackListener = null;

	/** 开启轨迹服务监听器 */
	protected OnStartTraceListener startTraceListener = null;

	/** 停止轨迹服务监听器 */
	protected OnStopTraceListener stopTraceListener = null;

	protected Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_trace);

		mContext = getApplicationContext();

		// 实例化轨迹服务客户端
		client = new LBSTraceClient(mContext);
		// 实例化轨迹服务
		trace = new Trace(mContext, serviceId, entityName, traceType);
		// 初始化OnEntityListener
		initOnEntityListener();
		// 初始化OnTrackListener
		initOnTrackListener();
		// 初始化开启轨迹服务监听器
		initOnStartTraceListener();
		// 初始化停止轨迹服务监听器
		initOnStopTraceListener();

		client.setOnTrackListener(trackListener);

		// 位置采集时间间隔，单位：秒。最小为2秒，最大为1分钟，否则设置不成功
		int gatherInterval = 3;
		// 打包时间间隔，单位：秒。打包时间间隔必须为采集时间间隔的整数倍，且最大不能超过1分钟，否则设置不成功
		int packInterval = 6;
		// 设置位置采集和打包周期
		client.setInterval(gatherInterval, packInterval);
		// 设置请求协议（0为http，1为https）
		client.setProtocolType(0);
	}

	/** 初始化OnEntityListener */
	private void initOnEntityListener() {
		entityListener = new OnEntityListener() {
			// 请求失败回调接口
			@Override
			public void onRequestFailedCallback(String arg0) {
				Looper.prepare();
				Toast.makeText(getApplicationContext(), "entity请求失败回调接口消息 : " + arg0, Toast.LENGTH_SHORT).show();
				Looper.loop();
			}

			// 添加entity回调接口
			@Override
			public void onAddEntityCallback(String arg0) {
				Looper.prepare();
				Toast.makeText(getApplicationContext(), "添加entity回调接口消息 : " + arg0, Toast.LENGTH_SHORT).show();
				Looper.loop();
			}

			// 查询entity列表回调接口
			@Override
			public void onQueryEntityListCallback(String message) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onReceiveLocation(TraceLocation location) {
				LogUtils.i("location : " + location.toString());
			}

		};
	}

	/** 初始化OnTrackListener */
	private void initOnTrackListener() {
		trackListener = new OnTrackListener() {

			// 请求失败回调接口
			@Override
			public void onRequestFailedCallback(String arg0) {
				Looper.prepare();
				Toast.makeText(getApplicationContext(), "track请求失败回调接口消息 : " + arg0, Toast.LENGTH_SHORT).show();
				Looper.loop();
			}

			// 轨迹属性回调接口，在回传轨迹点时回传属性数据
			@Override
			public Map<String, String> onTrackAttrCallback() {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", "Andy");
				return map;
			}

			// 查询历史轨迹回调接口
			@Override
			public void onQueryHistoryTrackCallback(String arg0) {
				super.onQueryHistoryTrackCallback(arg0);
			}

		};
	}

	/** 初始化OnStartTraceListener */
	private void initOnStartTraceListener() {
		// 初始化startTraceListener
		startTraceListener = new OnStartTraceListener() {

			// 开启轨迹服务回调接口（errorNo : 消息编码，message : 消息内容，详情查看类参考）
			public void onTraceCallback(int errorNo, String message) {
				LogUtils.i("开启轨迹服务回调接口消息 [消息编码 : " + errorNo + "，消息内容 : " + message + "]");
				if (!(errorNo == 0 || errorNo == 10006 || errorNo == 10008 || errorNo == 10009)) {// 若开启轨迹服务没有成功，则再次开启
					client.startTrace(trace, startTraceListener);
				}
			}

			// 轨迹服务推送接口（用于接收服务端推送消息，messageType : 消息类型，message : 消息内容，详情查看类参考）
			public void onTracePushCallback(byte messageType, String message) {
				LogUtils.i("轨迹服务推送接口消息 [消息类型 : " + messageType + "，消息内容 : " + message + "]");
			}

		};
	}

	/** 初始化OnStopTraceListener */
	private void initOnStopTraceListener() {
		// 初始化stopTraceListener
		stopTraceListener = new OnStopTraceListener() {

			// 轨迹服务停止成功
			public void onStopTraceSuccess() {
				LogUtils.i("停止轨迹服务成功");
			}

			// 轨迹服务停止失败（errorNo : 错误编码，msg : 消息内容，详情查看类参考）
			public void onStopTraceFailed(int errorNo, String msg) {
				LogUtils.i("停止轨迹服务接口消息 [错误编码 : " + errorNo + "，消息内容 : " + msg + "]");
			}
		};
	}

	public void start(View v) {
		client.startTrace(trace, startTraceListener);
	}

	public void stop(View v) {
		client.stopTrace(trace, stopTraceListener);
	}

	public void setName(View v) {
		trace = new Trace(mContext, serviceId, "忽悠宝宝强1", traceType);
		client.startTrace(trace, startTraceListener);
	}
}
