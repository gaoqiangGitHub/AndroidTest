package com.example.androidtest.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.androidtest.DividerItemDecoration;
import com.example.androidtest.R;
import com.example.androidtest.Result;
import com.example.androidtest.adapter.HeaderViewRecyclerAdapter;
import com.example.androidtest.adapter.RecyclerViewAdapter;
import com.example.androidtest.adapter.RecyclerViewAdapter.OnRecyclerViewItemClickListener;
import com.example.androidtest.base.BaseActivity;
import com.example.androidtest.callback.RecyclerViewOnScrollListener;
import com.example.androidtest.callback.VolleyCallBack;
import com.example.androidtest.entity.GoodsEntity;
import com.example.androidtest.utils.GsonUtils;
import com.example.androidtest.utils.VolleyUtils;

/**
 * @author
 * @version 2016-2-25下午4:10:35
 * @description
 */

public class RecyclerViewAty extends BaseActivity {
	protected static final String TAG = RecyclerViewAty.class.getSimpleName();

	private SwipeRefreshLayout mSwipeRefreshLayout;

	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLinearLayoutManager;
	private GridLayoutManager mGridLayoutManager;;
	private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
	private List<GoodsEntity> goodsList;
	private RecyclerViewAdapter adapter = null;
	private HeaderViewRecyclerAdapter recyclerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_recyclerview);

		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
		mSwipeRefreshLayout.setColorSchemeResources(R.color.black, R.color.green, R.color.red);
		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				goodsList.add(0, new GoodsEntity("你大爷的", "我崩溃了"));
				adapter.notifyDataSetChanged();
				mSwipeRefreshLayout.setRefreshing(false);
			}
		});

		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

		// 创建默认的线性LayoutManager
		mLinearLayoutManager = new LinearLayoutManager(this);
		// 创建Grid布局
		mGridLayoutManager = new GridLayoutManager(this, 3);
		// 创建瀑布流布局
		mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);

//		mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);// 竖向的List
//		mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);// 横向的List
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
		mRecyclerView.setHasFixedSize(true);
		// 设置Item增加和移除动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		// 添加分割线
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//		mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

		mRecyclerView.setOnScrollListener(new RecyclerViewOnScrollListener(mLinearLayoutManager) {

			@Override
			public void onLoadMore(int currentPage) {
				onLoad();
			}
		});

		initData();
	}

	private void initData() {
		String url = "http://120.26.79.15:8180/Common/GetGoodsList";
		HashMap<String, String> parms = new HashMap<String, String>();
		parms.put("goodsTypeId", "4");
		parms.put("areaName", "上海市");
		VolleyUtils.postStringRequest(url, parms, false, TAG, new VolleyCallBack() {

			@Override
			public void httpSucceed(String content) {
				Result result = Result.standardParse(content);
				if (result.isValidate()) {
					goodsList = GsonUtils.json2List(result.getMsg(), GoodsEntity.class);
					adapter = new RecyclerViewAdapter(goodsList);
//					mRecyclerView.setAdapter(adapter);

					recyclerAdapter = new HeaderViewRecyclerAdapter(adapter);
					mRecyclerView.setAdapter(recyclerAdapter);
					createLoadMoreView();

					adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {

						@Override
						public void onItemClick(View view, int position, GoodsEntity data) {
							Toast.makeText(RecyclerViewAty.this, data.getTitle(), Toast.LENGTH_SHORT).show();
//							GoodsEntity entity = new GoodsEntity();
//							entity.setTitle("大爷");
//							entity.setMemo("我TMD崩溃了");
//							addData(entity, 2);
//							adapter.notifyDataSetChanged();
						}

						@Override
						public void onItemLongClick(View view, int position, GoodsEntity data) {
							Toast.makeText(RecyclerViewAty.this, "当前点击的位置：" + position, Toast.LENGTH_SHORT).show();
						}
					});
				}
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

	private void createLoadMoreView() {
		View loadMoreView = LayoutInflater.from(RecyclerViewAty.this).inflate(R.layout.refreshview_footer_layout,
				mRecyclerView, false);
		recyclerAdapter.addFooterView(loadMoreView);
	}

	/** 添加数据 */
	private void addData(GoodsEntity content, int position) {
		goodsList.add(position, content);
		adapter.notifyItemInserted(position);
	}

	/** 删除数据 */
	private void removeData(int position) {
		goodsList.remove(position);
		adapter.notifyItemRemoved(position);
	}

	private void onLoad() {
		mRecyclerView.postDelayed(new Runnable() {

			@Override
			public void run() {
				List<GoodsEntity> moreList = new ArrayList<>();
				for (int i = 1; i < 11; i++) {
					moreList.add(new GoodsEntity(i + "", "大爷，我崩溃了" + i + "次"));
				}
				goodsList.addAll(moreList);
				adapter.notifyDataSetChanged();
			}
		}, 5000);
	}

}
