package com.castle.cooltask.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * ����Adapter<br>
 * ʹ�����ӣ�<br>
 * lv_goods.setAdapter(new
 * CommonAdapter&lt;GoodsInfo&gt;(context,list,R.layout.item_exchange_goods) {<br>
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
public abstract class CommonAdapter<T> extends BaseAdapter {
	/**
	 * �����?
	 */
	protected LayoutInflater mInflater;
	/**
	 * ������
	 */
	protected Context mContext;
	/**
	 * ����
	 */
	protected List<T> mDatas;
	/**
	 * Item����
	 */
	protected final int mItemLayoutId;

	public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
		convert(viewHolder, getItem(position),position);
		return viewHolder.getConvertView();

	}

	protected ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
	}

	public abstract void convert(ViewHolder helper, T item,int position);
}
