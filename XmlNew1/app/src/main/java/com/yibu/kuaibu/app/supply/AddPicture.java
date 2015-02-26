/**
 * 
 */
package com.yibu.kuaibu.app.supply;

import java.util.ArrayList;
import java.util.List;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.ui.photoalbum.ImageItem;
import com.yibu.kuaibu.ui.photoalbum.MyGridViewAdapter;
import com.yibu.kuaibu.ui.photoalbum.Utils;
import com.yibu.kuaibu.ui.photoalbum.MyGridViewAdapter.OnSelectImageChangeListener;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * @author liujian
 * 
 */
public class AddPicture extends Activity implements OnClickListener
{
	private GridView gv_mian;
	private List<ImageItem> list;
	private int sum = 0;
	private TextView overtv;
	private ImageView imagesum;
	private RelativeLayout bottom_rl;
	private int canselect;
	private MyGridViewAdapter adapter;
	private ArrayList<String> selectItem = new ArrayList<String>();
	
	private TextView back;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_picture_layout);
		canselect = getIntent().getIntExtra("canselect", 5);
		list = getAllPhotos();
		initView();
	}

	/**
		 * 
		 */
	private void initView()
	{
		gv_mian = (GridView) findViewById(R.id.gv_main);
		overtv = (TextView) findViewById(R.id.overtv);
		bottom_rl = (RelativeLayout) findViewById(R.id.bottom_rl);
		
		back = (TextView) findViewById(R.id.back);

		bottom_rl.setOnClickListener(this);
		back.setOnClickListener(this);
		
		imagesum = (ImageView) findViewById(R.id.imagesum);
		adapter = new MyGridViewAdapter(this, list, canselect);
		adapter.setOnSelectImageChangeListener(new OnSelectImageChangeListener()
		{

			@Override
			public void onSelectImageChange(int sum, int position, int state)
			{
				imagesum.setImageResource(Utils.changeImage(sum));
				if (state == 1)
				{
					selectItem.add(list.get(position).getPath());
				} else
				{
					selectItem.remove(list.get(position).getPath());
				}
			}
		});
		gv_mian.setAdapter(adapter);
		gv_mian.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Intent intent = new Intent(AddPicture.this,
						ImageShowActivity.class);
				intent.putExtra("photopath", list.get(position).getPath());
				intent.putExtra("position", position);
				if (selectItem.contains(list.get(position).getPath()))
				{
					intent.putExtra("state", 1);
				} else
				{
					intent.putExtra("state", -1);
				}
				startActivityForResult(intent, 0);
			}
		});
	}

	/**
	 * 得到手机所以图片路径
	 * 
	 * @return
	 */
	private List<ImageItem> getAllPhotos()
	{
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		Uri uri = intent.getData();
		List<ImageItem> list = new ArrayList<ImageItem>();
		List<ImageItem> listres = new ArrayList<ImageItem>();
		ImageItem item = null;
		String[] projection = new String[] { MediaStore.Images.Media.DATA };
		Cursor cursor = getContentResolver().query(uri, projection, null, null,
				null);
		while (cursor.moveToNext())
		{
			item = new ImageItem();
			String path = cursor.getString(0);
			item.setPath(path);
			list.add(item);
			item = null;
		}
		for (int i = list.size() - 1; i >= 0; i--)
		{
			listres.add(list.get(i));
		}
		list = null;
		return listres;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{

		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.bottom_rl:
			Intent intent = getIntent();
			intent.putStringArrayListExtra("photos", selectItem);
			AddPicture.this.setResult(20, intent);
			AddPicture.this.finish();
			
			break;
		case R.id.back:
			this.finish();
			break;
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == 0 && resultCode == 30)
		{
			String photo = data.getStringExtra("photo");
			int state = data.getIntExtra("state", 1);
			int position = data.getIntExtra("position", 0);
			if (state == 1)
			{ // 选中了
				if (selectItem.contains(photo))
				{

				} else
				{
					int sum = adapter.getSum();
					imagesum.setImageResource(Utils.changeImage(sum+1));
					adapter.setSum(adapter.getSum()+1);
					selectItem.add(photo);
					adapter.changeSelectState(1, position);
					adapter.notifyDataSetChanged();
				}
			} else if (state == -1)
			{ // 未选中
				if (selectItem.contains(photo))
				{
					int sum = adapter.getSum();
					imagesum.setImageResource(Utils.changeImage(sum-1));
					adapter.setSum(adapter.getSum()-1);
					selectItem.remove(photo);
					adapter.changeSelectState(0, position);
					adapter.notifyDataSetChanged();
				} 
			}
		}
	}

}
