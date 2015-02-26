/**
 * 
 */
package com.yibu.kuaibu.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.CartListItem;
import com.yibu.kuaibu.bean.CartListItem.ChildState;
import com.yibu.kuaibu.bean.CartListItem.GroupState;
import com.yibu.kuaibu.bean.gson.CartItemGson;
import com.yibu.kuaibu.bean.gson.CartItemGson.ChildItem;
import com.yibu.kuaibu.bean.gson.CartItemGson.GroupItem;


public class CartListAdapter extends BaseAdapter implements OnClickListener
{
	private List<CartListItem> list;
	private Context context;
	//private CartItem item;
	public OnChangeSelectListener onChangeSelectListener;
	//private Map<String,Integer> groupstate = new HashMap<String,Integer>();
	//private List<Map<String, Integer>> childstate = new ArrayList<Map<String,Integer>>();
    //private Map<String,Map<String,Integer>> childstate = new HashMap<String,Map<String,Integer>>();
    private List<GroupState> grouplist;
    

	
    public CartListAdapter(List<GroupState> grouplist, Context context)
	{
		super();
		this.grouplist = grouplist;
		this.context = context;
	}

	@Override
	public int getCount()
	{
		// return list.size()*2;
		return grouplist.size() * 2;
	}

	@Override
	public Object getItem(int position)
	{
		if (position % 2 == 0)
		{ // 空白区域
			return null;
		}
		// return list.get(position);
		return grouplist.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		
		int pos = (position - 1) / 2;
		if (position % 2 == 0)
		{
			convertView = View.inflate(context,
					R.layout.fram_cart1_listitem_blank, null);
		} else
		{
			convertView = View.inflate(context, R.layout.fram_cart1_listitem,
					null);
			LinearLayout ll = (LinearLayout) convertView
					.findViewById(R.id.content);
			TextView title = (TextView) convertView
					.findViewById(R.id.title);
			
			ImageButton group = (ImageButton) convertView.findViewById(R.id.groupbtn);
			group.setOnClickListener(this);
			group.setTag(pos);
			//groupstate.put(pos+"", 0);   //为选择状态
			if(grouplist.get(pos).groupstate == 0){
				group.setImageResource(R.drawable.selectno);
			}else{
				group.setImageResource(R.drawable.selected);
			}
			
			
			GroupItem groupItem = grouplist.get(pos).groupItem;
			title.setText(groupItem.company);
			//Map<String,Integer> childmap = new HashMap<String, Integer>();
			for (int i = 0; i < groupItem.cartlist.size(); i++)
			{
				ChildItem chlidItem = grouplist.get(pos).childlist.get(i).childItem;
				View view = View.inflate(context,
						R.layout.fram_cart1_listitem_content, null);
				ll.addView(view);
				
				ImageButton chlid = (ImageButton) view.findViewById(R.id.childbtn);
				chlid.setOnClickListener(this);
				chlid.setTag(pos+"|"+i);
				
				if(grouplist.get(pos).childlist.get(i).childstate == 0){
					chlid.setImageResource(R.drawable.selectno);
				}else{
					chlid.setImageResource(R.drawable.selected);
				}
				
				//childmap.put(i+"", 0);
				
				
				TextView cartname = (TextView) view
						.findViewById(R.id.cartname);
				TextView type = (TextView) view.findViewById(R.id.type);
				TextView price = (TextView) view
						.findViewById(R.id.price);
				TextView number = (TextView) view
						.findViewById(R.id.number);

				cartname.setText(chlidItem.catname);
				type.setText("类别：" + chlidItem.title);
				price.setText("￥" + chlidItem.price);
				number.setText("x" + chlidItem.number);

			}
			//childstate.put(pos+"", childmap);

		}

		return convertView;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.groupbtn:{
			double sum = 0;
			int pos = (Integer)v.getTag();
			
			LinearLayout ll = (LinearLayout) v.getParent().getParent();
			LinearLayout ll1 = (LinearLayout) ll.getChildAt(2);
			int count = ll1.getChildCount();
			if(grouplist.get(pos).groupstate == 0){   //全选
				((ImageButton)v).setImageResource(R.drawable.selected);
				grouplist.get(pos).groupstate = 1;
				
				for(int i=0;i<count;i++){			
					ImageButton chlidbtn = (ImageButton) ((LinearLayout)ll1.getChildAt(i)).getChildAt(0);
					if(grouplist.get(pos).childlist.get(i).childstate == 0){
				    	chlidbtn.setImageResource(R.drawable.selected);
				    	grouplist.get(pos).childlist.get(i).childstate = 1;
				    	sum+=grouplist.get(pos).childlist.get(i).childItem.price;
				    }else{
				    	continue;
				    }	
				}
				onChangeSelectListener.OnChange(sum);
				
			}else{
				((ImageButton)v).setImageResource(R.drawable.selectno);
				grouplist.get(pos).groupstate = 0;
				//反选
				for(int i=0;i<count;i++){
					ImageButton chlidbtn = (ImageButton) ((LinearLayout)ll1.getChildAt(i)).getChildAt(0);
				    if(grouplist.get(pos).childlist.get(i).childstate == 0){
				    	//childstate.get(pos+"").put(i+"", 0);
				    	continue;
				    }else{
				    	chlidbtn.setImageResource(R.drawable.selectno);
				    	grouplist.get(pos).childlist.get(i).childstate = 0;
				    	sum-=grouplist.get(pos).childlist.get(i).childItem.price;
				    }
				}
				onChangeSelectListener.OnChange(sum);
			}
			
			
			break;
		}
		case R.id.childbtn:
			double sum = 0;
			String str = (String) v.getTag();
			String[] strs = str.split("\\|");
			int pos = Integer.parseInt(strs[0]);
			int childid = Integer.parseInt(strs[1]);
			if(grouplist.get(pos).childlist.get(childid).childstate == 0){    //正选
				((ImageButton)v).setImageResource(R.drawable.selected);
				grouplist.get(pos).childlist.get(childid).childstate = 1;
				sum+=grouplist.get(pos).childlist.get(childid).childItem.price;
				onChangeSelectListener.OnChange(sum);
			}else{
				((ImageButton)v).setImageResource(R.drawable.selectno);
				grouplist.get(pos).childlist.get(childid).childstate = 0;
				sum-=grouplist.get(pos).childlist.get(childid).childItem.price;
				onChangeSelectListener.OnChange(sum);
			}
			break;
		}	
	}
	
	public interface OnChangeSelectListener{
		public void OnChange(double result);
	}

	public void setOnChangeSelectListener(
			OnChangeSelectListener onChangeSelectListener)
	{
		this.onChangeSelectListener = onChangeSelectListener;
	}

	public void setGrouplist(List<GroupState> grouplist)
	{
		this.grouplist = grouplist;
	}



}
