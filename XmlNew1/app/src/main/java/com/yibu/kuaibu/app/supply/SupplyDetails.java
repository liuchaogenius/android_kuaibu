/**
 * 
 */
package com.yibu.kuaibu.app.supply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.HomePageAdapter;
import com.yibu.kuaibu.bean.gson.SupplyDetailGson;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.utils.GsonUtils;
import com.yibu.kuaibu.utils.NetWorkUtil;

/**
 * @author liujian
 *
 */
public class SupplyDetails extends Activity implements OnClickListener
{
	private ViewPager viewPager;
	private List<View> list;
	private TextView title;
	private TextView time;
	private TextView pricetv;
	private TextView unit;
	private TextView state;
	private TextView catname;
	private TextView content;
	private TextView phonetv;
	private TextView storename;
	private ImageView vip_mark;
	
	private LinearLayout back_ll;
	
	private ImageView vip;
	private View vip_line_mark;
	
	private FrameLayout load_content;
	
	private int itemid;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.supply_details);
		
		itemid = getIntent().getIntExtra("itemid", 0);
		load_content = (FrameLayout) findViewById(R.id.load_content);
		back_ll = (LinearLayout) findViewById(R.id.back_ll);
		back_ll.setOnClickListener(this);
		
		initView();
	}

	/**
	 * 
	 */
	private void initView()
	{
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;// 宽度
		//int height = dm.heightPixels ;//高度
		
		list = new ArrayList<View>();
		viewPager = (ViewPager) findViewById(R.id.mypager);
		
		LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) viewPager.getLayoutParams(); //取控件textView当前的布局参数  
		linearParams.height = width/3;// 控件的高强制设成20  
		  
		//linearParams.width = width/3;// 控件的宽强制设成30   
		  
		viewPager.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
		
		title = (TextView) findViewById(R.id.title);
		time = (TextView) findViewById(R.id.time);
		pricetv = (TextView) findViewById(R.id.pricetv);
		unit = (TextView) findViewById(R.id.unit);
		state = (TextView) findViewById(R.id.state);
		catname = (TextView) findViewById(R.id.catname);
		content = (TextView) findViewById(R.id.content);
		phonetv = (TextView) findViewById(R.id.phonetv);
		storename = (TextView) findViewById(R.id.storename);
		vip = (ImageView) findViewById(R.id.vip);
		vip_line_mark = findViewById(R.id.vip_line_mark);
		
		vip_mark = (ImageView) findViewById(R.id.vip_mark);
		initData();
		
		View view = View.inflate(this, R.layout.supply_details_viewpager_item, null);
		View view1 = View.inflate(this, R.layout.supply_details_viewpager_item, null);
		ImageView image3 = (ImageView) view1.findViewById(R.id.image3);
		image3.setVisibility(View.GONE);
		list.add(view);
		list.add(view1);
		
		HomePageAdapter adapter = new HomePageAdapter(this, list);
		viewPager.setAdapter(adapter);
		
		
	}
	
	/**
	 * 
	 */
	private void initData()
	{
		if(NetWorkUtil.isNetWork(SupplyDetails.this)){
			
		
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		httpClientUtil.setOnGetData(new OnGetResponseData()
		{
			
			@Override
			public void OnGetData(String result)
			{
				if(result == null){
					load_content.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show(); 
				    return;
				}
				//LogUtils.d(result);
				
				SupplyDetailGson supplyDetailGson = GsonUtils.jsonToBean(result, SupplyDetailGson.class);
				
				if(supplyDetailGson.result == 1){
					
				
				title.setText(supplyDetailGson.data.title);
				time.setText("发布时间："+supplyDetailGson.data.editdate);
				pricetv.setText("￥"+supplyDetailGson.data.price);
				unit.setText(supplyDetailGson.data.unit);
				state.setText("状态："+supplyDetailGson.data.typename);
				catname.setText(supplyDetailGson.data.catname);
				content.setText(supplyDetailGson.data.content);
				phonetv.setText(supplyDetailGson.data.mobile);
				storename.setText(supplyDetailGson.data.truename+"店铺");
				if(supplyDetailGson.data.vip == 0){
					vip_mark.setVisibility(View.GONE);
					vip.setVisibility(View.GONE);
					vip_line_mark.setBackgroundColor(getResources().getColor(R.color.btn_bg));
				}else{
					vip_mark.setVisibility(View.VISIBLE);
					vip.setVisibility(View.VISIBLE);
					vip_line_mark.setBackgroundColor(getResources().getColor(R.color.vip_line));
				}
				 load_content.setVisibility(View.GONE);
				}else{
					load_content.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show(); 
				}
				
				
			}
		});
		Map<String, String> datas = new HashMap<String, String>();
		datas.put(
				"token",
				"D2t2eFRjBGJtUngTexZ5FSgYZgclKWpUc3hvC2YBEHRSM2xSextVP2tSdkNxS0EgIBhBdDMGBz57GlA5VDIiFA9ldhBUMAQ6bQZ4T3tDeUMoTmZRJRNqAHNIbzZmBA");
		datas.put("itemid", itemid+"");
		try
		{
			load_content.setVisibility(View.VISIBLE);
			httpClientUtil.postRequest(
					"http://yihaobu.hu8hu.com/app/getSellDetail.php", datas);
		} catch (Exception e)
		{
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
		case R.id.back_ll:
			this.finish();
			break;


		}
		
	}


	

}
