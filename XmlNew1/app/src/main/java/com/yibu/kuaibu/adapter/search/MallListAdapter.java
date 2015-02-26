/**
 * 
 */
package com.yibu.kuaibu.adapter.search;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.gson.MallListGson;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MallListAdapter extends BaseAdapter
{
	private Context context;
	private MallListGson mallListGson;
	
	public MallListAdapter(Context context, MallListGson mallListGson)
	{
		super();
		this.context = context;
		this.mallListGson = mallListGson;
	}

	@Override
	public int getCount()
	{
		return mallListGson.data.rslist.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mallListGson.data.rslist.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.mall_list_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.price = (TextView) convertView.findViewById(R.id.price);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.price.setText("￥"+mallListGson.data.rslist.get(position).price);
		holder.title.setText(mallListGson.data.rslist.get(position).title);
		return convertView;
	}
	
	private static class ViewHolder{
		ImageView image;
		TextView title;
		TextView price;
	}

	public void setMallListGson(MallListGson mallListGson)
	{
		this.mallListGson = mallListGson;
	}
	
}
