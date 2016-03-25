package com.example.androidtest.utils;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * @author
 * @version 2016-1-13下午2:16:22
 * @description 动画工具类
 */

public class AnimationUtils {

	/**
	 * 从控件所在位置移动到控件的底部
	 */
	public static TranslateAnimation moveToViewBottom() {
		TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
		mHiddenAction.setDuration(500);
		return mHiddenAction;
	}

	/**
	 * 从控件的底部移动到控件所在位置
	 */
	public static TranslateAnimation moveToViewLocation() {
		TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		mHiddenAction.setDuration(500);
		return mHiddenAction;
	}

}
