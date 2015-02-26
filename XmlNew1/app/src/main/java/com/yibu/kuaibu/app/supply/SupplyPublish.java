/**
 * 
 */
package com.yibu.kuaibu.app.supply;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.HomePageAdapter;
import com.yibu.kuaibu.bean.gson.SupplyResultGson;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.ui.photoalbum.Utils;
import com.yibu.kuaibu.ui.wheelview.AbstractWheelTextAdapter;
import com.yibu.kuaibu.ui.wheelview.OnWheelChangedListener;
import com.yibu.kuaibu.ui.wheelview.WheelView;
import com.yibu.kuaibu.utils.GsonUtils;
import com.yibu.kuaibu.utils.NetWorkUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author liujian
 * 
 */
public class SupplyPublish extends Activity implements OnClickListener
{
	private ViewPager viewPager;
	private List<View> list;
	private TextView timeet;
	private TextView typeet;



	private TextView nameet;
	private EditText priceet;
	private EditText detailet;
	private EditText linkman;
	private EditText linkphone;
	private RelativeLayout namerl;
	private int addposition = 1;
	
	private TextView publish;
	
	private LinearLayout back_ll;
	private RelativeLayout sort_rl;

	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private ImageView image11;
	private ImageView image12;
	private List<ImageView> imageViews = new ArrayList<ImageView>();
	private List<String> selectphotos = new ArrayList<String>();
	private List<Bitmap> bitmaps = new ArrayList<Bitmap>();

	private ImageView point1;
	private ImageView point2;
	
	private ImageView state1;
	private ImageView state2;
	private ImageView state3;
	
	private int[] statechose = new int[]{1,0,0};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.supply_publish);

		initView();

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;// 宽度
		// int height = dm.heightPixels ;//高度

		list = new ArrayList<View>();
		viewPager = (ViewPager) findViewById(R.id.mypager);

		LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) viewPager
				.getLayoutParams(); // 取控件textView当前的布局参数
		linearParams.height = width / 3;// 控件的高强制设成20

		viewPager.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件

		initPageContent();
		point1 = (ImageView) findViewById(R.id.point1);
		point2 = (ImageView) findViewById(R.id.point2);
		nameet = (TextView) findViewById(R.id.nameet);
		HomePageAdapter adapter = new HomePageAdapter(this, list);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			@Override
			public void onPageSelected(int arg0)
			{
				if (arg0 == 0)
				{
					point1.setImageResource(R.drawable.blackpoint);
					point2.setImageResource(R.drawable.whitepoint);
				} else
				{
					point2.setImageResource(R.drawable.blackpoint);
					point1.setImageResource(R.drawable.whitepoint);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{

			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{

			}
		});

	}

	private void initPageContent()
	{
		View view = View.inflate(this, R.layout.supply_details_viewpager_item,
				null);
		View view1 = View.inflate(this, R.layout.supply_details_viewpager_item,
				null);
		image1 = (ImageView) view.findViewById(R.id.image1);
		image2 = (ImageView) view.findViewById(R.id.image2);
		image3 = (ImageView) view.findViewById(R.id.image3);
		image1.setVisibility(View.VISIBLE);

		image11 = (ImageView) view1.findViewById(R.id.image1);
		image12 = (ImageView) view1.findViewById(R.id.image2);
		imageViews.add(image1);
		imageViews.add(image2);
		imageViews.add(image3);
		imageViews.add(image11);
		imageViews.add(image12);
		image1.setOnClickListener(this);
		image2.setOnClickListener(this);
		image3.setOnClickListener(this);
		image11.setOnClickListener(this);
		image12.setOnClickListener(this);

		image1.setTag(1);
		image2.setTag(2);
		image3.setTag(3);
		image11.setTag(11);
		image12.setTag(12);
		list.add(view);
		list.add(view1);

	}

	/**
	 * 
	 */
	private void initView()
	{
		typeet = (TextView) findViewById(R.id.typeet);
		priceet = (EditText) findViewById(R.id.priceet);
		detailet = (EditText) findViewById(R.id.detailet);
		linkman = (EditText) findViewById(R.id.linkman);
		linkphone = (EditText) findViewById(R.id.linkphone);
		
		namerl = (RelativeLayout) findViewById(R.id.namerl);
		namerl.setOnClickListener(this);
		
		back_ll = (LinearLayout) findViewById(R.id.back_ll);
		back_ll.setOnClickListener(this);
		
		nameet = (TextView) findViewById(R.id.nameet);
		
		publish = (TextView) findViewById(R.id.publish);
		publish.setOnClickListener(this);
		
		sort_rl = (RelativeLayout) findViewById(R.id.sort_rl);
		sort_rl.setOnClickListener(this);
		
		state1 = (ImageView) findViewById(R.id.state1);
		state1.setOnClickListener(this);
		state2 = (ImageView) findViewById(R.id.state2);
		state2.setOnClickListener(this);
		state3 = (ImageView) findViewById(R.id.state3);
		state3.setOnClickListener(this);
		
		
		
		timeet = (TextView) findViewById(R.id.timeet);
		timeet.setOnClickListener(this);


	}





	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.timeet:{
			// 触发动画，打开动画或者关闭动画
			Intent intent = new Intent(this,ChoseTime.class);
			startActivityForResult(intent, 0);
			break;}
		
		case R.id.image1:
		{
			int pos = (Integer) v.getTag();

			if (pos == 1)
			{
				if (addposition == 6)
				{
					Intent intent = new Intent(this, ImageShowActivity.class);
					intent.putExtra("photopath", selectphotos.get(4));
					intent.putExtra("position", 4);
					startActivityForResult(intent, 0);
				} else
				{
					Intent intent = new Intent(this, AddPicture.class);
					intent.putExtra("canselect", 6 - addposition);
					startActivityForResult(intent, 0);

					// Toast.makeText(getApplicationContext(), "page1--1", 0)
					// .show();
				}

			} else if (pos == 11)
			{

				Intent intent = new Intent(this, ImageShowActivity.class);
				intent.putExtra("photopath", selectphotos.get(2));
				intent.putExtra("state", 0);
				intent.putExtra("position", 2);
				// startActivity(intent);
				startActivityForResult(intent, 0);

			}

			break;
		}
		case R.id.image2:
		{
			int pos = (Integer) v.getTag();

			if (pos == 2)
			{

				Intent intent = new Intent(this, ImageShowActivity.class);
				intent.putExtra("photopath", selectphotos.get(0));
				intent.putExtra("position", 0);
				intent.putExtra("state", 0);
				startActivityForResult(intent, 0);

			} else if (pos == 12)
			{

				Intent intent = new Intent(this, ImageShowActivity.class);
				intent.putExtra("photopath", selectphotos.get(3));
				intent.putExtra("position", 3);
				intent.putExtra("state", 0);
				startActivityForResult(intent, 0);
			}

			break;
		}
		case R.id.image3:
		{

			Intent intent = new Intent(this, ImageShowActivity.class);
			intent.putExtra("photopath", selectphotos.get(1));
			intent.putExtra("position", 1);
			intent.putExtra("state", 0);
			startActivityForResult(intent, 0);

			break;
		}
		case R.id.namerl:{
			Intent intent = new Intent(this,InputTitleName.class);
			startActivityForResult(intent, 0);
			break;}
		case R.id.back_ll:
			this.finish();
			break;
		case R.id.sort_rl:{
			Intent intent = new Intent(this,SortType.class);
			startActivityForResult(intent, 0);
			break;}
		case R.id.state1:{
			int stateid = 0;
			changeState(stateid);
			break;}
		case R.id.state2:{
			int stateid = 1;
			changeState(stateid);
			break;}
		case R.id.state3:{
			int stateid = 2;
			changeState(stateid);
			break;}
		case R.id.publish:
			
			
			publish();
			
			
			
			
			
			break;
		}

	}

	/**
	 * 
	 */
	private void publish()
	{
		if(NetWorkUtil.isNetWork(SupplyPublish.this)){
			if(nameet.getText().toString().trim() == null || nameet.getText().toString().equals("")){
				Toast.makeText(getApplicationContext(), "请输入发布名称！", 0).show();
			    return;
			}
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			httpClientUtil.setOnGetData(new OnGetResponseData()
			{
				
				@Override
				public void OnGetData(String result)
				{
				  if(result == null){
						Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show();
						return;
				  }
				  
				  SupplyResultGson supplyResultGson = GsonUtils.jsonToBean(result, SupplyResultGson.class);
				  if(supplyResultGson.result == 1){
					  Toast.makeText(getApplicationContext(), "发布成功", 0).show(); 
				  }else{
					  Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show();
					  return; 
				  } 
				}
			});
			Map<String, String> datas = new HashMap<String, String>();
			datas.put("token", "D2t2eFRjBGJtUngTexZ5FSgYZgclKWpUc3hvC2YBEHRSM2xSextVP2tSdkNxS0EgIBhBdDMGBz57GlA5VDIiFA9ldhBUMAQ6bQZ4T3tDeUMoTmZRJRNqAHNIbzZmBA");
			datas.put("itemid", 1+"");
			datas.put("title", nameet.getText().toString().trim()+"");
			datas.put("price", priceet.getText().toString().trim()+"");
			datas.put("catid", "2,10,12");
			int typeid = 0;
			for(int i=0;i<3;i++){
				if(statechose[i]==1){
					typeid = i;
					break;
				}
			}
			datas.put("typeid", typeid+"");
			datas.put("today", timeet.getText().toString().trim()+"");
			datas.put("content", detailet.getText().toString().trim()+"");
			datas.put("truename", linkman.getText().toString().trim()+"");
			datas.put("mobile", linkphone.getText().toString().trim()+"");
			Map<String, String> files = new HashMap<String, String>();
			
			try
			{
				httpClientUtil.postRequestByXUtils("http://yihaobu.hu8hu.com/app/postSell.php", datas, files);
			} catch (Exception e)
			{
				Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show(); 
				e.printStackTrace();
			}
		}else{
			Toast.makeText(getApplicationContext(), R.string.network_not_connected, 0).show();
		}
		
	}

	private void changeState(int stateid)
	{
		switch (stateid)
		{
		case 0:
			if(statechose[stateid] == 1){
				state1.setBackgroundResource(R.drawable.checkbox2);
				statechose[stateid] = 0;
			}else{
				state1.setBackgroundResource(R.drawable.checkbox1);
				statechose[stateid] = 1;
				statechose[1] = 0;
				statechose[2] = 0;
				state2.setBackgroundResource(R.drawable.checkbox2);
				state3.setBackgroundResource(R.drawable.checkbox2);
			}
			break;
		case 1:
			if(statechose[stateid] == 1){
				state2.setBackgroundResource(R.drawable.checkbox2);
				statechose[stateid] = 0;
			}else{
				state2.setBackgroundResource(R.drawable.checkbox1);
				statechose[stateid] = 1;
				statechose[2] = 0;
				statechose[0] = 0;
				state1.setBackgroundResource(R.drawable.checkbox2);
				state3.setBackgroundResource(R.drawable.checkbox2);
			}
			break;
		case 2:
			if(statechose[stateid] == 1){
				state3.setBackgroundResource(R.drawable.checkbox2);
				statechose[stateid] = 0;
			}else{
				state3.setBackgroundResource(R.drawable.checkbox1);
				statechose[stateid] = 1;
				statechose[1] = 0;
				statechose[0] = 0;
				state1.setBackgroundResource(R.drawable.checkbox2);
				state2.setBackgroundResource(R.drawable.checkbox2);
			}
			break;
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == 0 && resultCode == 20)
		{

			ArrayList<String> photos = data.getStringArrayListExtra("photos");
			System.out.println("back--" + photos.size());
			for (int i = 0; i < photos.size(); i++)
			{
				try
				{
					// System.out.println(photos.get(i));
					if (addposition + i == 5)
					{
						selectphotos.add(addposition + i - 1, photos.get(i));
						Bitmap bitmap = Utils
								.getPathBitmap(SupplyPublish.this,
										Uri.fromFile(new File(photos.get(i))),
										200, 200);
						bitmaps.add(addposition + i - 1, bitmap);
						imageViews.get(0).setVisibility(View.VISIBLE);
						imageViews.get(0).setImageBitmap(bitmap);
					} else
					{
						selectphotos.add(addposition + i - 1, photos.get(i));
						Bitmap bitmap = Utils
								.getPathBitmap(SupplyPublish.this,
										Uri.fromFile(new File(photos.get(i))),
										200, 200);
						bitmaps.add(addposition + i - 1, bitmap);
						imageViews.get(addposition + i).setVisibility(
								View.VISIBLE);
						imageViews.get(addposition + i).setImageBitmap(bitmap);
					}

				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			// if (addposition+photos.size() <= 5)
			// {
			// imageViews.get(addposition+photos.size()-1).setVisibility(View.VISIBLE);
			// imageViews.get(addposition+photos.size()-1).setImageResource(
			// R.drawable.addpic);
			// }
			addposition += photos.size();
		} else if (requestCode == 0 && resultCode == 40)
		{
			int position = data.getIntExtra("position", 0);
			System.out.println("position---" + position);
			for (int i = 1; i < imageViews.size(); i++)
			{
				imageViews.get(i).setVisibility(View.GONE);
			}
			imageViews.get(0).setVisibility(View.VISIBLE);
			imageViews.get(0).setImageResource(R.drawable.addpic);
			selectphotos.remove(position);
			bitmaps.remove(position);
			addposition -= 1;
			for (int i = 1; i <= selectphotos.size(); i++)
			{

				imageViews.get(i).setVisibility(View.VISIBLE);
				imageViews.get(i).setImageBitmap(bitmaps.get(i - 1));

			}

		}else if(requestCode == 0 && resultCode == 60){
			String tag = data.getStringExtra("tag");
			nameet.setText(tag);
		}else if(requestCode == 0 && resultCode == 80){
			String sum = data.getStringExtra("sum");
			if(sum.equals("0")){
				
			}else{
				timeet.setText(sum);
			}
			
		}else if(requestCode == 0 && resultCode == 100){
			ArrayList<String> sort = data.getStringArrayListExtra("sort");
			StringBuilder sb = new StringBuilder();
			for(int i = 0;i<sort.size();i++){
				if(i == 0){
					sb.append(sort.get(i));
				}else{
					sb.append("、"+sort.get(i));
				}
				
			}
			typeet.setText(sb);
		}

	}
}
