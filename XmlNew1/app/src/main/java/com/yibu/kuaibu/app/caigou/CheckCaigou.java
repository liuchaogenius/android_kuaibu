/**
 * 
 */
package com.yibu.kuaibu.app.caigou;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import android.widget.ListView;
import android.widget.TextView;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.CheckCaigouListAdapter;
import com.yibu.kuaibu.adapter.CheckSupplyListAdapter;
import com.yibu.kuaibu.bean.gson.CaigouItemGson;
import com.yibu.kuaibu.bean.gson.SupplyItemGson;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.ui.swipexlistview.XListView;
import com.yibu.kuaibu.ui.swipexlistview.XListView.IXListViewListener;
import com.yibu.kuaibu.utils.DateUtil;
import com.yibu.kuaibu.utils.GsonUtils;


public class CheckCaigou extends Activity implements OnClickListener
{

	// private CheckSupplyListAdapter checkSupplyListAdapter;
	private CheckCaigouListAdapter checkCaigouListAdapter;
	private TextView alldata;
	private TextView vipdata;
	private CaigouItemGson caigouItemGson;
	private int flag = 0;
	private XListView xlistView;

	private LinearLayout back_ll;
	private FrameLayout load_content;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_caigou);

		alldata = (TextView) findViewById(R.id.alldata);
		vipdata = (TextView) findViewById(R.id.vipdata);
		back_ll = (LinearLayout) findViewById(R.id.back_ll);
		load_content = (FrameLayout) findViewById(R.id.load_content);
		back_ll.setOnClickListener(this);

		alldata.setOnClickListener(this);
		vipdata.setOnClickListener(this);

		xlistView = (XListView) findViewById(R.id.xlistView);

		// 设置xlistview可以加载、刷新
		xlistView.setPullLoadEnable(true);
		xlistView.setPullRefreshEnable(true);

		initData(false,false, 0);
		xlistView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id)
			{
				Intent intent = new Intent(CheckCaigou.this,
						CaigouDetails.class);
				intent.putExtra("itemid",
						caigouItemGson.data.rslist.get(position - 1).itemid);
				CheckCaigou.this.startActivity(intent);
			}
		});
		xlistView.setXListViewListener(new IXListViewListener()
		{

			@Override
			public void onRefresh()
			{
				initData(true,true, flag);

			}

			@Override
			public void onLoadMore()
			{
				initData(true,true, flag);

			}
		});

	}

	/**
	 * 
	 */
	private void initData(final boolean isagain, boolean ispullordown,
			int allorvip)
	{
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		httpClientUtil.setOnGetData(new OnGetResponseData()
		{

			@Override
			public void OnGetData(String result)
			{
				caigouItemGson = GsonUtils.jsonToBean(result,
						CaigouItemGson.class);

				if (isagain)
				{
					load_content.setVisibility(View.GONE);
					xlistView.stopLoadMore();
					xlistView.stopRefresh();
					xlistView.setRefreshTime(DateUtil.getCurrentTime());
					checkCaigouListAdapter.setCaigouItemGson(caigouItemGson);
					checkCaigouListAdapter.notifyDataSetChanged();

				} else
				{
//					xlistView.stopLoadMore();
//					xlistView.stopRefresh();
					// xlistView.setDivider(null);
					xlistView.setRefreshTime(DateUtil.getCurrentTime());
					load_content.setVisibility(View.GONE);
					checkCaigouListAdapter = new CheckCaigouListAdapter(
							CheckCaigou.this, caigouItemGson);
					xlistView.setAdapter(checkCaigouListAdapter);
				}

			}
		});
		Map<String, String> datas = new HashMap<String, String>();
		datas.put(
				"token",
				"D2t2eFRjBGJtUngTexZ5FSgYZgclKWpUc3hvC2YBEHRSM2xSextVP2tSdkNxS0EgIBhBdDMGBz57GlA5VDIiFA9ldhBUMAQ6bQZ4T3tDeUMoTmZRJRNqAHNIbzZmBA");
		datas.put("userid", 1 + "");
		datas.put("catid", "2,3,5");
		datas.put("keyword", "装饰布");
		if (allorvip == 0)
		{

		} else
		{
			datas.put("vip", 1 + "");
		}
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
		case R.id.alldata:
			if (flag == 0)
			{

			} else
			{
				alldata.setTextColor(getResources().getColor(R.color.redcode));
				vipdata.setTextColor(getResources().getColor(R.color.black));
				flag = 0;
				initData(true,false, 0);
				// Toast.makeText(getApplicationContext(), "all", 0).show();
			}
			break;
		case R.id.vipdata:
			if (flag == 1)
			{

			} else
			{
				vipdata.setTextColor(getResources().getColor(R.color.redcode));
				alldata.setTextColor(getResources().getColor(R.color.black));
				flag = 1;
				initData(true,false, 1);
				// Toast.makeText(getApplicationContext(), "vip", 0).show();
			}
			break;
		case R.id.back_ll:
			this.finish();
			break;
		}

	}
}
