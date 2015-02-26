package com.yibu.kuaibu.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.shangcheng.ShangChengTJDPAdapter;
import com.yibu.kuaibu.adapterly.CpxiangqingpingjiaAdapter;
import com.yibu.kuaibu.adapterly.Cpxiangqingpingjialist;
import com.yibu.kuaibu.data.Unit;
import com.yibu.kuaibu.net.NetStateManager;
import com.yibu.kuaibu.net.ReqsyListener;
import com.yibu.kuaibu.utils.HttpsUtil;
import com.yibu.kuaibu.utils.YhdConstant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class YhOrderConfirm extends Activity
{

	private ImageView mTitleBack;
	

	
	
	//立即购买
	private Button buyimmed;
	
	

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.yhorderconfirm);
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
		
		TextView tx = (TextView) findViewById(R.id.main_head_title);
		tx.setText("确认订单");

//		getdata();
		
//		buyimmed = (Button) findViewById(R.id.buynow);
//		buyimmed.setOnClickListener(new View.OnClickListener()
//		{
//			
//			@Override
//			public void onClick(View v)
//			{
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(YhOrderConfirm.this, YhOrderConfirm.class);
//				startActivity(intent);
//			}
//		});

	}

	private void getdata()
	{
		// TODO Auto-generated method stub

//		
//		// minelistItem = new ArrayList<HashMap<String, Object>>();
//		List<Cpxiangqingpingjialist> minelistItem = new ArrayList<Cpxiangqingpingjialist>();
//
//		for (int j = 0; j < 10; j++)
//		{
//			// HashMap<String, Object> map = new HashMap<String, Object>();
//			// map.put("usericon", "url");
//			// map.put("username", "可爱的芭比");
//			// minelistItem.add(map);
//
//			minelistItem.add(new Cpxiangqingpingjialist("" + i,
//					YhdConstant.yhd_pic_rul + itemobj.getString("thumb"),
//					itemobj.getString("title"), "¥"
//							+ itemobj.getString("price"), abenClick));
//		}
//
//		minelist.setAdapter(new CpxiangqingpingjiaAdapter(YhCPXiangQing.this,
//				minelistItem, minelist));
//
//		minelist.setOnItemClickListener(new OnItemClickListener()
//		{
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3)
//			{
//				Log.i("GetMsg", "onItemClick: arg2: " + arg2);
//				Log.i("GetMsg", "onItemClick: arg0: "
//						+ minelistItem.get(arg2).get("mname"));
//
//			}
//
//		});

		
		
		
		
		if (!Unit.getThreadPoolInstance().isShutdown())
		{
			Unit.getThreadPoolInstance().execute(new Runnable()
			{
				boolean mRun = true;
				boolean isRequest = false;

				@Override
				public void run()
				{
					while (mRun)
					{

						if (NetStateManager.isOnline())
						{
							Reqsydata(new ReqsyListener()
							{

								@Override
								public void onGet(String response)
								{
									// TODO Auto-generated method stub
									Log.i("YhShangChengtt", "onGet:" + response);
									if (response != null
											&& !response.equalsIgnoreCase(""))
									{
										try
										{
											JSONObject obj1 = new JSONObject(
													response);
											String status = obj1
													.getString("result");
											if (status.equalsIgnoreCase("1"))
											{
												JSONObject obj2 = new JSONObject(
														obj1.getString("data"));
												
												

												

												

											}

										} catch (JSONException e)
										{
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
									mRun = false;
								}

								@Override
								public void onIOExc(IOException e)
								{
									// TODO Auto-generated method stub

								}

								@Override
								public void onErr(Exception e)
								{
									// TODO Auto-generated method stub

								}

							});
							isRequest = true;
						}
						try
						{
							if (isRequest == true)
							{
								Thread.sleep(15000);// 15s
								isRequest = false;
							} else
							{
								Thread.sleep(2000);// 5s
							}

						} catch (InterruptedException e1)
						{
							e1.printStackTrace();
						}
					}
				}
			});
		}
		
		
	}

	
	
	protected void Reqsydata(final ReqsyListener ttreqsyListener)
	{
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		if (!Unit.getThreadPoolInstance().isShutdown())
		{
			Unit.getThreadPoolInstance().execute(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						List<NameValuePair> mparams = new ArrayList<NameValuePair>();
						;

						mparams.add(new BasicNameValuePair("token",
								TempData.mtemptoken));

						String response = HttpsUtil.sendPostRequestnew(
								YhdConstant.yhd_service_url
										+ YhdConstant.yhd_get_chanpin
										+ YhdConstant.yhd_mock, mparams,
								"UTF-8");

						// Log.e("YhShangCheng","String = " + response);

						if (ttreqsyListener != null)
						{
							// Log.e("YhShangCheng","String1 = " + response);
							ttreqsyListener.onGet(response);
						}
					} catch (Exception e)
					{
						if (ttreqsyListener != null)
						{
							ttreqsyListener.onErr(e);
						}
					}
				}
			});
		}
	}

}
