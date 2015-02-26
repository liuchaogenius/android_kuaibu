/**
 * 
 */
package com.yibu.kuaibu.app.caigou;

import com.yibu.kuaibu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;


public class ReportPrice extends Activity implements OnClickListener
{
	
	private LinearLayout back_ll;
	


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_price);
		initView();
	}

	/**
	 * 
	 */
	private void initView()
	{
		back_ll = (LinearLayout) findViewById(R.id.back_ll);
		back_ll.setOnClickListener(this);
		
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
