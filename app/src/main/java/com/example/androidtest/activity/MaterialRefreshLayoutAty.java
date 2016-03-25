package com.example.androidtest.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.androidtest.DividerItemDecoration;
import com.example.androidtest.R;
import com.example.androidtest.adapter.RecyclerViewAdapter;
import com.example.androidtest.base.BaseActivity;
import com.example.androidtest.entity.GoodsEntity;

/**
 * @author
 * @version 2016-3-1下午2:02:31
 * @description
 */

public class MaterialRefreshLayoutAty extends BaseActivity {
	private MaterialRefreshLayout mRefreshLayout;
	private RecyclerView mRecyclerView;

	private List<GoodsEntity> datas = new ArrayList<>();
	private RecyclerViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_materialrefreshlayout);
		setData();

		mRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.materialRefreshLayout);
		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		LinearLayoutManager manager = new LinearLayoutManager(this);
		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

		adapter = new RecyclerViewAdapter(datas);
		mRecyclerView.setAdapter(adapter);

		mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {

			@Override
			public void onRefresh(MaterialRefreshLayout arg0) {
				refresh();
			}

			@Override
			public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
				super.onRefreshLoadMore(materialRefreshLayout);
				onLoad();
			}
		});
	}

	private void setData() {
		for (int i = 0; i < 10; i++) {
			datas.add(new GoodsEntity("第" + i + "次", "殷烨是傻屌"));
		}
	}

	public void refresh() {
		mRecyclerView.postDelayed(new Runnable() {

			@Override
			public void run() {
				datas.add(0, new GoodsEntity("你大爷的", "我崩溃了"));
				adapter.notifyDataSetChanged();
				mRefreshLayout.finishRefresh();
			}
		}, 3000);
	}

	private void onLoad() {
		mRecyclerView.postDelayed(new Runnable() {

			@Override
			public void run() {
				List<GoodsEntity> moreList = new ArrayList<>();
				for (int i = 1; i < 11; i++) {
					moreList.add(new GoodsEntity(i + "", "大爷，我崩溃了" + i + "次"));
				}
				datas.addAll(moreList);
				adapter.notifyDataSetChanged();
				mRefreshLayout.finishRefreshLoadMore();
			}
		}, 3000);
	}

}
