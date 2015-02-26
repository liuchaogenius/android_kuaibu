package com.yibu.kuaibu.app;

import java.util.ArrayList;
import java.util.HashMap;

import com.yibu.kuaibu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class YhMyGongyingDetail extends Activity
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

		setContentView(R.layout.yhmygongyingdetail);
		
		//rename title		
		((TextView)findViewById(R.id.yhd_act_title)).setText("编辑供应");
		
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
		
		
		//get intent data
		Intent intent = getIntent();
		String itemtitle = intent.getStringExtra("itemtitle");
		String itemmianliao = intent.getStringExtra("itemmianliao");
		String itemstate = intent.getStringExtra("itemstate");
		String itemtime = intent.getStringExtra("itemtime");
		String itemliulan = intent.getStringExtra("itemliulan");
		String itemid = intent.getStringExtra("itemid");
		
		
	}

}
