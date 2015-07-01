package com.castle.cooltask.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

/**
 * ����Adapter<br>
 * ʹ�����ӣ�<br>
 * lv_goods.setAdapter(new
 * CommonAdapter&lt;GoodsInfo&gt;(context,list,R.layout.item_exchange_goods,listView) {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;public void convert(ViewHolder holder, GoodsInfo
 * info) {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;holder.setImageResource(R.id.
 * iv_img,info.getImg());<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;holder.setText(R.id.tv_title,
 * info.getTitle());<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;holder.setText(R.id.tv_point,
 * info.getPoint());<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
 * });<br>
 **/
public abstract class AdaptiveCommonAdapter<T> extends CommonAdapter {
	
	private AbsListView absListView;
	
	private int size = 0;

	public AdaptiveCommonAdapter(Context context, List<T> mDatas, int itemLayoutId,AbsListView absListView) {
		super(context, mDatas, itemLayoutId);
		this.absListView = absListView;
	}
	
	public AdaptiveCommonAdapter(Context context, List<T> mDatas, int itemLayoutId,AbsListView absListView,int size) {
		this(context,  mDatas, itemLayoutId,absListView);
		this.size = size;
		
	}
	
	@SuppressLint("NewApi") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
		convert(viewHolder, getItem(position),position);
		int absListViewHight = absListView.getHeight();
		if(mDatas.size()!= 0){
			int size = mDatas.size();
			if(absListView instanceof GridView){
				
				double doubleSize = size;
				double doubleColumns = ((GridView)absListView).getNumColumns();
				
				size = (int) Math.ceil(doubleSize/doubleColumns);
			}
			if (this.size != 0){
				size = this.size;
			}
			int itemHight = absListViewHight/size;
			convertView = viewHolder.getConvertView();
			LayoutParams params = convertView.getLayoutParams();
			params.height = itemHight;
			convertView.setLayoutParams(params);
		}
	
		return viewHolder.getConvertView();

	}
	
	
	

}
