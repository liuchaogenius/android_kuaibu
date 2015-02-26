package com.yibu.kuaibu.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class SshangjituijAdapter extends BaseAdapter
{

	private Context mContext;
	private Integer[] mThumbIds;

	public SshangjituijAdapter(Context c, Integer[] tmThumbIds)
	{
		// TODO Auto-generated constructor stub
		mContext = c;
		mThumbIds = tmThumbIds;
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return mThumbIds.length;
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		ImageView imageview;
		if (convertView == null)
		{
			imageview = new ImageView(mContext);
			imageview.setLayoutParams(new GridView.LayoutParams(85, 85));
//			imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
//			imageview.setPadding(8, 8, 8, 8);
		} else
		{
			imageview = (ImageView) convertView;
		}
//		imageview.setImageResource(mThumbIds[position]);
		imageview.setBackgroundResource(mThumbIds[position]);
		return imageview;
	}

}
