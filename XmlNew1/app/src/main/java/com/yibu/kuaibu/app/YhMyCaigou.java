package com.yibu.kuaibu.app;

import java.util.ArrayList;
import java.util.HashMap;

import com.yibu.kuaibu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class YhMyCaigou extends Activity
{

	private ImageView mTitleBack;

	private ListView wodecaigoulist;

	private ArrayList<HashMap<String, Object>> listItem = null;
	private SimpleAdapter mSimpleAdapter = null;

	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{

				case 7:
					HashMap<String, Object> tempmap = new HashMap<String, Object>();
					tempmap = (HashMap<String, Object>) msg.obj;
	
					listItem.add(tempmap);
	
					mSimpleAdapter.notifyDataSetChanged();
					break;
				default:
					break;

			}

			super.handleMessage(msg);
		}
	};

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.yhmycaigou);		
		
		((TextView)findViewById(R.id.yhd_act_title)).setText("我的求购");
		
		mTitleBack = (ImageView) findViewById(R.id.yhd_act_left);
		mTitleBack.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});

		// 初始化listview
		wodecaigoulist = (ListView) findViewById(R.id.list_wode_caigou);

		listItem = new ArrayList<HashMap<String, Object>>();
		mSimpleAdapter = new SimpleAdapter(this, listItem,
				R.layout.yhwodecaigoulistitem, new String[] { "itemicon",
						"itemtitle", "itemmianliao", "itemstate", "itemtime",
						"itemliulan" }, new int[] { R.id.wodecaigoulisticon,
						R.id.wdcaigoutitle, R.id.wdcaigoufenlei,
						R.id.wdcaigoustate, R.id.wdcaigoutime,
						R.id.wdcaigouliulan });
		wodecaigoulist.setAdapter(mSimpleAdapter);

		getdata();
		
		wodecaigoulist.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				// Logger.i("GetMsg","onItemClick: arg0: " + arg0);

				Log.i("YhMyCaigou", "onItemClick: arg2: " + arg2);
				
				Log.i("YhMyCaigou", "onItemClick: itemtitle: " + listItem.get(arg2).get("itemtitle"));
				Log.i("YhMyCaigou", "onItemClick: itemmianliao: " + listItem.get(arg2).get("itemmianliao"));
				Log.i("YhMyCaigou", "onItemClick: itemstate: " + listItem.get(arg2).get("itemstate"));
				Log.i("YhMyCaigou", "onItemClick: itemtime: " + listItem.get(arg2).get("itemtime"));
				Log.i("YhMyCaigou", "onItemClick: itemliulan: " + listItem.get(arg2).get("itemliulan"));
				Log.i("YhMyCaigou", "onItemClick: itemid: " + listItem.get(arg2).get("itemid"));
								
				Intent mintent = new Intent(YhMyCaigou.this,
						YhMyCaigouDetail.class);
				mintent.putExtra("itemtitle",
						"" + listItem.get(arg2).get("itemtitle"));
				mintent.putExtra("itemmianliao",
						"" + listItem.get(arg2).get("itemmianliao"));
				mintent.putExtra("itemstate",
						"" + listItem.get(arg2).get("itemstate"));
				mintent.putExtra("itemtime",
						"" + listItem.get(arg2).get("itemtime"));
				mintent.putExtra("itemliulan",
						"" + listItem.get(arg2).get("itemliulan"));
				mintent.putExtra("itemid",
						"" + listItem.get(arg2).get("itemid"));
				startActivity(mintent);
				
			}
		});

	}

	private void getdata()
	{
		// TODO Auto-generated method stub

		Integer[] imgeIDs = { R.drawable.test_yhdwodeqiugoupic,
				R.drawable.test_yhdwodeqiugoupic2, R.drawable.mine_wodeshoucang,
				R.drawable.mine_guanyu, R.drawable.mine_protocol,
				R.drawable.test_yhdwodeqiugoupic, R.drawable.test_yhdwodeqiugoupic,
				R.drawable.test_yhdwodeqiugoupic, R.drawable.mine_help,
				R.drawable.mine_dianpuxinxi };

		int mcount = 10;
		for (int j = 0; j < mcount; j++)
		{

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemicon", imgeIDs[j]);
			map.put("itemtitle", "寻找迪龙之" + j);
			map.put("itemmianliao", "面料" + j);
			map.put("itemstate", "寻找中");
			map.put("itemtime", "2014-10-" + j);
			map.put("itemliulan", "500" + j);
			map.put("itemid", "1000" + j);      //求购的id
			
			//还需要put， 图片数组

			Message message = handler.obtainMessage();
			message.what = 7;
			message.obj = map;
			handler.sendMessage(message);

		}

	}

}
