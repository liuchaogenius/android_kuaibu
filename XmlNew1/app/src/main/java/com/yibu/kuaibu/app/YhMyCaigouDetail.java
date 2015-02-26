package com.yibu.kuaibu.app;

import java.util.ArrayList;
import java.util.HashMap;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.ui.ModelPopup;
import com.yibu.kuaibu.ui.ModelPopup.OnDialogListener;
import com.yibu.kuaibu.utils.AppToast;
import com.yibu.kuaibu.utils.ImageManager2;
import com.yibu.kuaibu.utils.TimeFormatUitl;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class YhMyCaigouDetail extends Activity
{

	private ImageView mTitleBack;

	private ListView wodecaigoulist;

	private ArrayList<HashMap<String, Object>> listItem = null;
	private SimpleAdapter mSimpleAdapter = null;
	
	private LinearLayout selectedImageLayout;
	private HorizontalScrollView scrollview;
	
	
	private ArrayList<String> selectedDataList = new ArrayList<String>();   //已选择的列表
	private HashMap<String, View> mhashMap = new HashMap<String, View>();   //存图片地址跟view的对应关系
	
	private ImageView button1;
	
	private ModelPopup mPopup;
	
	private LinearLayout layout_user;
	
	private Uri photoUri;
	/***
	 * 使用照相机拍照获取图片
	 */
	public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
	/***
	 * 使用相册中的图片
	 */
	public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
	//cut
	private static final int CUT_PHOTO = 3;

	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{

				case 7:
					HashMap<String, Object> tempmap = new HashMap<String, Object>();
					tempmap = (HashMap<String, Object>) msg.obj;
	
					listItem.add(tempmap);
	
					mSimpleAdapter.notifyDataSetChanged();
					break;
				default:
					break;

			}

			super.handleMessage(msg);
		}
	};

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.yhmycaigoudetail);
		
		//rename title		
		((TextView)findViewById(R.id.yhd_act_title)).setText("编辑求购");
		
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
		
		
		//get intent data
		Intent intent = getIntent();
		String itemtitle = intent.getStringExtra("itemtitle");
		String itemmianliao = intent.getStringExtra("itemmianliao");
		String itemstate = intent.getStringExtra("itemstate");
		String itemtime = intent.getStringExtra("itemtime");
		String itemliulan = intent.getStringExtra("itemliulan");
		String itemid = intent.getStringExtra("itemid");
		
		
		scrollview = (HorizontalScrollView) findViewById(R.id.horizontalview);
		selectedImageLayout = (LinearLayout) findViewById(R.id.imagelayout1);
		
		initSelectImage();
		
		
		OnDialogListener mlistener;
		mlistener = new OnDialogListener()
		{
			
			@Override
			public void onTakePhoto()
			{
				// TODO Auto-generated method stub
				takePhoto();
			}
			
			@Override
			public void onModel()
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onChoosePhoto()
			{
				// TODO Auto-generated method stub
				choosePhoto();
			}
			
			@Override
			public void onCancel()
			{
				// TODO Auto-generated method stub
				
			}
		};
		
		mPopup = new ModelPopup(YhMyCaigouDetail.this, mlistener, false);
		layout_user= (LinearLayout) findViewById(R.id.mine_user_layout);
		
		button1 = (ImageView) findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				mPopup.showAtLocation(layout_user, Gravity.BOTTOM, 0, 0);
				
			}
		});
	}
	
	private void choosePhoto(){
		
		Intent choosePictureIntent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(choosePictureIntent, SELECT_PIC_BY_PICK_PHOTO);
		
	}
	
	private void takePhoto() {
		// TODO Auto-generated method stub
		// 执行拍照前，应该先判断SD卡是否存在
		String SDState = Environment.getExternalStorageState();
		if (!SDState.equals(Environment.MEDIA_MOUNTED)) {
			AppToast.showShortText(YhMyCaigouDetail.this, "内存卡不存在");
			return;
		}
		try {
			photoUri = getContentResolver().insert(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					new ContentValues());
			if (photoUri != null) {
				Intent i = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
				startActivityForResult(i, SELECT_PIC_BY_TACK_PHOTO);

			} else {
				AppToast.showShortText(YhMyCaigouDetail.this, "发生意外，无法写入相册");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			AppToast.showShortText(YhMyCaigouDetail.this, "发生意外，无法写入相册");
		}
	}

	private void initSelectImage() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		selectedDataList = (ArrayList<String>) bundle
				.getSerializable("dataList");

		if (selectedDataList == null || selectedDataList.size() == 0)
			return;

		scrollview.setVisibility(View.VISIBLE);
		for (final String path : selectedDataList) {
			View view = LayoutInflater.from(YhMyCaigouDetail.this).inflate(
					R.layout.yh_choose_imageview, selectedImageLayout, false);
			ImageView imageView = (ImageView) view.findViewById(R.id.img);
			selectedImageLayout.addView(view);
			mhashMap.put(path, view);
			ImageManager2.from(YhMyCaigouDetail.this).displayImage(imageView,
					path, R.drawable.camera_default, 100, 100);
			view.findViewById(R.id.iv_delete).setOnClickListener(
					new View.OnClickListener() {

						@Override
						public void onClick(View v) {
//							removePath(path);
							// gridImageAdapter.notifyDataSetChanged();
						}
					});
		}
		// okButton.setText("完成("+selectedDataList.size()+"/8)");
	}
	
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case SELECT_PIC_BY_TACK_PHOTO:
				// 选择自拍结果
				beginCrop(photoUri);

				break;
			case SELECT_PIC_BY_PICK_PHOTO:
				// 选择图库图片结果
				beginCrop(intent.getData());
				break;
			case CUT_PHOTO:
				handleCrop(intent);
				break;
			}

		}
	}
	
	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void beginCrop(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高，注意如果return-data=true情况下,其实得到的是缩略图，并不是真实拍摄的图片大小，
		// 而原因是拍照的图片太大，所以这个宽高当你设置很大的时候发现并不起作用，就是因为返回的原图是缩略图，但是作为头像还是够清晰了
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		//返回图片数据
		intent.putExtra("return-data", true);
		startActivityForResult(intent, CUT_PHOTO);
	}
	
	private void handleCrop(Intent result) {
		Bundle extras = result.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
//			mwodeicon.setImageBitmap(photo);
			
			String path = TimeFormatUitl.getFileTimeName();
			
			Log.i("path","path = " + path);
//			PhotoUtil.getFileFromBytes(data, path);     //保存图片
			glApplication.saveMyBitmap(photo,path);
			
			View view = LayoutInflater.from(YhMyCaigouDetail.this).inflate(
					R.layout.yh_choose_imageview, selectedImageLayout, false);
			ImageView imageView = (ImageView) view.findViewById(R.id.img);

//			ImageManager2.from(YhMyCaigouDetail.this).displayImage(imageView,
//					path, R.drawable.camera_default, 100, 100);    //源
//			
//			imageView.setImageBitmap(photo);    //我
			
			
			ImageManager2.from(YhMyCaigouDetail.this).setImageBitmap(imageView,
					photo,true);      //我修改
			
			imageView.setTag(path);
			
			mhashMap.put(path, view);          //存
			selectedDataList.add(path);
			selectedImageLayout.addView(view);     //layout添加
		}
	}

}
