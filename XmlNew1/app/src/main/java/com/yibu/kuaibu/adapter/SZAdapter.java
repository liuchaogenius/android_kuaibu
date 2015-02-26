package com.yibu.kuaibu.adapter;

import com.yibu.kuaibu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

class SetType
{
	int mContent;
	int mType;
//	OnCheckedChangeListener mOnCheckedChangeListener;

	public SetType(int con, int type)
	{
		this.mContent = con;
		this.mType = type;
	}

	public SetType(int con, int type,
			OnCheckedChangeListener mOnCheckedChangeListener)
	{
		this.mContent = con;
		this.mType = type;
//		this.mOnCheckedChangeListener = mOnCheckedChangeListener;
	}

}

public class SZAdapter extends BaseAdapter
{

	

	private LayoutInflater inflater;
	Context mContext;
	SetType ar[];

	public SZAdapter(Context m)
	{
		mContext = m;
		inflater = LayoutInflater.from(m);
	}

	public SZAdapter(Context m, SetType a[])
	{
		mContext = m;
		inflater = LayoutInflater.from(m);
		ar = a;
	}

	@Override
	public int getCount()
	{
		return ar.length;
	}

	@Override
	public Object getItem(int position)
	{
		return ar[position];
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		convertView = inflater.inflate(R.layout.item, null);
		TextView tv = (TextView) convertView.findViewById(R.id.text);// ����Բ��listviewһ�㶼�ǹ̶��ļ�������������û�����Ż����?��Ҫ�Ļ�������
		ImageView mTriangle = (ImageView) convertView
				.findViewById(R.id.triangle);
//		CheckSwitchButton mCheckSwitchButton = (CheckSwitchButton) convertView
//				.findViewById(R.id.mCheckSwithcButton);

		tv.setText(ar[position].mContent);

		

		if (ar.length == 1)
		{
			setBackgroundDrawable(convertView, R.drawable.list_round_selector);

		} else if (ar.length == 2)
		{
			if (position == 0)
			{
				setBackgroundDrawable(convertView, R.drawable.list_top_selector);
			} else if (position == ar.length - 1)
			{
				setBackgroundDrawable(convertView,
						R.drawable.list_bottom_selector);
			}
		} else
		{
			if (position == 0)
			{
				setBackgroundDrawable(convertView, R.drawable.list_top_selector);
			} else if (position == ar.length - 1)
			{
				setBackgroundDrawable(convertView,
						R.drawable.list_bottom_selector);
			} else
			{
				setBackgroundDrawable(convertView,
						R.drawable.list_rect_selector);
			}
		}
		// convertView.setOnClickListener(new (position));
		return convertView;
	}

	private void setBackgroundDrawable(View view, int resID)
	{
		view.setBackgroundDrawable(mContext.getResources().getDrawable(resID));
	}

}
