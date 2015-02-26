/**
 * 
 */
package com.yibu.kuaibu.app.myCaigouAndSupply;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.MyCaigouListAdapter;
import com.yibu.kuaibu.app.caigou.CaigouDetails;
import com.yibu.kuaibu.app.caigou.CaigouPublish;
import com.yibu.kuaibu.bean.gson.CaigouItemGson;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.ui.swipexlistview.XListView;
import com.yibu.kuaibu.ui.swipexlistview.XListView.IXListViewListener;
import com.yibu.kuaibu.utils.DateUtil;
import com.yibu.kuaibu.utils.GsonUtils;

/**
 * @author liujian
 * 
 */
public class MyCaigou extends Activity implements OnClickListener
{
	// private SwipeListView mSwipeListView;
	private MyCaigouListAdapter myCaigouListAdapter;
	private CaigouItemGson caigouItemGson;
	private XListView mListView;
	private LinearLayout back_ll;
	private TextView addtv;
	
	private FrameLayout load_content;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_caigou);
		
		back_ll = (LinearLayout) findViewById(R.id.back_ll);
		back_ll.setOnClickListener(this);
		
		addtv = (TextView) findViewById(R.id.addtv);
		addtv.setOnClickListener(this);
		
		load_content = (FrameLayout) findViewById(R.id.load_content);
		
		mListView = (XListView) findViewById(R.id.xListView);
		// 设置xlistview可以加载、刷新
		mListView.setPullLoadEnable(true);
		mListView.setPullRefreshEnable(true);
        mListView.setXListViewListener(new IXListViewListener()
		{
			
			@Override
			public void onRefresh()
			{
				initData(true);
			}
			
			@Override
			public void onLoadMore()
			{
				initData(true);
			}
		});
        
        mListView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3)
			{
				Intent intent = new Intent(MyCaigou.this, CaigouDetails.class);
				intent.putExtra("itemid",
						caigouItemGson.data.rslist.get(position-1).itemid);
				MyCaigou.this.startActivity(intent);
			}
		});

		initData(false);

	}

	/**
	 * 
	 */
	private void initData(final boolean ispullordown)
	{
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		httpClientUtil.setOnGetData(new OnGetResponseData()
		{

			@Override
			public void OnGetData(String result)
			{
				caigouItemGson = GsonUtils.jsonToBean(result,
						CaigouItemGson.class);
				if(ispullordown){
					mListView.stopLoadMore();
					mListView.stopRefresh();
					mListView.setRefreshTime(DateUtil.getCurrentTime());
					myCaigouListAdapter.setCaigouItemGson(caigouItemGson);
					myCaigouListAdapter.notifyDataSetChanged();
					
				}else{
					mListView.setRefreshTime(DateUtil.getCurrentTime());
					myCaigouListAdapter = new MyCaigouListAdapter(caigouItemGson,
							MyCaigou.this);
					mListView.setAdapter(myCaigouListAdapter);
					load_content.setVisibility(View.GONE);
				}
				
				

			}
		});
		Map<String, String> datas = new HashMap<String, String>();
		datas.put(
				"token",
				"D2t2eFRjBGJtUngTexZ5FSgYZgclKWpUc3hvC2YBEHRSM2xSextVP2tSdkNxS0EgIBhBdDMGBz57GlA5VDIiFA9ldhBUMAQ6bQZ4T3tDeUMoTmZRJRNqAHNIbzZmBA");
		datas.put("userid", 2 + "");
		datas.put("catid", "2,3,5");
		datas.put("keyword", "装饰布");

		// datas.put("vip", 0 + "");
		datas.put("pageid", 1 + "");
		datas.put("pagesize", 10 + "");
		try
		{
			if(ispullordown){
				
			}else{
				load_content.setVisibility(View.VISIBLE);
			}
			
			httpClientUtil.postRequest(
					"http://yihaobu.hu8hu.com/app/getBuyList.php", datas);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.back_ll:
			finish();
			break;
		case R.id.addtv:
			Intent intent = new Intent(this,CaigouPublish.class);
			startActivity(intent);
			break;
		}
	}






}
