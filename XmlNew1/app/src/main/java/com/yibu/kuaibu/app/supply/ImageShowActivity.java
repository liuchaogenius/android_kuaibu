 /**
 * 
 */
package com.yibu.kuaibu.app.supply;

import java.io.File;
import java.io.FileNotFoundException;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.ui.photoalbum.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author liujian
 * 
 */
public class ImageShowActivity extends Activity implements OnClickListener{

	private Bitmap bitmap;
	private ImageView image;
	private ImageView image_right;
	private int state;
	private TextView back;
	private String photo;
	private int position;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.imageshow);

		image_right = (ImageView) findViewById(R.id.image_right);
		image_right.setOnClickListener(this);
		back = (TextView) findViewById(R.id.back);
		back.setOnClickListener(this);
		
		Intent intent = getIntent();
		photo = intent.getStringExtra("photopath");
		state = intent.getIntExtra("state", 0);
		if(state == -1){  //  相册状态，未选中
			image_right.setImageResource(R.drawable.selectno2);
			position = intent.getIntExtra("position", 0);
		}else if(state == 0){  //供应发布状态
			image_right.setImageResource(R.drawable.delete);
			position = intent.getIntExtra("position", 0);
		}else if(state == 1){ //  相册状态，选中
			image_right.setImageResource(R.drawable.selected2);
			position = intent.getIntExtra("position", 0);
		}

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;// ���
		int height = dm.heightPixels ;//�߶�
		if(photo == null){
			bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.imgbg);
		}else{
			
			try {
				bitmap =Utils.getPathBitmap(this,Uri.fromFile(new File(photo)), width, height);
			} catch (FileNotFoundException e) {
				bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.imgbg);
			}
		}
		image = (ImageView) findViewById(R.id.image);
		image.setImageBitmap(bitmap);
		
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.image_right:
			if(state == -1){ //  相册状态，未选中
				state = 1;
				image_right.setImageResource(R.drawable.selected2);
				
				
			}else if(state == 0){ //供应发布状态
				//返回，并删除当前照片
				Intent intent = getIntent();
				intent.putExtra("position", position);
				this.setResult(40, intent);
				this.finish();
				
				
			}else if(state == 1){ //  相册状态，选中
				state = -1;
				image_right.setImageResource(R.drawable.selectno2);
			}
			break;
		case R.id.back:
			Intent intent = getIntent();
			if(state == -1){
				intent.putExtra("photo", photo);
				intent.putExtra("state", -1);
				intent.putExtra("position", position);
				this.setResult(30, intent);
				this.finish();
			}else if(state == 1){
				intent.putExtra("photo", photo);
				intent.putExtra("state", 1);
				intent.putExtra("position", position);
				this.setResult(30, intent);
				this.finish();
			}else if(state == 0){
				//直接返回
			}
			
			break;
		}
		
	}

}
