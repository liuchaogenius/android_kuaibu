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
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class YhShangCheng extends Activity
{

	private GridView CuXiaoChanPinGard; // 促销产品之gardview

	private GridView TuijianduipuGard; // 推荐店铺

	private GridView scChanPinTuiJianGard; // 产品推荐

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
				List<ImageAndText> apthis = (List<ImageAndText>) msg.obj;
				CuXiaoChanPinGard.setAdapter(new ImageAndTextListAdapter(
						YhShangCheng.this, apthis, CuXiaoChanPinGard));

				break;

			case 4:
				List<ImageAndText> tjdpadapter = (List<ImageAndText>) msg.obj;
				TuijianduipuGard.setAdapter(new ShangChengTJDPAdapter(
						YhShangCheng.this, tjdpadapter, TuijianduipuGard));

				break;
			case 5:
				List<ImageAndText> cptjadapter = (List<ImageAndText>) msg.obj;
				scChanPinTuiJianGard.setAdapter(new ImageAndTextListAdapter(
						YhShangCheng.this, cptjadapter, scChanPinTuiJianGard));
				break;
			default:
				break;

			}
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yhshangcheng);

		findviews();

		getscdata();

	}

	private void findviews()
	{
		// TODO Auto-generated method stub
		CuXiaoChanPinGard = (GridView) findViewById(R.id.shangcCXChanPinGard);
		TuijianduipuGard = (GridView) findViewById(R.id.scdianpugridview);
		scChanPinTuiJianGard = (GridView) findViewById(R.id.sccptjGard);

	}

	private void getscdata()
	{
		// TODO Auto-generated method stub

		// 促销产品
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.test_yhdwodegongyingpic2);// 添加图像资源的ID
			map.put("ItemText", "NO." + String.valueOf(i));// 按序号做ItemText
			lstImageItem.add(map);
		}
		// 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
		SimpleAdapter saImageItems = new SimpleAdapter(this, // 没什么解释
				lstImageItem,// 数据来源
				R.layout.schanpintuijiangridview_item,// night_item的XML实现

				// 动态数组与ImageItem对应的子项
				new String[] { "ItemImage", "ItemText" },

				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.ItemImage, R.id.ItemText });
		// 添加并且显示
		CuXiaoChanPinGard.setAdapter(saImageItems);

		// 推荐店铺 TuijianduipuGard
		ArrayList<HashMap<String, Object>> sctuijiandianpuItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.test_yhdwodegongyingpic2);// 添加图像资源的ID
			map.put("ItemText", "NO." + String.valueOf(i));// 按序号做ItemText
			sctuijiandianpuItem.add(map);
		}
		// 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
		SimpleAdapter sctuijiandianpuadapter = new SimpleAdapter(this, // 没什么解释
				sctuijiandianpuItem,// 数据来源
				R.layout.sctjdpgridview_item,// night_item的XML实现

				// 动态数组与ImageItem对应的子项
				new String[] { "ItemImage", "ItemText" },

				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.ItemImage, R.id.ItemText });
		// 添加并且显示
		TuijianduipuGard.setAdapter(sctuijiandianpuadapter);

		// 产品推荐
		ArrayList<HashMap<String, Object>> scChanPinTuiJianGardItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++)
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.test_yhdwodegongyingpic2);// 添加图像资源的ID
			map.put("ItemText", "NO." + String.valueOf(i));// 按序号做ItemText
			scChanPinTuiJianGardItem.add(map);
		}
		// 生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
		SimpleAdapter scChanPinTuiJianItemsadp = new SimpleAdapter(this, // 没什么解释
				lstImageItem,// 数据来源
				R.layout.schanpintuijiangridview_item,// night_item的XML实现

				// 动态数组与ImageItem对应的子项
				new String[] { "ItemImage", "ItemText" },

				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.ItemImage, R.id.ItemText });
		// 添加并且显示
		scChanPinTuiJianGard.setAdapter(scChanPinTuiJianItemsadp);

		// get server data

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

												// 解析促销产品
												JSONArray obj3 = new JSONArray(
														obj2.getString("hotlist"));
												List<ImageAndText> cpcxlist = new ArrayList<ImageAndText>();
												for (int i = 0; i < obj3
														.length(); i++)
												{

													final JSONObject itemobj = new JSONObject(
															obj3.get(i)
																	.toString());
													
													
													View.OnClickListener abenClick = new View.OnClickListener()
													{
														public void onClick(
																View v)
														{
															// TODO
															// Auto-generated
															// method stub

															Log.e("kbsc","jump");
															Intent intjump = new Intent(
																	YhShangCheng.this,
																	YhCPXiangQing.class);
															try
															{
																intjump.putExtra(
																		"userid",
																		itemobj.getString("itemid"));
															} catch (JSONException e)
															{
																// TODO
																// Auto-generated
																// catch block
																e.printStackTrace();
															}
															startActivity(intjump);
														}
													};
													
													
													cpcxlist.add(new ImageAndText(
															itemobj.getString("itemid"),
															YhdConstant.yhd_pic_rul
																	+ itemobj
																			.getString("thumb"),
															itemobj.getString("title"),
															"¥"
																	+ itemobj
																			.getString("price"),abenClick));
												}

												Message msg1 = handler
														.obtainMessage();
												;
												msg1.what = 3;
												msg1.obj = cpcxlist;
												handler.sendMessage(msg1);

												// CuXiaoChanPinGard
												// .setAdapter(new
												// ImageAndTextListAdapter(
												// YhShangCheng.this, list,
												// CuXiaoChanPinGard));

												// 推荐店铺
												JSONArray obj4 = new JSONArray(
														obj2.getString("shoplist"));
												List<ImageAndText> tjdplist = new ArrayList<ImageAndText>();
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
															// TODO
															// Auto-generated
															// method stub

															Intent intjump = new Intent(
																	YhShangCheng.this,
																	YhShangChengOne.class);
															try
															{
																intjump.putExtra(
																		"userid",
																		itemobj.getString("itemid"));
															} catch (JSONException e)
															{
																// TODO
																// Auto-generated
																// catch block
																e.printStackTrace();
															}
															startActivity(intjump);
														}
													};

													tjdplist.add(new ImageAndText(
															itemobj.getString("itemid"),
															YhdConstant.yhd_pic_rul
																	+ itemobj
																			.getString("avatar"),
															itemobj.getString("company"),
															"0",abenClick));
												}

												Message msg2 = handler
														.obtainMessage();
												msg2.what = 4;
												msg2.obj = tjdplist;
												handler.sendMessage(msg2);

												// 解析促销产品
												JSONArray obj5 = new JSONArray(
														obj2.getString("malllist"));
												List<ImageAndText> cptjlist = new ArrayList<ImageAndText>();
												for (int i = 0; i < obj5
														.length(); i++)
												{

													JSONObject itemobj = new JSONObject(
															obj5.get(i)
																	.toString());
													cptjlist.add(new ImageAndText(
															itemobj.getString("itemid"),
															YhdConstant.yhd_pic_rul
																	+ itemobj
																			.getString("thumb"),
															itemobj.getString("title"),
															"¥"
																	+ itemobj
																			.getString("price"),null));
												}

												Message msg3 = handler
														.obtainMessage();
												;
												msg3.what = 5;
												msg3.obj = cptjlist;
												handler.sendMessage(msg3);

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
										+ YhdConstant.yhd_get_shangcheng
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
