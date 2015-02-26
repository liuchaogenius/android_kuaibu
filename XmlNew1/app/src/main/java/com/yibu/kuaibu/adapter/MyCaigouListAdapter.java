/**
 * 
 */
package com.yibu.kuaibu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.gson.CaigouItemGson;
import com.yibu.kuaibu.bean.gson.CaigouItemGson.SupplyItem;
import com.yibu.kuaibu.ui.swipexlistview.swipe.BaseSwipeAdapter;
import com.yibu.kuaibu.ui.swipexlistview.swipe.SwipeLayout;

/**
 * @author liujian
 * 
 */
public class MyCaigouListAdapter extends BaseSwipeAdapter
{
	private CaigouItemGson caigouItemGson;
	private Context context;

	public MyCaigouListAdapter(CaigouItemGson caigouItemGson, Context context)
	{
		super();
		this.caigouItemGson = caigouItemGson;
		this.context = context;
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
		View v = LayoutInflater.from(context).inflate(R.layout.my_caigou_listitem,
				parent, false);
		final SwipeLayout swipeLayout = (SwipeLayout) v
				.findViewById(getSwipeLayoutResourceId(position));
		// 添加删除布局的点击事件
				v.findViewById(R.id.ll_delete).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Toast.makeText(context, "delete", Toast.LENGTH_SHORT).show();
						// 点击完成之后，关闭删除menu
						swipeLayout.close();
//						list.remove(position);
//						noti();
		
					}
				});
				
				v.findViewById(R.id.ll_updata).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Toast.makeText(context, "updata", Toast.LENGTH_SHORT).show();
						// 点击完成之后，关闭删除menu
						swipeLayout.close();
//						list.remove(position);
//						noti();
		
					}
				});
		
		return v;
	}

	@Override
	public void fillValues(int position, View convertView)
	{
		ViewHolder holder;
		holder = new ViewHolder();
		convertView = View.inflate(context, R.layout.my_caigou_listitem, null);
		convertView.setTag(holder);

		holder.title = (TextView) convertView.findViewById(R.id.title);
		holder.type = (TextView) convertView.findViewById(R.id.type);
		holder.state = (TextView) convertView.findViewById(R.id.state);
		holder.time = (TextView) convertView.findViewById(R.id.time);
		holder.vip_mark = (ImageView) convertView.findViewById(R.id.vip_mark);

		SupplyItem supplyItem = caigouItemGson.data.rslist.get(position);
		holder.title.setText(supplyItem.title);
		holder.type.setText(supplyItem.catname);
		holder.state.setText("状态：" + supplyItem.typename);
		holder.time.setText(supplyItem.editdate);
		if (supplyItem.vip == 1)
		{
			holder.vip_mark.setVisibility(View.VISIBLE);
		} else if (supplyItem.vip == 0)
		{
			holder.vip_mark.setVisibility(View.GONE);
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

	public void setCaigouItemGson(CaigouItemGson caigouItemGson)
	{
		this.caigouItemGson = caigouItemGson;
	}
	
	

}
