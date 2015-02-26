package com.yibu.kuaibu.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapterly.Orderlist;
import com.yibu.kuaibu.adapterly.OrderlistAdapter;
import com.yibu.kuaibu.data.Unit;
import com.yibu.kuaibu.net.NetStateManager;
import com.yibu.kuaibu.net.ReqsyListener;
import com.yibu.kuaibu.utils.HttpsUtil;
import com.yibu.kuaibu.utils.YhdConstant;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class YhWodeOrder extends Activity
{

	private ImageView mTitleBack;
	private TextView mtitletxt;
	
	private ListView minelist;
	
	final Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case 1:

				break;

			case 2:

				break;

			case 3:
				List<Orderlist> apthis = (List<Orderlist>) msg.obj;
				minelist.setAdapter(new OrderlistAdapter(
						YhWodeOrder.this, apthis, minelist));

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
		
		setContentView(R.layout.yhwodeorder);
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
		
		mtitletxt=(TextView) findViewById(R.id.yhd_act_title);
		mtitletxt.setText("我的订单");
		
		minelist = (ListView) findViewById(R.id.listmine);
		
		getdata();
	}


	private void getdata()
	{
				
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
												
												

												// 解析订单 order listview
												JSONArray obj3 = new JSONArray(
														obj2.getString("rslist"));
												List<Orderlist> cpcxlist = new ArrayList<Orderlist>();
												for (int i = 0; i < obj3
														.length(); i++)
												{

													final JSONObject itemobj = new JSONObject(
															obj3.get(i)
																	.toString());
													
																										
													cpcxlist.add(new Orderlist(
															itemobj.getString("itemid"),
															itemobj.getString("orderid"),
															itemobj.getString("sellid"),
															itemobj.getString("seller"),
															itemobj.getString("sellcom"),
															itemobj.getString("title"),
															YhdConstant.yhd_pic_rul	+ itemobj.getString("thumb"),
															itemobj.getString("price"),
															itemobj.getString("number"),
															itemobj.getString("fee"),
															itemobj.getString("fee_name"),
															itemobj.getString("amount"),
															itemobj.getString("addtime"),
															itemobj.getInt("status"),
															itemobj.getString("dstatus"),
															null));
												}

												Message msg1 = handler
														.obtainMessage();
												
												msg1.what = 3;
												msg1.obj = cpcxlist;
												handler.sendMessage(msg1);

												

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
						
						mparams.add(new BasicNameValuePair("pageid",
								"1"));
						
						mparams.add(new BasicNameValuePair("pagesize",
								"10"));

						String response = HttpsUtil.sendPostRequestnew(
								YhdConstant.yhd_service_url
										+ YhdConstant.yhd_get_orderlist
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
