/**
 * 
 */
package com.yibu.kuaibu.app.supply;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.HotTagGridAdapter;
import com.yibu.kuaibu.bean.gson.HotTagGson;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.utils.GsonUtils;
import com.yibu.kuaibu.utils.NetWorkUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author liujian
 *
 */
public class InputTitleName extends Activity implements OnClickListener
{
	private GridView hot_tag_grid;
	private HotTagGson hotTagGson;
	private LinearLayout back_ll;
	private TextView truetv;
	private EditText taget;
	
	private FrameLayout load_content;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_title_name);
		
		initView();
		
		initData();
	}

	/**
	 * 
	 */
	private void initData()
	{
		if(NetWorkUtil.isNetWork(InputTitleName.this)){
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			httpClientUtil.setOnGetData(new OnGetResponseData()
			{
				
				@Override
				public void OnGetData(String result)
				{
					if(result == null){
						load_content.setVisibility(View.GONE);
						Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show();
				        return;
					}
					hotTagGson = GsonUtils.jsonToBean(result, HotTagGson.class);
					
					if(hotTagGson.result == 1){
						load_content.setVisibility(View.GONE);
						HotTagGridAdapter hotTagListAdapter = new HotTagGridAdapter(hotTagGson, InputTitleName.this);
						hot_tag_grid.setAdapter(hotTagListAdapter);
					}else{
						load_content.setVisibility(View.GONE);
						Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show(); 
					}
					
				}
			});
			try
			{
				load_content.setVisibility(View.VISIBLE);
				httpClientUtil.postRequest("http://yihaobu.hu8hu.com/app/getTitleTag.php", null);
			} catch (Exception e)
			{
				load_content.setVisibility(View.GONE);
				Toast.makeText(getApplicationContext(), R.string.server_connect_error, 0).show(); 
				e.printStackTrace();
			}
		}else{
			Toast.makeText(getApplicationContext(), R.string.network_not_connected, 0).show();
		}
		
	}

	/**
	 * 
	 */
	private void initView()
	{
		hot_tag_grid = (GridView) findViewById(R.id.hot_tag_grid);
		back_ll = (LinearLayout) findViewById(R.id.back_ll);
		truetv = (TextView) findViewById(R.id.truetv);
		taget = (EditText) findViewById(R.id.taget);
		load_content = (FrameLayout) findViewById(R.id.load_content);
		
		hot_tag_grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
		
		hot_tag_grid.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3)
			{
				String str = taget.getText().toString();
				str+=(" "+hotTagGson.data.taglist.get(position));
				taget.setText(str);
				
			}
		});
		truetv.setOnClickListener(this);
		
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
		case R.id.truetv:
			
			String tag = taget.getText().toString().trim();
			Intent intent = getIntent();
			intent.putExtra("tag", tag);
			this.setResult(60, intent);
			this.finish();
			
			break;

		}
		
	}
	

}
