/**
 * 
 */
package com.yibu.kuaibu.adapter.search;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.gson.CompanyListGson;
import com.yibu.kuaibu.bean.gson.CompanyListGson.ChildItem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CompanyListAdapter extends BaseAdapter
{
	private Context context;
	private CompanyListGson companyListGson;
	
	
	public CompanyListAdapter(Context context, CompanyListGson companyListGson)
	{
		super();
		this.context = context;
		this.companyListGson = companyListGson;
	}

	@Override
	public int getCount()
	{
		return companyListGson.data.rslist.size();
	}

	@Override
	public Object getItem(int position)
	{
		return companyListGson.data.rslist.get(position);
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
			convertView = View.inflate(context, R.layout.company_list_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.vip_mark = (ImageView) convertView.findViewById(R.id.vip_mark);
		    holder.title = (TextView) convertView.findViewById(R.id.title);
		    holder.author = (TextView) convertView.findViewById(R.id.author);
		    holder.state_tv = (TextView) convertView.findViewById(R.id.state_tv);
		    holder.true_tv = (TextView) convertView.findViewById(R.id.true_tv);
		    
		    convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		 ChildItem item = companyListGson.data.rslist.get(position);
		 if(item.groupid > 6){
			 holder.vip_mark.setImageResource(R.drawable.shangc);
		 }else {
			 holder.vip_mark.setImageResource(R.drawable.dianpu);
		 }
		 holder.title.setText(item.company);
		 holder.author.setText("掌柜："+item.truename);
		 holder.state_tv.setText("服务态度："+item.star1);
		 holder.true_tv.setText("描述相符："+item.star2);
		
		return convertView;
	}
	
	private static class ViewHolder{
		ImageView image;
		ImageView vip_mark;
		TextView title;
		TextView author;
		TextView state_tv;
		TextView true_tv;
	}

	public void setCompanyListGson(CompanyListGson companyListGson)
	{
		this.companyListGson = companyListGson;
	}
	
	

}
