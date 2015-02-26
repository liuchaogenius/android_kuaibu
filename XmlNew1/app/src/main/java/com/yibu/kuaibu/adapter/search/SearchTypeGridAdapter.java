/**
 * 
 */
package com.yibu.kuaibu.adapter.search;

import java.util.List;

import com.lidroid.xutils.db.sqlite.CursorUtils.FindCacheSequence;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.gson.HotTagGson;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SearchTypeGridAdapter extends BaseAdapter
{
	private List<String> list;
	private Context context;

	public SearchTypeGridAdapter(List<String> list, Context context)
	{
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount()
	{
		return list.size();

	}

	@Override
	public Object getItem(int position)
	{
		return list.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder;
        if(convertView == null){
        	holder = new ViewHolder();
    		convertView = View.inflate(context, R.layout.search_gridview_item,
    				null);
    		holder.tag1 = (TextView) convertView.findViewById(R.id.name);
    		
    		convertView.setTag(holder);
        }else{
        	holder = (ViewHolder) convertView.getTag();
        }
		

		// int count = 0+position*4;

		holder.tag1.setText(list.get(position));

		return convertView;
	}

	private static class ViewHolder
	{
		TextView tag1;

	}

	public void setList(List<String> list)
	{
		this.list = list;
	}

}
