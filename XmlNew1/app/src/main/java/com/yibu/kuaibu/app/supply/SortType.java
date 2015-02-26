/**
 * 
 */
package com.yibu.kuaibu.app.supply;

import java.util.ArrayList;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.SortTypeListAdapter;
import com.yibu.kuaibu.bean.gson.SortTypeGson;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.utils.GsonUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author liujian
 *
 */
public class SortType extends Activity implements OnClickListener
{
	private ListView typelist;
	private SortTypeGson sortTypeGson;
	private TextView suretv;
	private SortTypeListAdapter sortTypeListAdapter;
	private FrameLayout load_content;
	private LinearLayout back_ll;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sort_type);
		
		initView();
		
		initData();
	}

	/**
	 * 
	 */
	private void initData()
	{
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		httpClientUtil.setOnGetData(new OnGetResponseData()
		{
			
			@Override
			public void OnGetData(String result)
			{
				sortTypeGson = GsonUtils.jsonToBean(result, SortTypeGson.class);
				
				System.out.println("sortTypeGson--"+sortTypeGson.data.size());
				
				sortTypeListAdapter = new SortTypeListAdapter(sortTypeGson, SortType.this);
				typelist.setAdapter(sortTypeListAdapter);
				load_content.setVisibility(View.GONE);
			}
		});
		try
		{
			load_content.setVisibility(View.VISIBLE);
			httpClientUtil.postRequest("http://yihaobu.hu8hu.com/app/getCategory.php", null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void initView()
	{
		load_content = (FrameLayout) findViewById(R.id.load_content);
		back_ll = (LinearLayout) findViewById(R.id.back_ll);
		typelist = (ListView) findViewById(R.id.typelist);
		suretv = (TextView) findViewById(R.id.suretv);
		suretv.setOnClickListener(this);
		back_ll.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.suretv:
			Intent intent = getIntent();
			ArrayList<String> result = sortTypeListAdapter.getSelected();
			ArrayList<String> catid = sortTypeListAdapter.getSelectedCatID();
			intent.putStringArrayListExtra("sort", result);
			intent.putStringArrayListExtra("catid", catid);
			this.setResult(100, intent);
			this.finish();
			break;

		case R.id.back_ll:
			this.finish();
			break;
		}
		
		
	}
	

}
