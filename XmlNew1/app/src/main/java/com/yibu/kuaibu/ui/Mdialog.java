package com.yibu.kuaibu.ui;

import com.yibu.kuaibu.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Mdialog extends Dialog
{

	private LinearLayout layout;
	private Context ct;
	private ImageView spaceshipImage;
	private TextView mtv;

	public Mdialog(Context context, boolean cancelable,
			OnCancelListener cancelListener)
	{
		super(context, cancelable, cancelListener);
	}

	public Mdialog(Context context, int theme)
	{
		super(context, theme);
		ct = context;
		layout = new LinearLayout(ct);

		init();
	}

	public Mdialog(Context context)
	{
		super(context);
		// ct = context;
		// layout = new LinearLayout(ct);
		// init();
	}

	private void init()
	{
		// getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		LayoutInflater inflater = LayoutInflater.from(ct);
		View v = inflater.inflate(R.layout.mdialogview, null);
		layout = (LinearLayout) v.findViewById(R.id.dialog_view);
		spaceshipImage = (ImageView) v.findViewById(R.id.img);
		mtv = (TextView) v.findViewById(R.id.diatextview);
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(ct,
				R.anim.dialoganimation);
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);
		setCancelable(true);
		// setCanceledOnTouchOutside(true);
		setContentView(layout, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
	}

	public void setBackgroundDrawable(int theme)
	{
		getWindow().setBackgroundDrawableResource(theme);
	}

	// public ImageView getSpaceshipImage() {
	// return spaceshipImage;
	// }
	//
	// public void setSpaceshipImage(int mid) {
	// spaceshipImage.setImageResource(mid);
	// spaceshipImage.setBackgroundResource(mid);
	// }
	//
	public TextView getMtv()
	{
		return mtv;
	}
	//
	// public void setMtv(String string) {
	// mtv.setText(string);
	// }

}
