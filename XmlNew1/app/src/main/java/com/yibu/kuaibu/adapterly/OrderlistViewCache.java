package com.yibu.kuaibu.adapterly;

import com.yibu.kuaibu.R;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OrderlistViewCache {

    private View baseView;
    private TextView textViewfortitle;
    private TextView textViewforprice;
    private ImageView imageView;          //头像
    private TextView textViewfortime;   
    private TextView torderprice;      //价格
    private TextView tnum;                 //数量
    private TextView ttotal;           //总金额

    
    private RelativeLayout rlayout;    //工具栏 RelativeLayout
    private Button bt1;                // 申请退款
    private Button bt2;                // 取消订单
    private Button bt3;                // 付款
    private Button bt4;                // 评价
    private Button bt5;                // 确认收货
    private RelativeLayout rshiji;   //实际付款
    
    private LinearLayout majorlayout; //主要layout
    
    
       
    public LinearLayout getMajorlayout()
	{		
		if (majorlayout == null) {
			majorlayout = (LinearLayout) baseView.findViewById(R.id.mainlayout);
        }
		return majorlayout;
	}

	public RelativeLayout getRshiji()
	{		
		if (rshiji == null) {
			rshiji = (RelativeLayout) baseView.findViewById(R.id.order_yijingfukuan);
        }
		return rshiji;
	}

	public RelativeLayout getRlayout()
	{		
		if (rlayout == null) {
			rlayout = (RelativeLayout) baseView.findViewById(R.id.boxlayout);
        }
		return rlayout;
	}

	public Button getBt1()
	{		
		if (bt1 == null) {
			bt1 = (Button) baseView.findViewById(R.id.order_tuikuan);
        }
		return bt1;
	}

	public Button getBt2()
	{		
		if (bt2 == null) {
			bt2 = (Button) baseView.findViewById(R.id.order_cancel);
        }
		return bt2;
	}

	public Button getBt3()
	{
		
		if (bt3 == null) {
			bt3 = (Button) baseView.findViewById(R.id.order_fukuan);
        }
		return bt3;
	}

	public Button getBt4()
	{
		
		if (bt4 == null) {
			bt4 = (Button) baseView.findViewById(R.id.order_pingjia);
        }
		return bt4;
	}

	public Button getBt5()
	{		
		if (bt5 == null) {
			bt5 = (Button) baseView.findViewById(R.id.order_confirm);
        }
		return bt5;
	}

	public TextView getTorderprice()
	{
    	if (torderprice == null) {
    		torderprice = (TextView) baseView.findViewById(R.id.orderprice);
        }
		return torderprice;
	}

	public TextView getTnum()
	{
		if (tnum == null) {
			tnum = (TextView) baseView.findViewById(R.id.ordercount);
        }
		return tnum;
	}

	public TextView getTtotal()
	{
		
		if (ttotal == null) {
    		ttotal = (TextView) baseView.findViewById(R.id.ordertotal);
        }
		return ttotal;
	}

	public TextView getOrderprice()
	{
    	if (torderprice == null) {
    		torderprice = (TextView) baseView.findViewById(R.id.orderprice);
        }
		return torderprice;
	}

	public OrderlistViewCache(View baseView) {
        this.baseView = baseView;
    }

//    public TextView getTextView() {
//        if (textView == null) {
//            textView = (TextView) baseView.findViewById(R.id.text);
//        }
//        return textView;
//    }

    public ImageView getImageView() {
        if (imageView == null) {
            imageView = (ImageView) baseView.findViewById(R.id.orderimage);
        }
        return imageView;
    }

	public TextView getTextViewfortitle()
	{
		if (textViewfortitle == null) {
			textViewfortitle = (TextView) baseView.findViewById(R.id.shopname);
        }
		return textViewfortitle;
	}

	public TextView getTextViewforprice()
	{
		if (textViewforprice == null) {
			textViewforprice = (TextView) baseView.findViewById(R.id.orderstatus);
        }
		return textViewforprice;
	}

	public TextView getTextViewfortime()
	{
		// TODO Auto-generated method stub
		if (textViewfortime == null) {
			textViewfortime = (TextView) baseView.findViewById(R.id.ordercontent);
        }
		return textViewfortime;
	}

    
}