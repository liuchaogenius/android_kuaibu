package com.yibu.kuaibu.app;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.app.glApplication.WzUser;
import com.yibu.kuaibu.data.Unit;
import com.yibu.kuaibu.net.NetStateManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class LoadApp extends Activity
{
	boolean ifAnimationEnd = false;
	boolean ifAppUpdate = false;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.loadlaojin, null);
		setContentView(view);

		new NetStateManager(getApplicationContext());
		glApplication.mwzuser = new WzUser();

		// 渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationEnd(Animation arg0)
			{

				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
			}

			@Override
			public void onAnimationStart(Animation animation)
			{
			}

		});

	}

	private void redirectTo()
	{
			
//			Intent intent = new Intent(this, Ma.class);
		Intent intent = new Intent(this, Ma.class);
			startActivity(intent);
			finish();
	}

	@Override
	protected void onDestroy()
	{

		super.onDestroy();
	}

}
