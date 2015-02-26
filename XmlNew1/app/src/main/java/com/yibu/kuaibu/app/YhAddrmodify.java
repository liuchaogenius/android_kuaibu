package com.yibu.kuaibu.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.data.Unit;
import com.yibu.kuaibu.net.NetStateManager;
import com.yibu.kuaibu.net.ReqsyListener;
import com.yibu.kuaibu.utils.HttpsUtil;
import com.yibu.kuaibu.utils.YhdConstant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class YhAddrmodify extends Activity
{

	private ImageView mTitleBack;
	private TextView mrenzhengtexttitle;
	private TextView mrighttexttitle;

	private ListView myaddrlistview;
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

		setContentView(R.layout.yhdeliverymodify);
		mTitleBack = (ImageView) findViewById(R.id.head_title_left);
		mTitleBack.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});

		mrenzhengtexttitle = (TextView) findViewById(R.id.main_head_title);
		mrenzhengtexttitle.setText("收货地址管理");

		mrighttexttitle = (TextView) findViewById(R.id.head_title_right);
		mrighttexttitle.setVisibility(View.INVISIBLE);

		
	}

	
}
