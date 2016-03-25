package com.example.androidtest.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtest.R;
import com.example.androidtest.Result;
import com.example.androidtest.base.COBaseAdapter;
import com.example.androidtest.callback.VolleyCallBack;
import com.example.androidtest.entity.GoodsEntity;
import com.example.androidtest.entity.User;
import com.example.androidtest.loader.LoadImage;
import com.example.androidtest.utils.GsonUtils;
import com.example.androidtest.utils.LoadingUtils;
import com.example.androidtest.utils.SPUtils;
import com.example.androidtest.utils.ScreenSizeUtils;
import com.example.androidtest.utils.Utils;
import com.example.androidtest.utils.VolleyUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.util.LogUtils;

import de.greenrobot.event.EventBus;

public class MainAty extends Activity implements OnClickListener {
	protected static final String TAG = MainAty.class.getSimpleName();

	private ProgressBar mPBar;

	private TextView textView;

	private ListView listView;
	private List<User> userList = new ArrayList<User>();

	private Button button;
	private RelativeLayout layout;
	private LinearLayout linearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main);
		EventBus.getDefault().register(this);
		textView = (TextView) findViewById(R.id.textView);
		textView.setOnClickListener(this);
		listView = (ListView) findViewById(R.id.listView);
		layout = (RelativeLayout) findViewById(R.id.relativeLayout);
		linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
		linearLayout.setOnClickListener(this);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);

		int px2dip = Utils.px2dip(this, 20);
		int px2sp = Utils.px2sp(this, 20);
		int dip2px = Utils.dip2px(this, 20);
		textView.setText("px2dip:" + px2dip + "\npx2sp:" + px2sp + "\ndip2px:" + dip2px);

		mPBar = LoadingUtils.createProgressBar(this, null);

		Log.i(TAG, ScreenSizeUtils.getScreenHeight(this) + "");

		User user = new User();
		user.setAge(18);
		user.setName("Andy");
		SPUtils.putObject(this, "sp", "sp", user);
		User u = (User) SPUtils.getObject(this, "sp", "sp");
		Log.i(TAG, u.getAge() + "");
		Log.i(TAG, u.getName() + "");

//		WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//		Display display = manager.getDefaultDisplay();
//		Log.i(TAG, "已经弃用的方法值：" + display.getWidth() + "---" + display.getHeight());

		DisplayMetrics dm = getResources().getDisplayMetrics();
		Log.i(TAG, dm.widthPixels + "---" + dm.heightPixels);

		User a = new User();
		a.setName("http://pic.baike.soso.com/p/20130829/20130829094104-1901011238.jpg");
		userList.add(a);
		User b = new User();
		b.setName("http://img3.3lian.com/2014/f1/2/d/11.jpg");
		userList.add(b);
		User c = new User();
		c.setName("http://img3.3lian.com/2014/f1/2/d/9.jpg");
		userList.add(c);
		User d = new User();
		d.setName("http://f.hiphotos.baidu.com/zhidao/pic/item/c8177f3e6709c93d759982a99f3df8dcd1005400.jpg");
		userList.add(d);
		User e = new User();
		e.setName("http://www.qqkw.net/uploads/allimg/150316/1-1503161J910.jpg");
		userList.add(e);
		GlideAdapter adapter = new GlideAdapter(this, userList);
		listView.setAdapter(adapter);

		Animation animation_in = AnimationUtils.loadAnimation(this, R.anim.animation_enterfromleft);
		layout.setAnimation(animation_in);

//		final Animation translateAnimation = new TranslateAnimation(-100f, 0, 0, 0); // 移动动画效果
//		translateAnimation.setDuration(800); // 设置动画持续时间
//		translateAnimation.setFillAfter(true); // 保留在终止位置
//		translateAnimation.setFillEnabled(true); // 使能填充效果
//		listView.setVisibility(View.VISIBLE);
//		listView.setAnimation(translateAnimation); // 设置动画效果
//		translateAnimation.startNow();                  //启动动画    
//		translateAnimation.cancel();

		login();
	}

	private void login() {
		String url = "http://120.26.79.15:8180/Common/GetGoodsList";
		HashMap<String, String> parms = new HashMap<String, String>();
		parms.put("goodsTypeId", "4");
		parms.put("areaName", "上海市");
		VolleyUtils.postStringRequest(url, parms, false, TAG, new VolleyCallBack() {

			@Override
			public void httpSucceed(String content) {
				// TODO Auto-generated method stub
				Result result = Result.standardParse(content);
				if (result.isValidate()) {
					Type listType = new TypeToken<LinkedList<GoodsEntity>>() {
					}.getType();
					List<GoodsEntity> mList = GsonUtils.json2List(result.getMsg(), listType);
					LogUtils.i(mList.size() + "");
					List<GoodsEntity> mList2 = GsonUtils.json2List(result.getMsg(), GoodsEntity.class);
					LogUtils.i(mList2.size() + "");
				}
//				LoginInfoEntity entity = fromJsonToObj(content, LoginInfoEntity.class);
//				LogUtils.i(entity.getInfo().getStationInfo().getStation_name());
			}

			@Override
			public void httpLoading() {
				// TODO Auto-generated method stub

			}

			@Override
			public void httpFail(String error) {
				// TODO Auto-generated method stub

			}
		});
	}

	public static <T> T fromJsonToObj(String json, Class<T> cls) {
		T result = null;
		try {
			Gson gson = new Gson();
			result = (T) gson.fromJson(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.i("json数据解析错误：" + e.toString());
		}
		return result;
	}

	@Override
	public void onClick(View v) {
		if (v == textView) {
//			if (!Utils.isFastDoubleClick()) {
//				textView.setText("我被点击啦");
//			} else {
//				Log.i(TAG, "尼妹的，你都点过好几次了");
//			}
			EventBus.getDefault().post(new User("宝宝", 23));
			post();
			listView.setVisibility(View.VISIBLE);
			Animation out = AnimationUtils.loadAnimation(MainAty.this, R.anim.animation_enterfromleft);
			layout.setAnimation(out);
		} else if (v == button) {
			listView.setVisibility(View.GONE);
//			Animation out = AnimationUtils.loadAnimation(MainAty.this, R.anim.animation_fadefromleft);
//			listView.setAnimation(out);
//			out.setAnimationListener(new AnimationListener() {
//				
//				@Override
//				public void onAnimationStart(Animation arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void onAnimationRepeat(Animation arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void onAnimationEnd(Animation arg0) {
//					listView.setVisibility(View.GONE);
//				}
//			});
		} else if (v == linearLayout) {
			listView.setVisibility(View.GONE);
		}
	}

	public void onEventMainThread(User user) {
		Toast.makeText(this, user.getName() + "你TMD触发我了", Toast.LENGTH_SHORT).show();
	}

	private void post() {
		HashMap<String, String> parms = new HashMap<String, String>();
		parms.put("appType", "xiaoge");
		VolleyUtils.postStringRequest("http://192.168.40.157:9008/Admin/VersionUpgrade", parms, false, TAG,
				new VolleyCallBack() {

					@Override
					public void httpSucceed(String content) {
						mPBar.setVisibility(View.GONE);
						textView.setText(content);
						LogUtils.i(content);
					}

					@Override
					public void httpLoading() {
						mPBar.setVisibility(View.VISIBLE);
					}

					@Override
					public void httpFail(String error) {
						mPBar.setVisibility(View.GONE);
						textView.setText(error);
						LogUtils.i(error);
					}
				});
	}

	private static class GlideAdapter extends COBaseAdapter<User> {
		private Context mContext;

		public GlideAdapter(Context mContext, List<User> dataList) {
			super(dataList);
			this.mContext = mContext;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LayoutInflater.from(mContext).inflate(R.layout.listv_item_main, parent, false);
				holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			User user = getData(position);
			if (user != null) {/*
								 * Glide.with(mContext) .load(user.getName())
								 * .error(R.drawable.android)// load失败的Drawable
								 * .placeholder(R.drawable.android)//
								 * loading时候的Drawable // .animate(null)//
								 * 设置load完的动画 // .centerCrop()// 中心切圆，会填满
								 * .fitCenter()// 中心fit，以原本图片的长宽为主 //
								 * .thumbnail(0.1f)// 縮略圖, 0.1f就是原本的十分之一
								 * .into(holder.imageView);
								 */
				LoadImage.displayNet(holder.imageView, user.getName(), R.drawable.android);
			}
			return convertView;
		}

		final static class ViewHolder {
			ImageView imageView;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
