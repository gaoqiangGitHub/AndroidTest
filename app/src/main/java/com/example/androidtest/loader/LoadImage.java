package com.example.androidtest.loader;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;

import com.example.androidtest.R;
import com.example.androidtest.utils.ValueUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

/**
 * @author
 * @version 2016-1-30下午4:25:43
 * @description 图片加载类
 */

public class LoadImage {

	private static ImageLoader mImageLoader = null;
	private static DisplayImageOptions options = null;

	/** 获取ImageLoader对象 */
	public static ImageLoader getImageLoader() {
		return mImageLoader;
	}

	/**
	 * 初始化
	 * 
	 * @param context 上下文
	 */
	public static void init(Context context) {
		File imageCacheFolder = StorageUtils.getOwnCacheDirectory(context, "/zzz/imageCache/");// 设置缓存目录
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
				.memoryCacheExtraOptions(300, 300)// 即保存的每个缓存文件的最大长宽
				.threadPoolSize(3)// 线程池内加载的数量
				.denyCacheImageMultipleSizesInMemory()// 当同一个URL获取不同大小的图片，缓存到内存时，只缓存一个,默认会缓存多个不同的大小的相同图片
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())// 将保存的时候的URI名称用MD5加密
				.diskCache(new UnlimitedDiskCache(imageCacheFolder))// 自定义缓存路径
				.diskCacheSize(300 * 1024 * 1024)// 设置缓存的大小
				.build();// 开始构建
		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.android)// 设置图片在下载期间显示的图片
				.showImageForEmptyUri(R.drawable.android)// 设置图片URL为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.android)// 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
				.bitmapConfig(Config.RGB_565)// 设置图片的解码类型
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				.displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
				.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
				.build();// 构建完成
		mImageLoader = ImageLoader.getInstance();
		mImageLoader.init(configuration);
	}

	/**
	 * 显示图片
	 * 
	 * @param igv 显示图片的ImageView组件
	 * @param url 图片的地址
	 * @param drawresid 默认显示的图片
	 * 
	 */
	public static void displayNet(ImageView igv, String url, int drawresid) {
		if (url == null || url.trim().equals("null") || url.trim().equals("")) {
			getImageLoader().displayImage("drawable://" + drawresid, igv, options);
		} else {
			getImageLoader().displayImage(url, igv, options);
		}
	}

	/**
	 * 显示手机本地图片
	 * 
	 * @param imageView 显示图片的ImageView组件
	 * @param path 本地图片的绝对路径
	 * @param drawresid 默认显示的图片
	 */
	public static void displayLocal(ImageView imageView, String path, int drawresid) {
		if (ValueUtils.isStrEmpty(path)) {
			getImageLoader().displayImage("drawable://" + drawresid, imageView, options);
		} else {
			getImageLoader().displayImage("file://" + path, imageView, options);
		}
	}

}
