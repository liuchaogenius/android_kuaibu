package com.yibu.kuaibu.app;

import com.yibu.kuaibu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class YhAbout extends Activity
{

	private ImageView mTitleBack;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.yhabout);
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
		
	}

	

}
