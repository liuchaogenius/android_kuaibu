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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class YhDeliveryAddress extends Activity
{

	private ImageView mTitleBack;
	private TextView mrenzhengtexttitle;
	private TextView mrighttexttitle;

	private ListView myaddrlistview;
	private ArrayList<HashMap<String, Object>> listItem = null;
	private SimpleAdapter mSimpleAdapter = null;

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

		setContentView(R.layout.yhdelivery);
		mTitleBack = (ImageView) findViewById(R.id.head_title_left);
		mTitleBack.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});

		mrenzhengtexttitle = (TextView) findViewById(R.id.main_head_title);
		mrenzhengtexttitle.setText("收货地址");

		mrighttexttitle = (TextView) findViewById(R.id.head_title_right);
		mrighttexttitle.setText("添加");

		myaddrlistview = (ListView) findViewById(R.id.wode_addrs);

		listItem = new ArrayList<HashMap<String, Object>>();
		mSimpleAdapter = new SimpleAdapter(this, listItem,
				R.layout.yhmyaddrslistitem, new String[] { "itemname",
						"itemtel", "itemicon", "itemadd" }, new int[] {
						R.id.umyadduser, R.id.umyaddtel, R.id.umyaddicon,
						R.id.umyaddaddress });
		myaddrlistview.setAdapter(mSimpleAdapter);

		myaddrlistview.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				// TODO Auto-generated method stub
				
				Log.i("YhDeliveryAddress", "onItemClick: arg2: " + position);
				int arg2 = position;
				Log.i("YhDeliveryAddress", "onItemClick: itemname: " + listItem.get(arg2).get("itemname"));
				Log.i("YhDeliveryAddress", "onItemClick: itemtel: " + listItem.get(arg2).get("itemtel"));
				Log.i("YhDeliveryAddress", "onItemClick: itemicon: " + listItem.get(arg2).get("itemicon"));
				Log.i("YhDeliveryAddress", "onItemClick: itemadd: " + listItem.get(arg2).get("itemadd"));				
				Log.i("YhDeliveryAddress", "onItemClick: itemid: " + listItem.get(arg2).get("itemid"));
								
				Intent mintent = new Intent(YhDeliveryAddress.this,
						YhAddrmodify.class);
				mintent.putExtra("itemname",
						"" + listItem.get(arg2).get("itemname"));
				mintent.putExtra("itemtel",
						"" + listItem.get(arg2).get("itemtel"));
				mintent.putExtra("itemicon",
						"" + listItem.get(arg2).get("itemicon"));
				mintent.putExtra("itemadd",
						"" + listItem.get(arg2).get("itemadd"));
				mintent.putExtra("itemid",
						"" + listItem.get(arg2).get("itemid"));
//				mintent.putExtra("itemid",
//						"" + listItem.get(arg2).get("itemid"));
				startActivity(mintent);
				
			}
		});
		
		mrighttexttitle.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent mintent = new Intent(YhDeliveryAddress.this,
						YhAddradd.class);				
				startActivity(mintent);
				
			}
		});

		getdata();
	}

	private void getdata()
	{
		// TODO Auto-generated method stub
		// int mcount = 2;
		// for (int j = 0; j < mcount; j++)
		// {
		//
		// HashMap<String, Object> map = new HashMap<String, Object>();
		//
		// map.put("itemname", "某某" + j);
		// map.put("itemtel", "1311111111" + j);
		// if (j == 1)
		// {
		// map.put("itemicon", "");
		// } else
		// {
		// map.put("itemicon", "[默认]");
		// }
		// // map.put("itemicon", "[默认]");
		// map.put("itemadd", "浙江杭州西湖区浙江大学紫金港校区碧峰学园3幢20" + j);
		//
		// // 还需要put， 图片数组
		//
		// Message message = handler.obtainMessage();
		// message.what = 7;
		// message.obj = map;
		// handler.sendMessage(message);
		//
		// }

		// 读取服务器数据
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
									Log.i("YhDeliveryAddress", "onGet:"
											+ response);
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

												// 收货地址
												JSONArray obj4 = new JSONArray(
														obj2.getString("rslist"));

												for (int i = 0; i < obj4
														.length(); i++)
												{
													JSONObject itemobj = new JSONObject(
															obj4.get(i)
																	.toString());

													HashMap<String, Object> map = new HashMap<String, Object>();

													map.put("itemname", itemobj
															.get("truename"));
													map.put("itemtel", itemobj
															.get("mobile"));
													if (("" + itemobj
															.get("ismain"))
															.equalsIgnoreCase("1"))
													{
														map.put("itemicon",
																"[默认]");
													} else
													{
														map.put("itemicon", "");
													}
													// map.put("itemicon",
													// "[默认]");
													map.put("itemadd", itemobj
															.get("address"));

													map.put("itemid", itemobj
															.get("itemid"));

													// 还需要put， 图片数组

													Message message = handler
															.obtainMessage();
													message.what = 7;
													message.obj = map;
													handler.sendMessage(message);

												}

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
						mparams.add(new BasicNameValuePair("pageid", "1"));
						mparams.add(new BasicNameValuePair("pagesize", "10"));

						String response = HttpsUtil.sendPostRequestnew(
								YhdConstant.yhd_service_url
										+ YhdConstant.yhd_get_address
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
