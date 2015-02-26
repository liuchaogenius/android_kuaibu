/**
 * 
 */
package com.yibu.kuaibu.adapter;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.gson.CaigouItemGson;
import com.yibu.kuaibu.bean.gson.SupplyItemGson;
import com.yibu.kuaibu.bean.gson.CaigouItemGson.SupplyItem;
import com.yibu.kuaibu.ui.swipexlistview.swipe.BaseSwipeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CheckCaigouListAdapter extends BaseSwipeAdapter
{
	private Context context;
	private CaigouItemGson caigouItemGson;

	public CheckCaigouListAdapter(Context context, CaigouItemGson caigouItemGson)
	{
		super();
		this.context = context;
		this.caigouItemGson = caigouItemGson;
	}

	@Override
	public int getCount()
	{
		return caigouItemGson.data.rslist.size();
	}

	@Override
	public Object getItem(int position)
	{
		return caigouItemGson.data.rslist.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public int getSwipeLayoutResourceId(int position)
	{
		return R.id.myswipe;
	}

	@Override
	public View generateView(int position, ViewGroup parent)
	{
		View v = LayoutInflater.from(context).inflate(
				R.layout.check_caigou_item, parent, false);
		// final SwipeLayout swipeLayout = (SwipeLayout) v
		// .findViewById(getSwipeLayoutResourceId(position));
		return v;
	}

	@Override
	public void fillValues(int position, View convertView)
	{

		//convertView = View.inflate(context, R.layout.check_supply_item, null);

		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView type = (TextView) convertView.findViewById(R.id.type);
		TextView state = (TextView) convertView.findViewById(R.id.state);
		TextView time = (TextView) convertView.findViewById(R.id.time);
		ImageView vip_mark = (ImageView) convertView
				.findViewById(R.id.vip_mark);

		SupplyItem supplyItem = caigouItemGson.data.rslist.get(position);
	    title.setText(supplyItem.title);
		type.setText(supplyItem.catname);
		state.setText("状态：" + supplyItem.typename);
		time.setText(supplyItem.editdate);
		if (supplyItem.vip == 1)
		{
			vip_mark.setVisibility(View.VISIBLE);
		} else if (supplyItem.vip == 0)
		{
			vip_mark.setVisibility(View.GONE);
		}
	}



	public void setCaigouItemGson(CaigouItemGson caigouItemGson)
	{
		this.caigouItemGson = caigouItemGson;
	}

}
