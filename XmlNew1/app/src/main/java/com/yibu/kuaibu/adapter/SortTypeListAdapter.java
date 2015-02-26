/**
 * 
 */
package com.yibu.kuaibu.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.gson.SortTypeGson;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author liujian
 *
 */
public class SortTypeListAdapter extends BaseAdapter implements OnClickListener
{
	private SortTypeGson sortTypeGson;
	private Context context;
	private Map<String,String> selected = new HashMap<String,String>();
	
	

	public SortTypeListAdapter(SortTypeGson sortTypeGson, Context context)
	{
		super();
		this.sortTypeGson = sortTypeGson;
		this.context = context;
	}

	@Override
	public int getCount()
	{
		return sortTypeGson.data.size()*2;
	}

	@Override
	public Object getItem(int position)
	{
		if(position % 2 == 0){
			return sortTypeGson.data.get(position);
		}else{
			return null;
		}
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		int pos = position/2;    //对应data位置
		
		if(position%2 == 0){
			convertView = View.inflate(context, R.layout.sort_type_listcontent, null);
			TextView title = (TextView) convertView.findViewById(R.id.title);
			title.setText(sortTypeGson.data.get(pos).catname);
			LinearLayout child_content = (LinearLayout) convertView.findViewById(R.id.child_content);
			int count = 0;
			if(sortTypeGson.data.get(pos).subcate.size()%4 == 0){  // 计算行数
				count = sortTypeGson.data.get(pos).subcate.size()/4; 
			}else{
				count = sortTypeGson.data.get(pos).subcate.size()/4+1;
			}
			for(int i=1;i<=count;i++){
				LinearLayout ll = (LinearLayout) View.inflate(context, R.layout.sort_type_listcontent_child, null);
			    if(i == count){
			    	int a = sortTypeGson.data.get(pos).subcate.size() - (i-1)*4;
			    	for(int j=0;j<a;j++){
			    		TextView v = (TextView) ((LinearLayout)ll.getChildAt(j)).getChildAt(0);
			    		v.setVisibility(View.VISIBLE);
			    		if(selected.containsKey(pos+"") && selected.get(pos+"").equals(((i-1)*4+j)+"")){
			    			v.setTextColor(context.getResources().getColor(R.color.redcode));
			    		}
			    		v.setTag(pos+"|"+((i-1)*4+j));
			    		v.setOnClickListener(this);
			    		v.setText(sortTypeGson.data.get(pos).subcate.get((i-1)*4+j).catname);
			    	}
			    }else{
			    	for(int j=0;j<4;j++){
			    		//System.out.println(sortTypeGson.data.get(pos).subcate.size()+"------"+(i-1)*4+j);
			    		TextView v = (TextView) ((LinearLayout)ll.getChildAt(j)).getChildAt(0);
			    		v.setVisibility(View.VISIBLE);
			    		if(selected.containsKey(pos+"") && selected.get(pos+"").equals(((i-1)*4+j)+"")){
			    			v.setTextColor(context.getResources().getColor(R.color.redcode));
			    		}
			    		v.setTag(pos+"|"+((i-1)*4+j));
			    		v.setOnClickListener(this);
			    		
			    		v.setText(sortTypeGson.data.get(pos).subcate.get((i-1)*4+j).catname);
			    	}
			    }
			    child_content.addView(ll);
			}
			
			
		}else{
			convertView = View.inflate(context,
					R.layout.fram_cart1_listitem_blank, null);
		}
		convertView.setEnabled(false);
		return convertView;
	}

	@Override
	public boolean isEnabled(int position)
	{
		return false;
	}

	@Override
	public void onClick(View v)
	{
		
		String[] info = ((String)v.getTag()).split("\\|");
		String key = info[0];
		String value = info[1];
		
		TextView tv = (TextView) v;
		if(selected.containsKey(key)){
			if(selected.get(key).equals(value)){
				tv.setTextColor(context.getResources().getColor(R.color.type_code));
			    selected.remove(key);
			}else{
				String oldvalue = selected.get(key);
				TextView t = getViewByValue(oldvalue, tv);
			    t.setTextColor(context.getResources().getColor(R.color.type_code));
			    tv.setTextColor(context.getResources().getColor(R.color.redcode));
			    selected.put(key, value);
			}
		}else{
			  tv.setTextColor(context.getResources().getColor(R.color.redcode));
			  selected.put(key, value);
		}
		
		
	}

	private TextView getViewByValue(String value, TextView tv)
	{
		int pos = Integer.parseInt(value)/4;  //第几行
		int position = Integer.parseInt(value)-4*(pos);  //该行第几个
		//Toast.makeText(context, pos+"----"+position, 0).show();
		LinearLayout ll_p = (LinearLayout) (tv.getParent().getParent().getParent());
		TextView t = (TextView) ((LinearLayout) (((LinearLayout)ll_p.getChildAt(pos)).getChildAt(position))).getChildAt(0);
		return t;
	}

	public ArrayList<String> getSelected()
	{
		ArrayList<String> result = new ArrayList<String>();
		for(String item : selected.keySet()){

			result.add(sortTypeGson.data.get(Integer.parseInt(item)).subcate.get(Integer.parseInt(selected.get(item))).catname);
		}
		
		return result;
	}
	
	public ArrayList<String> getSelectedCatID(){
		ArrayList<String> result = new ArrayList<String>();
		for(String item : selected.keySet()){

			result.add(sortTypeGson.data.get(Integer.parseInt(item)).subcate.get(Integer.parseInt(selected.get(item))).catid+"");
		}
		
		return result;
	}
	
	

}
