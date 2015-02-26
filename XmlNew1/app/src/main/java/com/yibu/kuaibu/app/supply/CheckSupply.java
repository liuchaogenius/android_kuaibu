/**
 * 
 */
package com.yibu.kuaibu.app.supply;

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
import android.widget.Toast;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.CheckSupplyListAdapter;
import com.yibu.kuaibu.bean.gson.SupplyItemGson;
import com.yibu.kuaibu.bean.gson.SupplyItemGson.SupplyItem;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.NetStateManager;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.ui.swipexlistview.XListView;
import com.yibu.kuaibu.ui.swipexlistview.XListView.IXListViewListener;
import com.yibu.kuaibu.utils.DateUtil;
import com.yibu.kuaibu.utils.GsonUtils;
import com.yibu.kuaibu.utils.NetWorkUtil;

public class CheckSupply extends Activity implements OnClickListener
{
	//private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
	private XListView xlistView;
	private CheckSupplyListAdapter checkSupplyListAdapter;
	private TextView alldata;
	private TextView vipdata;
	private SupplyItemGson supplyItemGson;
	private FrameLayout load_content;
	private int flag = 0;   //tab id
	
	private int pages = 1;  //当前page
	private int pagetotal = 0;  //总页数
	
	private LinearLayout back_ll;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_supply);

		alldata = (TextView) findViewById(R.id.alldata);
		vipdata = (TextView) findViewById(R.id.vipdata);
		load_content = (FrameLayout) findViewById(R.id.load_content);
		back_ll = (LinearLayout) findViewById(R.id.back_ll);

		xlistView = (XListView) findViewById(R.id.xlistView);

		// 设置xlistview可以加载、刷新
		xlistView.setPullLoadEnable(true);
		xlistView.setPullRefreshEnable(true);

		alldata.setOnClickListener(this);
		vipdata.setOnClickListener(this);
		back_ll.setOnClickListener(this);
		initData(0, 0,pages);
		xlistView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id)
			{

				System.out.println(position);
				Intent intent = new Intent(CheckSupply.this,
						SupplyDetails.class);
				intent.putExtra("itemid",
						supplyItemGson.data.rslist.get(position - 1).itemid);
				CheckSupply.this.startActivity(intent);

			}
		});
		xlistView.setXListViewListener(new IXListViewListener()
		{

			@Override
			public void onRefresh()
			{
				pages = 1;
				initData(1, flag,pages);
			}

			@Override
			public void onLoadMore()
			{
				if(pages == pagetotal){
					Toast.makeText(getApplicationContext(), "已加载完全部数据", 0).show();
				}else{
					initData(-1, flag,pages+1);	
				}
				
			}
		});

	}

	/**
	 * 
	 * @param ispullordown 1:下拉； 0：头切换；-1：上啦
	 * @param allorvip   0：全部；1:vip
	 * @param pageid  要加载的页面
	 */
	private void initData(final int ispullordown, int allorvip,int pageid)
	{
		if(NetWorkUtil.isNetWork(CheckSupply.this)){
			
		
			
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		httpClientUtil.setOnGetData(new OnGetResponseData()
		{

			@Override
			public void OnGetData(String result)
			{
				if(result == null){
					xlistView.stopRefresh();
					xlistView.stopLoadMore();
					load_content.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show();
				}else{
					if(supplyItemGson == null || ispullordown == 0){  //头切换，重新加载数据
						supplyItemGson = GsonUtils.jsonToBean(result,
								SupplyItemGson.class);
						 if(supplyItemGson.result == 1){
							 xlistView.setRefreshTime(DateUtil.getCurrentTime());
							load_content.setVisibility(View.GONE);
							checkSupplyListAdapter = new CheckSupplyListAdapter(
										CheckSupply.this, supplyItemGson);
							xlistView.setAdapter(checkSupplyListAdapter);
							 
						 }else{
							 load_content.setVisibility(View.GONE);
							 Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show(); 
						 }	
					}else{  
						if(ispullordown == 1){  //下拉
							supplyItemGson = GsonUtils.jsonToBean(result,
									SupplyItemGson.class);
							
							if(supplyItemGson.result == 1){
								
							
							xlistView.stopRefresh();
							xlistView.setRefreshTime(DateUtil.getCurrentTime());
							//load_content.setVisibility(View.GONE);
							checkSupplyListAdapter = new CheckSupplyListAdapter(
									CheckSupply.this, supplyItemGson);
							xlistView.setAdapter(checkSupplyListAdapter);
							}else{
								 xlistView.stopRefresh();
								 Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show(); 
							}
							
						}else if(ispullordown == -1){  //上拉
							SupplyItemGson newsGson = GsonUtils.jsonToBean(result,
									SupplyItemGson.class);
							if(newsGson.result == 1){
								for(SupplyItem item : newsGson.data.rslist){
									supplyItemGson.data.rslist.add(item);
								}
								xlistView.stopLoadMore();
								xlistView.setRefreshTime(DateUtil.getCurrentTime());
								//load_content.setVisibility(View.GONE);
								checkSupplyListAdapter.setSupplyItemGson(supplyItemGson);
								checkSupplyListAdapter.notifyDataSetChanged();
								pages = pages+1;
							}else{
								xlistView.stopLoadMore();
								Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show(); 
							}
							
							
						}
						
					}
					pagetotal = supplyItemGson.data.page.pagetotal;
	              
				}
				
			}
		});
		Map<String, String> datas = new HashMap<String, String>();
		datas.put(
				"token",
				"D2t2eFRjBGJtUngTexZ5FSgYZgclKWpUc3hvC2YBEHRSM2xSextVP2tSdkNxS0EgIBhBdDMGBz57GlA5VDIiFA9ldhBUMAQ6bQZ4T3tDeUMoTmZRJRNqAHNIbzZmBA");
	
		if (allorvip == 1)
		{
			datas.put("vip", 1 + "");
		}
		// datas.put("vip", 0 + "");
		datas.put("pageid", pages + "");
		datas.put("pagesize", 20 + "");
		
		try
		{
            if(ispullordown==0){
            	load_content.setVisibility(View.VISIBLE);
            }
			
			httpClientUtil.postRequest(
					"http://yihaobu.hu8hu.com/app/getSellList.php", datas);
		} catch (Exception e)
		{
			xlistView.stopRefresh();
			xlistView.stopLoadMore();
        	load_content.setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show(); 
			e.printStackTrace();
		}
		
		}else{
			Toast.makeText(getApplicationContext(), R.string.network_not_connected, 0).show();
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.alldata:
			clearData();
			if (flag == 0)
			{

			} else
			{
				alldata.setTextColor(getResources().getColor(R.color.redcode));
				vipdata.setTextColor(getResources().getColor(R.color.black));
				flag = 0;
				pages = 1;
				initData(0, 0,pages);
				// Toast.makeText(getApplicationContext(), "all", 0).show();
			}
			break;
		case R.id.vipdata:
			clearData();
			if (flag == 1)
			{

			} else
			{
				vipdata.setTextColor(getResources().getColor(R.color.redcode));
				alldata.setTextColor(getResources().getColor(R.color.black));
				pages = 1;
				flag = 1;
				initData(0,1,pages);
			}
			break;
		case R.id.back_ll:
		{
			this.finish();
			break;
		}
		}

	}

	private void clearData()
	{
		xlistView.stopRefresh();
		xlistView.stopLoadMore();
		supplyItemGson.data.rslist.clear();
		checkSupplyListAdapter.setSupplyItemGson(supplyItemGson);
		checkSupplyListAdapter.notifyDataSetChanged();
	}
}
