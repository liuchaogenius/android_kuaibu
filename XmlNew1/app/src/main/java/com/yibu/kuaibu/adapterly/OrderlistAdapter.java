package com.yibu.kuaibu.adapterly;

import java.util.List;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapterly.AsyncImageLoader.ImageCallback;
import com.yibu.kuaibu.app.YhCPXiangQing;
import com.yibu.kuaibu.app.YhPingjia;
import com.yibu.kuaibu.app.YhShangCheng;
import com.yibu.kuaibu.app.YhWodeOrderDetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OrderlistAdapter extends ArrayAdapter<Orderlist>
{

	private ListView gridView;
	private AsyncImageLoader asyncImageLoader;

	public OrderlistAdapter(Activity activity, List<Orderlist> apthis,
			ListView minelist)
	{
		super(activity, 0, apthis);
		this.gridView = minelist;
		asyncImageLoader = new AsyncImageLoader();
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		Activity activity = (Activity) getContext();

		// Inflate the views from XML
		View rowView = convertView;
		OrderlistViewCache viewCache;
		if (rowView == null)
		{
			Log.e("OrderlistAdapter", "null, create");
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.sorderlistview_item, null);
			viewCache = new OrderlistViewCache(rowView);
			rowView.setTag(viewCache);
		} else
		{
			Log.e("OrderlistAdapter", "not null, won't create");
			viewCache = (OrderlistViewCache) rowView.getTag();
		}
		Orderlist imageAndText = getItem(position);

		// 头像
		String imageUrl = imageAndText.getMthumb();
		ImageView imageView = viewCache.getImageView();

		Log.i("imageUrl", "-----imageUrl = " + imageUrl);
		// if(imageView == null){
		// Log.i("OrderlistAdapter","imageView null = ");
		//
		// }else{
		// Log.i("OrderlistAdapter","imageView not null= ");
		// imageView.setOnClickListener(imageAndText.getmOnclick());
		//
		// }
		imageView.setTag(imageUrl);
		Drawable cachedImage = asyncImageLoader.loadDrawable(imageUrl,
				new ImageCallback()
				{
					public void imageLoaded(Drawable imageDrawable,
							String imageUrl)
					{
						ImageView imageViewByTag = (ImageView) gridView
								.findViewWithTag(imageUrl);
						if (imageViewByTag != null)
						{
							imageViewByTag.setImageDrawable(imageDrawable);
						}
					}
				});
		if (cachedImage == null)
		{
			imageView.setImageResource(R.drawable.test_yhdwodegongyingpic2);
		} else
		{
			imageView.setImageDrawable(cachedImage);
		}
		// 店名
		TextView textView = viewCache.getTextViewfortitle();
		textView.setText(imageAndText.getMsellcom());

		// 状态
		TextView textView2 = viewCache.getTextViewforprice();
		textView2.setText(imageAndText.getMdstatus());

		// 正文
		TextView textView3 = viewCache.getTextViewfortime();
		textView3.setText(imageAndText.getMtitle());

		// 价格
		TextView textView4 = viewCache.getTorderprice();
		textView4.setText(imageAndText.getMprice());

		// 数量
		TextView textView5 = viewCache.getTnum();
		textView5.setText(imageAndText.getMnumber());

		// total
		TextView textView6 = viewCache.getTtotal();
		textView6.setText(imageAndText.getMamount());

		// 处理按钮状态及点击事件
		int orderstate = imageAndText.getMstatus();
		Log.i("orderstate", "orderstate is : " + orderstate);

		RelativeLayout rlayout = viewCache.getRlayout();
		Button btn1 = viewCache.getBt1();
		Button btn2 = viewCache.getBt2();
		final Button btn3 = viewCache.getBt3();
		final Button btn4 = viewCache.getBt4();
		Button btn5 = viewCache.getBt5();

		if (orderstate == 0)
		{
			//待确认
			rlayout.setVisibility(View.GONE);     
		} else if (orderstate == 1)
		{
                 //待付款
			rlayout.setVisibility(View.VISIBLE);
			btn1.setVisibility(View.GONE);
			btn2.setVisibility(View.VISIBLE);
			btn3.setVisibility(View.VISIBLE);
			btn4.setVisibility(View.GONE);
			btn5.setVisibility(View.GONE);
		} else if (orderstate == 2)
		{
			//已支付
			rlayout.setVisibility(View.GONE);
			
			

		} else if (orderstate == 3)
		{
			//已发货
			rlayout.setVisibility(View.VISIBLE);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.GONE);
			btn3.setVisibility(View.GONE);
			btn4.setVisibility(View.GONE);
			btn5.setVisibility(View.VISIBLE);

		} else if (orderstate == 4)
		{
			//交易成功
			rlayout.setVisibility(View.VISIBLE);
			btn1.setVisibility(View.GONE);
			btn2.setVisibility(View.GONE);
			btn3.setVisibility(View.GONE);
			btn4.setVisibility(View.VISIBLE);
			btn5.setVisibility(View.GONE);

		} else if (orderstate == 5)
		{
			//卖家申请退款
			rlayout.setVisibility(View.GONE);
			

		} else if (orderstate == 6)
		{
			//已退款给买家
			rlayout.setVisibility(View.GONE);

		} else if (orderstate == 7)
		{
			//已付款给卖家
			rlayout.setVisibility(View.VISIBLE);
			btn1.setVisibility(View.VISIBLE);
			btn2.setVisibility(View.GONE);
			btn3.setVisibility(View.GONE);
			btn4.setVisibility(View.GONE);
			btn5.setVisibility(View.GONE);

		} else if (orderstate == 8)
		{
			//买家关闭
			rlayout.setVisibility(View.GONE);
			

		} else if (orderstate == 9)
		{
			//卖家关闭
			
			RelativeLayout rlayout1 = viewCache.getRshiji();
			rlayout1.setVisibility(View.GONE);
			rlayout.setVisibility(View.GONE);
		}
		
		
		//主布局，点击跳转到我的订单详情页面
		final LinearLayout majorlayout = viewCache.getMajorlayout();
		if(majorlayout !=null){
			majorlayout.setOnClickListener(new View.OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					Intent intjump = new Intent(
							majorlayout.getContext(),
							YhWodeOrderDetail.class);
					majorlayout.getContext().startActivity(intjump);
				}
			});
			
		}
		
		//发表评价按钮
		if(btn4 != null){
			btn4.setOnClickListener(new View.OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					Intent intjump = new Intent(
							majorlayout.getContext(),
							YhPingjia.class);
					btn4.getContext().startActivity(intjump);
				}
			});
		}
		
		//付款 按钮
				if(btn3 != null){
					btn3.setOnClickListener(new View.OnClickListener()
					{
						
						@Override
						public void onClick(View v)
						{
							// TODO Auto-generated method stub
							Intent intjump = new Intent(
									majorlayout.getContext(),
									YhPingjia.class);
							btn3.getContext().startActivity(intjump);
						}
					});
				}

		return rowView;
	}

}