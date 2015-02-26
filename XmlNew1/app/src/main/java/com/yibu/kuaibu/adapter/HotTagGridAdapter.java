/**
 * 
 */
package com.yibu.kuaibu.adapter;

import com.lidroid.xutils.db.sqlite.CursorUtils.FindCacheSequence;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.gson.HotTagGson;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HotTagGridAdapter extends BaseAdapter
{
	private HotTagGson hotTagGson;
	private Context context;

	public HotTagGridAdapter(HotTagGson hotTagGson, Context context)
	{
		super();
		this.hotTagGson = hotTagGson;
		this.context = context;
	}

	@Override
	public int getCount()
	{
		return hotTagGson.data.taglist.size();

	}

	@Override
	public Object getItem(int position)
	{
		return hotTagGson.data.taglist.get(position);
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
    		convertView = View.inflate(context, R.layout.input_title_name_griditem,
    				null);
    		holder.tag1 = (TextView) convertView.findViewById(R.id.tag1);
    		
    		convertView.setTag(holder);
        }else{
        	holder = (ViewHolder) convertView.getTag();
        }
		

		// int count = 0+position*4;

		holder.tag1.setText(hotTagGson.data.taglist.get(position));

		return convertView;
	}

	private static class ViewHolder
	{
		TextView tag1;

	}

}
