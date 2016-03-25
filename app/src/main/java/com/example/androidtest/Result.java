package com.example.androidtest;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.androidtest.utils.ValueUtils;

public class Result implements Serializable {
	private static final long serialVersionUID = 8810307231352887722L;
	private String msg;
	private boolean validate;
	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/** 老版本返回结果处理 */
	public static Result parse(String string) {
		Result result = new Result();
		if (ValueUtils.isStrNotEmpty(string)) {
			result.setValidate(true);
			result.setMsg(string);
		} else {
			result.setValidate(false);
			result.setMsg("连接超时,请重试");
		}
		return result;
	}

	/** 标准返回结果处理 */
	public static Result standardParse(String string) {
		Result result = new Result();
		if (ValueUtils.isStrNotEmpty(string)) {
			try {
				JSONObject jsonObject = new JSONObject(string);
				if (jsonObject.getString("status").equals("1") || jsonObject.optString("status").equals("true")) {// 返回正确结果
					result.setValidate(true);
					result.setStatus(true);
					result.setMsg(jsonObject.getString("info"));
				} else if (jsonObject.getString("status").equals("0") || jsonObject.optString("status").equals("false")) {// 返回错误结果
//					if (jsonObject.getString("info").equals("身份验证错误，请重新登陆")) {
//						SendBroadcast.getInstance().sendBroadcast(AppContext.context, null,
//								"android.session.failure.RECEIVER");
//					}
					result.setValidate(false);
					result.setStatus(true);
					result.setMsg(jsonObject.getString("info"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
				result.setValidate(false);
				result.setStatus(false);
				result.setMsg("解析出错");
				return result;
			}
		} else {
			result.setValidate(false);
			result.setStatus(false);
			result.setMsg("连接超时,请重试");
		}
		return result;
	}

}
