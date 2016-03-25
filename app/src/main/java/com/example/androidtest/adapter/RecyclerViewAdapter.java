package com.example.androidtest.adapter;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidtest.R;
import com.example.androidtest.R.id;
import com.example.androidtest.R.layout;
import com.example.androidtest.entity.GoodsEntity;

/**
 * @author
 * @version 2016-2-25下午4:40:36
 * @description
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> implements
		View.OnClickListener, View.OnLongClickListener {
	public interface OnRecyclerViewItemClickListener {
		void onItemClick(View view, int position, GoodsEntity data);

		void onItemLongClick(View view, int position, GoodsEntity data);
	}

	private List<GoodsEntity> datas;
	private OnRecyclerViewItemClickListener mOnItemClickListener = null;

	public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}

	public RecyclerViewAdapter(List<GoodsEntity> datas) {
		this.datas = datas;
	}

	// 获取数据的数量
	@Override
	public int getItemCount() {
		return datas.size();
	}

	// 将数据与界面进行绑定的操作
	@Override
	public void onBindViewHolder(RecyclerViewHolder viewHolder, final int position) {
		viewHolder.title.setText(datas.get(position).getTitle());
		viewHolder.memo.setText(datas.get(position).getMemo());

		// 将数据保存在itemView的Tag中，以便点击时进行获取
		viewHolder.itemView.setTag(position);

		// 如果设置了回调，则设置点击事件
//		if (mOnItemClickListener != null) {
//			viewHolder.itemView.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					mOnItemClickListener.onItemClick(v, position, datas.get(position));
//				}
//			});
//		}
	}

	// 创建新View，被LayoutManager所调用
	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listv_item_recyclerview, viewGroup,
				false);
		RecyclerViewHolder vh = new RecyclerViewHolder(view);
		// 将创建的View注册点击事件
		view.setOnClickListener(this);
		// 将创建的view注册长按点击事件
		view.setOnLongClickListener(this);
		return vh;
	}

	// 自定义的ViewHolder，持有每个Item的的所有界面元素
	class RecyclerViewHolder extends RecyclerView.ViewHolder {
		public TextView title;
		public TextView memo;

		public RecyclerViewHolder(View view) {
			super(view);
			title = (TextView) view.findViewById(R.id.title);
			memo = (TextView) view.findViewById(R.id.memo);
		}
	}

	@Override
	public void onClick(View v) {
		if (mOnItemClickListener != null) {
			// 注意这里使用getTag方法获取数据
			mOnItemClickListener.onItemClick(v, (int) v.getTag(), datas.get((int) v.getTag()));
		}
	}

	@Override
	public boolean onLongClick(View v) {
		if (mOnItemClickListener != null) {
			mOnItemClickListener.onItemLongClick(v, (int) v.getTag(), datas.get((int) v.getTag()));
		}
		return true;
	}
}
