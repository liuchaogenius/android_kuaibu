package com.yibu.kuaibu.app;

import com.yibu.kuaibu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class YhWodeDianpu extends Activity
{

	private ImageView mTitleBack;
	private TextView mtitletxt;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.yhwodedianpu);
		mTitleBack = (ImageView) findViewById(R.id.yhd_act_left);
		mTitleBack.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		mtitletxt=(TextView) findViewById(R.id.yhd_act_title);
		mtitletxt.setText("我的店铺");
	}

	

}
