/**
 * 
 */
package com.yibu.kuaibu.app.supply;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.ui.wheelview.AbstractWheelTextAdapter;
import com.yibu.kuaibu.ui.wheelview.OnWheelChangedListener;
import com.yibu.kuaibu.ui.wheelview.WheelView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * @author liujian
 * 
 */
public class ChoseTime extends Activity implements OnClickListener
{

	private WheelView mywheelview;
	private LinearLayout ll_wheelview;
	private Animation topDownAnim;
	private Animation topUpAnim;

	private TextView cancle;
	private TextView finish;
	private String sum;
	private boolean animState = false;
	private int tag = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chose_time);
		 getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);//需要添加的语句

		mywheelview = (WheelView) findViewById(R.id.mywheelview);
		ll_wheelview = (LinearLayout) findViewById(R.id.ll_wheelview);

		cancle = (TextView) findViewById(R.id.cancle);
		finish = (TextView) findViewById(R.id.finish);
		cancle.setOnClickListener(this);
		finish.setOnClickListener(this);

		// 加载动画
		topDownAnim = AnimationUtils.loadAnimation(this, R.anim.top_down);
		topUpAnim = AnimationUtils.loadAnimation(this, R.anim.top_up);
		// 设置动画执行后的监听处理
		topDownAnim.setAnimationListener(topDownAnimListener);
		topUpAnim.setAnimationListener(topDownAnimListener);

		// 执行向上动画
		ll_wheelview.setVisibility(View.VISIBLE);
		ll_wheelview.startAnimation(topUpAnim);

		mywheelview.setViewAdapter(new CountryAdapter(this));
		mywheelview.addChangingListener(new OnWheelChangedListener()
		{

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue)
			{
				sum = newValue + 1 + "";

			}
		});
	}

	private AnimationListener topDownAnimListener = new AnimationListener()
	{

		@Override
		public void onAnimationEnd(Animation animation)
		{
			/**
			 * 1.让当前动画固定住，不要回到原来的位置 2.为了响应点击事件，需要重新设置submenu的位置和宽高
			 */
			animation.setFillAfter(true);// 动画执行后停住
			if(animState){
				if (sum == null)
				{
					Intent intent = ChoseTime.this.getIntent();
					if(tag == 1){
						intent.putExtra("sum", "0");
					}else{
						intent.putExtra("sum", "1");
					}
					
					ChoseTime.this.setResult(80, intent);
					ChoseTime.this.finish();
				} else
				{
					Intent intent = ChoseTime.this.getIntent();
					if(tag == 1){
						intent.putExtra("sum", "0");
					}else{
						intent.putExtra("sum", sum);
					}
					ChoseTime.this.setResult(80, intent);
					ChoseTime.this.finish();
				}
			}
			
			
			
			// //先设置submenu的宽高
			// RelativeLayout.LayoutParams params = new
			// RelativeLayout.LayoutParams(
			// LayoutParams.MATCH_PARENT,
			// DensityUtil.dip2px(getApplicationContext(),70));
			// //让其在titlebar的下边，因为titlebar是50dp，向下移动50dp，正好就显示出了submenu
			// params.setMargins(0, DensityUtil.dip2px(getApplicationContext(),
			// 50), 0, 0);
			// subMenu.clearAnimation();//清掉submenu上的动画
			// subMenu.setLayoutParams(params);//将参数设置给submenu

		}

		@Override
		public void onAnimationStart(Animation animation)
		{
		}

		@Override
		public void onAnimationRepeat(Animation animation)
		{
		}

	};

	/**
	 * Adapter for countries
	 */
	private class CountryAdapter extends AbstractWheelTextAdapter
	{

		/**
		 * @param context
		 */
		protected CountryAdapter(Context context)
		{
			super(context, R.layout.text, NO_RESOURCE);

			setItemTextResource(R.id.data);
		}

		@Override
		protected CharSequence getItemText(int index)
		{
			return index + 1 + "";
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.jian.wheeltest.WheelViewAdapter#getItemsCount()
		 */
		@Override
		public int getItemsCount()
		{
			return 7;
		}

		@Override
		public View getItem(int index, View convertView, ViewGroup parent)
		{
			View view = super.getItem(index, convertView, parent);
			TextView data = (TextView) view.findViewById(R.id.data);
			data.setTextColor(getResources().getColor(R.color.black));

			return view;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.cancle:
			// 执行向下动画
			tag = 1;
			animState = true;
			ll_wheelview.startAnimation(topDownAnim);
			//ll_wheelview.setVisibility(View.GONE);
			
			break;
		case R.id.finish:
			// 执行向下动画
			animState = true;
			ll_wheelview.startAnimation(topDownAnim);
			//ll_wheelview.setVisibility(View.GONE);
			

			break;
		}

	}

}
