/**
 * 
 */
package com.yibu.kuaibu.adapterly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.gson.SupplyItemGson;
import com.yibu.kuaibu.bean.gson.SupplyItemGson.SupplyItem;
import com.yibu.kuaibu.ui.swipexlistview.swipe.BaseSwipeAdapter;


public class ScGongyingXinxiListAdapter extends BaseSwipeAdapter
{
	private Context context;
	private SupplyItemGson supplyItemGson;

	public ScGongyingXinxiListAdapter(Context context, SupplyItemGson supplyItemGson)
	{
		super();
		this.context = context;
		this.supplyItemGson = supplyItemGson;
	}

	@Override
	public int getCount()
	{
		return supplyItemGson.data.rslist.size();
	}

	@Override
	public Object getItem(int position)
	{
		return supplyItemGson.data.rslist.get(position);
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

		SupplyItem supplyItem = supplyItemGson.data.rslist.get(position);
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

	private static class ViewHolder
	{
		TextView title;
		TextView type;
		TextView state;
		TextView time;
		ImageView vip_mark;
	}

	public void setSupplyItemGson(SupplyItemGson supplyItemGson)
	{
		this.supplyItemGson = supplyItemGson;
	}

}
