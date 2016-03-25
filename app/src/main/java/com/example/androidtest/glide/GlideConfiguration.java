package com.example.androidtest.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 * @author
 * @version 2016-1-30下午2:21:35
 * @description
 */

public class GlideConfiguration implements GlideModule {

	@Override
	public void applyOptions(Context context, GlideBuilder builder) {
		// Apply options to the builder here.
		builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
	}

	@Override
	public void registerComponents(Context context, Glide glide) {
		// register ModelLoaders here.
	}

}
