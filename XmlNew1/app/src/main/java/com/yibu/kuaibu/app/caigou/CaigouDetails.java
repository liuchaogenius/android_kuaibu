/**
 * 
 */
package com.yibu.kuaibu.app.caigou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lidroid.xutils.util.LogUtils;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.HomePageAdapter;
import com.yibu.kuaibu.bean.gson.CaigouDetailGson;
import com.yibu.kuaibu.bean.gson.SupplyDetailGson;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.utils.GsonUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CaigouDetails extends Activity implements OnClickListener
{
	private TextView reportprice;
	private FrameLayout load_content;
	private LinearLayout back_ll;
	
	private TextView title;
	private TextView time;
	private TextView state;
	private TextView catname;
	private TextView content;
	private TextView phonetv;
	private TextView storename;
	private ImageView vip;
	private View vip_line_mark;
	private ImageView vip_mark;
	
	private ViewPager viewPager;
	private List<View> list;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.caigou_details);
		
		initView();
		
		
		
	}

	/**
	 * 
	 */
	private void initView()
	{
		reportprice = (TextView) findViewById(R.id.reportprice);
		load_content = (FrameLayout) findViewById(R.id.load_content);
		back_ll = (LinearLayout) findViewById(R.id.back_ll);
		
		back_ll.setOnClickListener(this);
		
		title = (TextView) findViewById(R.id.title);
		time = (TextView) findViewById(R.id.time);
		state = (TextView) findViewById(R.id.state);
		catname = (TextView) findViewById(R.id.catname);
		content = (TextView) findViewById(R.id.content);
		phonetv = (TextView) findViewById(R.id.phonetv);
		storename = (TextView) findViewById(R.id.storename);
		vip = (ImageView) findViewById(R.id.vip);
		vip_line_mark = findViewById(R.id.vip_line_mark);
		vip_mark = (ImageView) findViewById(R.id.vip_mark);
		
		reportprice.setOnClickListener(this);
		load_content.setOnClickListener(this);
		
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
		HttpClientUtil httpClientUtil = new HttpClientUtil();
		httpClientUtil.setOnGetData(new OnGetResponseData()
		{
			
			@Override
			public void OnGetData(String result)
			{
				LogUtils.d(result);
				CaigouDetailGson caigouDetailGson = GsonUtils.jsonToBean(result, CaigouDetailGson.class);
				
				title.setText(caigouDetailGson.data.title);
				time.setText("发布时间："+caigouDetailGson.data.editdate);

				state.setText("状态："+caigouDetailGson.data.typename);
				catname.setText(caigouDetailGson.data.catname);
				content.setText(caigouDetailGson.data.content);
				phonetv.setText(caigouDetailGson.data.mobile);
				storename.setText(caigouDetailGson.data.truename+"店铺");
				if(caigouDetailGson.data.vip == 0){
					vip_mark.setVisibility(View.GONE);
					vip.setVisibility(View.GONE);
					vip_line_mark.setBackgroundColor(getResources().getColor(R.color.btn_bg));
				}else{
					vip_mark.setVisibility(View.VISIBLE);
					vip.setVisibility(View.VISIBLE);
					vip_line_mark.setBackgroundColor(getResources().getColor(R.color.vip_line));
				}
				
				
				load_content.setVisibility(View.GONE);
				
			}
		});
		Map<String, String> datas = new HashMap<String, String>();
		datas.put(
				"token",
				"D2t2eFRjBGJtUngTexZ5FSgYZgclKWpUc3hvC2YBEHRSM2xSextVP2tSdkNxS0EgIBhBdDMGBz57GlA5VDIiFA9ldhBUMAQ6bQZ4T3tDeUMoTmZRJRNqAHNIbzZmBA");
		datas.put("itemid", 1+"");
		try
		{
			load_content.setVisibility(View.VISIBLE);
			httpClientUtil.postRequest(
					"http://yihaobu.hu8hu.com/app/getBuyDetail.php", datas);
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
		case R.id.reportprice:  //我要报价
			Intent intent = new Intent(this,ReportPrice.class);
			this.startActivity(intent);
			break;
		case R.id.back_ll:
			this.finish();
			break;
		}
		
	}
	

}
