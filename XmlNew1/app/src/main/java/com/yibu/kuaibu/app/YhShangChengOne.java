package com.yibu.kuaibu.app;

import java.util.HashMap;
import java.util.Map;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.CheckCaigouListAdapter;
import com.yibu.kuaibu.adapterly.ScGongyingXinxiListAdapter;
import com.yibu.kuaibu.app.caigou.CheckCaigou;
import com.yibu.kuaibu.app.supply.CheckSupply;
import com.yibu.kuaibu.bean.gson.CaigouItemGson;
import com.yibu.kuaibu.bean.gson.SupplyItemGson;
import com.yibu.kuaibu.bean.gson.SupplyItemGson.SupplyItem;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.ui.swipexlistview.XListView;
import com.yibu.kuaibu.utils.DateUtil;
import com.yibu.kuaibu.utils.GsonUtils;
import com.yibu.kuaibu.utils.NetWorkUtil;
import com.yibu.kuaibu.utils.YhdConstant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class YhShangChengOne extends Activity implements OnClickListener
{

	private TextView chanpinxinxi;
	private TextView gongyingxinxi;
	private TextView yangbanxinxi;
	
	private int flag = 0;
	
	private FrameLayout load_content;
	
	private XListView xlistView;
	
	private SupplyItemGson supplyItemGson;
	private ScGongyingXinxiListAdapter checkSupplyListAdapter;
	
	private int pages = 1;  //当前page
	private int pagetotal = 0;  //总页数

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yhshangchengone);
		
		 Intent intent =getIntent();
         /*取出Intent中附加的数据*/
         String first = intent.getStringExtra("userid");
         Log.i("YhShangChengOne","shopid:" + first);

		findviews();

		getscdata();
		
		
		chanpinxinxi.setOnClickListener(this);
		gongyingxinxi.setOnClickListener(this);
		yangbanxinxi.setOnClickListener(this);
	}

	private void findviews()
	{
		
		load_content = (FrameLayout) findViewById(R.id.load_content);
		
		
		xlistView = (XListView) findViewById(R.id.xlistView);
		// 设置xlistview可以加载、刷新
		xlistView.setPullLoadEnable(true);
		xlistView.setPullRefreshEnable(true);
		
		
		chanpinxinxi = (TextView) findViewById(R.id.chanpinxinxi);
		gongyingxinxi = (TextView) findViewById(R.id.gongyingxinxi);
		yangbanxinxi  = (TextView) findViewById(R.id.yangbanxinxi);
	}

	private void getscdata()
	{
		

	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.chanpinxinxi:
			if (flag == 0)
			{

			} else
			{
				chanpinxinxi.setTextColor(getResources().getColor(R.color.redcode));
				gongyingxinxi.setTextColor(getResources().getColor(R.color.black));			
				yangbanxinxi.setTextColor(getResources().getColor(R.color.black));
				
				
				flag = 0;
//				initData(true,false, 0);
				initData(1, flag,pages);
				// Toast.makeText(getApplicationContext(), "all", 0).show();
			}
			break;
		case R.id.gongyingxinxi:
			Log.i("test","0");
			if (flag == 1)
			{

			} else
			{
				chanpinxinxi.setTextColor(getResources().getColor(R.color.black));
				gongyingxinxi.setTextColor(getResources().getColor(R.color.redcode));			
				yangbanxinxi.setTextColor(getResources().getColor(R.color.black));
				flag = 1;
				initData(1, flag,pages);
				// Toast.makeText(getApplicationContext(), "vip", 0).show();
			}
			break;
		case R.id.yangbanxinxi:
			if (flag == 2)
			{

			} else
			{
				chanpinxinxi.setTextColor(getResources().getColor(R.color.black));
				gongyingxinxi.setTextColor(getResources().getColor(R.color.black));			
				yangbanxinxi.setTextColor(getResources().getColor(R.color.redcode));
				flag = 2;
				initData(1, flag,pages);
				
			}
			break;
		}

	}
	
	
	/**
	 * 
	 * @param ispullordown 1:下拉； 0：头切换；-1：上啦
	 * @param allorvip   0：全部；1:vip
	 * @param pageid  要加载的页面
	 */
	private void initData(final int ispullordown, int allorvip, int pageid)
	{
		if (NetWorkUtil.isNetWork(YhShangChengOne.this))
		{

			HttpClientUtil httpClientUtil = new HttpClientUtil();
			httpClientUtil.setOnGetData(new OnGetResponseData()
			{

				@Override
				public void OnGetData(String result)
				{
					Log.i("yhshangchengone","" + result);
					if (result == null)
					{
						xlistView.stopRefresh();
						xlistView.stopLoadMore();
						load_content.setVisibility(View.GONE);
						Toast.makeText(getApplicationContext(),
								R.string.server_connect_error, 0).show();
					} else
					{
						if (supplyItemGson == null || ispullordown == 0)
						{ // 头切换，重新加载数据
							supplyItemGson = GsonUtils.jsonToBean(result,
									SupplyItemGson.class);
							if (supplyItemGson.result == 1)
							{
								xlistView.setRefreshTime(DateUtil
										.getCurrentTime());
								load_content.setVisibility(View.GONE);
								checkSupplyListAdapter = new ScGongyingXinxiListAdapter(
										YhShangChengOne.this, supplyItemGson);
								xlistView.setAdapter(checkSupplyListAdapter);

							} else
							{
								load_content.setVisibility(View.GONE);
								Toast.makeText(getApplicationContext(),
										R.string.server_connect_error, 0)
										.show();
							}
						} else
						{
							if (ispullordown == 1)
							{ // 下拉
								supplyItemGson = GsonUtils.jsonToBean(result,
										SupplyItemGson.class);

								Log.i("test","1");
								if (supplyItemGson.result == 1)
								{

									Log.i("test","result = 1");
									xlistView.stopRefresh();
									xlistView.setRefreshTime(DateUtil
											.getCurrentTime());
									// load_content.setVisibility(View.GONE);
									checkSupplyListAdapter = new ScGongyingXinxiListAdapter(
											YhShangChengOne.this,
											supplyItemGson);
									xlistView
											.setAdapter(checkSupplyListAdapter);
								} else
								{
									xlistView.stopRefresh();
									Toast.makeText(getApplicationContext(),
											R.string.server_connect_error, 0)
											.show();
								}

							} else if (ispullordown == -1)
							{ // 上拉
								SupplyItemGson newsGson = GsonUtils.jsonToBean(
										result, SupplyItemGson.class);
								if (newsGson.result == 1)
								{
									for (SupplyItem item : newsGson.data.rslist)
									{
										supplyItemGson.data.rslist.add(item);
									}
									xlistView.stopLoadMore();
									xlistView.setRefreshTime(DateUtil
											.getCurrentTime());
									// load_content.setVisibility(View.GONE);
									checkSupplyListAdapter
											.setSupplyItemGson(supplyItemGson);
									checkSupplyListAdapter
											.notifyDataSetChanged();
									pages = pages + 1;
								} else
								{
									xlistView.stopLoadMore();
									Toast.makeText(getApplicationContext(),
											R.string.server_connect_error, 0)
											.show();
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
					TempData.mtemptoken);

			if (allorvip == 1)
			{
				datas.put("vip", 0 + "");
			}
			// datas.put("vip", 0 + "");
			datas.put("pageid", pages + "");
			datas.put("pagesize", 20 + "");
			datas.put("userid", ""+2);
//			datas.put("keyword", ""+"装饰布");
//			datas.put("vip", ""+"0");
			try
			{
				if (ispullordown == 0)
				{
					load_content.setVisibility(View.VISIBLE);
				}

				httpClientUtil.postRequest(YhdConstant.yhd_service_url
						+ YhdConstant.yhd_get_gongying+ YhdConstant.yhd_mock, datas);
			} catch (Exception e)
			{
				xlistView.stopRefresh();
				xlistView.stopLoadMore();
				load_content.setVisibility(View.GONE);
				Toast.makeText(getApplicationContext(),
						R.string.server_connect_error, 0).show();
				e.printStackTrace();
			}

		} else
		{
			Toast.makeText(getApplicationContext(),
					R.string.network_not_connected, 0).show();
		}
	}

}
