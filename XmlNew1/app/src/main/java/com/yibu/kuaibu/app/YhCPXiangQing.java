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
import com.yibu.kuaibu.adapterly.CpxiangqingpingjiaAdapter;
import com.yibu.kuaibu.adapterly.Cpxiangqingpingjialist;
import com.yibu.kuaibu.adapterly.Cpxiangqingpopgridlist;
import com.yibu.kuaibu.data.Unit;
import com.yibu.kuaibu.net.NetStateManager;
import com.yibu.kuaibu.net.ReqsyListener;
import com.yibu.kuaibu.ui.ChooseSeKuailPopup;
import com.yibu.kuaibu.ui.ChooseSeKuailPopup.OnDialogListener;
import com.yibu.kuaibu.utils.HttpsUtil;
import com.yibu.kuaibu.utils.YhdConstant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class YhCPXiangQing extends Activity
{

	private ImageView mTitleBack;
	private TextView mrenzhengtexttitle;

	private ListView minelist;
	private ArrayList<HashMap<String, Object>> minelistItem = null;
	private SimpleAdapter minelistAdapter = null;
	
	
	private RelativeLayout chooseSekuai;
	private ChooseSeKuailPopup mPopup;
	
	private GridView CuXiaoChanPinGard;
	
	//立即购买
	private Button buyimmed;
	
	final Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case 1:

				break;

			case 2:
				//弹出框gridview
				List<Cpxiangqingpopgridlist> adapterpopdata = (List<Cpxiangqingpopgridlist>) msg.obj;
//				CuXiaoChanPinGard.setAdapter(new Cpxiangqingpopgridlist(
//						YhCPXiangQing.this, adapterpopdata, CuXiaoChanPinGard));
				
				Log.e("set","2");
				OnDialogListener mlistener;
				mlistener = new OnDialogListener()
				{

					@Override
					public void onTakePhoto()
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onModel()
					{
						// TODO Auto-generated method stub

					}

					

					

					@Override
					public void onClosePop()
					{
						// TODO Auto-generated method stub
						mPopup.dismiss();
					}
				};
				
				mPopup = new ChooseSeKuailPopup(YhCPXiangQing.this, mlistener, false,adapterpopdata);
				break;

			case 3:
				//评价
				List<Cpxiangqingpingjialist> apthis = (List<Cpxiangqingpingjialist>) msg.obj;
				minelist.setAdapter(new CpxiangqingpingjiaAdapter(
						YhCPXiangQing.this, apthis, minelist));

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

		setContentView(R.layout.cpxiangqing);
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
		
		minelist = (ListView) findViewById(R.id.listmine);
		
		
		
		
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View mPopView = inflater.inflate(R.layout.dialog_get_sekuai, null);
		CuXiaoChanPinGard = (GridView) mPopView.findViewById(R.id.shangcCXChanPinGard);

		ImageView btn_close=(ImageView)mPopView.findViewById(R.id.close_dia);
		
		btn_close.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Log.i("click","click");
			}
		});
		
		
		getdata();
		
		
		OnDialogListener mlistener;
		mlistener = new OnDialogListener()
		{

			@Override
			public void onTakePhoto()
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onModel()
			{
				// TODO Auto-generated method stub

			}

			

			

			@Override
			public void onClosePop()
			{
				// TODO Auto-generated method stub
				mPopup.dismiss();
			}
		};
		
		mPopup = new ChooseSeKuailPopup(YhCPXiangQing.this, mlistener, false);
		
		chooseSekuai = (RelativeLayout) findViewById(R.id.choose_sekuai);
		
		chooseSekuai.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				mPopup.showAtLocation(chooseSekuai, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
			}
		});
		
		
		
		
		
		
		
		buyimmed = (Button) findViewById(R.id.buynow);
		buyimmed.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(YhCPXiangQing.this, YhOrderConfirm.class);
				startActivity(intent);
			}
		});

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
												
												
												//解析色块
												JSONArray obj4 = new JSONArray(
														obj2.getString("sku"));
												List<Cpxiangqingpopgridlist> sklist = new ArrayList<Cpxiangqingpopgridlist>();
												for (int i = 0; i < obj4
														.length(); i++)
												{
													
													final JSONObject itemobj = new JSONObject(
															obj4.get(i)
																	.toString());
													
													View.OnClickListener abenClick = new View.OnClickListener()
													{
														public void onClick(
																View v)
														{
															
															
														}
													};
													
													sklist.add(new Cpxiangqingpopgridlist(
															itemobj.getString("skuid"),
															YhdConstant.yhd_pic_rul
																	+ itemobj
																			.getString("thumb"),
															itemobj.getString("skuname"),
															"¥"
																	+ itemobj
																			.getString("skuname"),"", "", abenClick));
													
//													Message msg55 = handler
//															.obtainMessage();
//													;
//													msg55.what = 2;
//													msg55.obj = sklist;
//													handler.sendMessage(msg55);
												
												}
												
												Message msg55 = handler
														.obtainMessage();
												;
												msg55.what = 2;
												msg55.obj = sklist;
												handler.sendMessage(msg55);
												

												// 解析评价listview
												JSONArray obj3 = new JSONArray(
														obj2.getString("comment"));
												List<Cpxiangqingpingjialist> cpcxlist = new ArrayList<Cpxiangqingpingjialist>();
												for (int i = 0; i < obj3
														.length(); i++)
												{

													final JSONObject itemobj = new JSONObject(
															obj3.get(i)
																	.toString());
													
																										
													cpcxlist.add(new Cpxiangqingpingjialist(
															itemobj.getString("itemid"),
															YhdConstant.yhd_pic_rul	+ itemobj.getString("avatar"),
															itemobj.getString("truename"),
															itemobj.getString("comment"),
															itemobj.getString("adddate"),
															itemobj.getString("truename"),
															null));
												}

												Message msg1 = handler
														.obtainMessage();
												;
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
