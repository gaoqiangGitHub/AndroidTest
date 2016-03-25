package com.example.androidtest.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * @author
 * @version 2015-12-23下午8:39:17
 * @description 菊花工具类
 */

public class LoadingUtils {

	/**
	 * 在屏幕上添加一个转动的小菊花(传说中的Loading),默认为隐藏状态
	 * 注意：务必保证此方法在setContentView()方法后调用,否则小菊花将会处于最底层,被屏幕其他View给覆盖
	 * 
	 * @param activity 需要添加菊花的Activity
	 * @param customIndeterminateDrawable 自定义的菊花图片,可以为null,此时为系统默认菊花
	 * @return progressBar(菊花对象)
	 */
	public static ProgressBar createProgressBar(Activity activity, Drawable customDrawable) {
		// activity根部的ViewGroup,其实是一个FrameLayout
		FrameLayout rootContainer = (FrameLayout) activity.findViewById(android.R.id.content);
		// 给progressBar准备一个FrameLayout的LayoutParams
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		// 设置对其方式为：屏幕居中对其
		lp.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;

		ProgressBar progressBar = new ProgressBar(activity);
		progressBar.setVisibility(View.GONE);
		progressBar.setLayoutParams(lp);
		// 自定义小菊花
		if (customDrawable != null) {
			progressBar.setIndeterminateDrawable(customDrawable);
		}
		// 将菊花添加到FrameLayout中
		rootContainer.addView(progressBar);
		return progressBar;
	}

}
