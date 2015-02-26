package com.yibu.kuaibu.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.CartListAdapter;
import com.yibu.kuaibu.adapter.CheckCaigouListAdapter;
import com.yibu.kuaibu.adapter.CheckSupplyListAdapter;
import com.yibu.kuaibu.adapter.ShoppingAdapter;
import com.yibu.kuaibu.adapter.CartListAdapter.OnChangeSelectListener;
import com.yibu.kuaibu.adapter.search.CompanyListAdapter;
import com.yibu.kuaibu.adapter.search.MallListAdapter;
import com.yibu.kuaibu.adapter.search.SearchTypeGridAdapter;
import com.yibu.kuaibu.adapterly.MyAdapter;
import com.yibu.kuaibu.adapterly.shangjituijian.ShangJiTuiJianImageAndText;
import com.yibu.kuaibu.adapterly.shangjituijian.ShangJiTuiJianImageAndTextListAdapter;
import com.yibu.kuaibu.app.activity.CaiGouActivity;
import com.yibu.kuaibu.app.activity.GongYingActivity;
import com.yibu.kuaibu.app.activity.LeiMuActivity;
import com.yibu.kuaibu.app.caigou.CaigouPublish;
import com.yibu.kuaibu.app.caigou.CheckCaigou;
import com.yibu.kuaibu.app.myCaigouAndSupply.MyCaigou;
import com.yibu.kuaibu.app.myCaigouAndSupply.MySupply;
import com.yibu.kuaibu.app.supply.CheckSupply;
import com.yibu.kuaibu.app.supply.SortType;
import com.yibu.kuaibu.app.supply.SupplyPublish;
import com.yibu.kuaibu.bean.CartListItem.ChildState;
import com.yibu.kuaibu.bean.CartListItem.GroupState;
import com.yibu.kuaibu.bean.gson.CaigouItemGson;
import com.yibu.kuaibu.bean.gson.CartItemGson;
import com.yibu.kuaibu.bean.gson.CompanyListGson;
import com.yibu.kuaibu.bean.gson.MallListGson;
import com.yibu.kuaibu.bean.gson.SupplyItemGson;
import com.yibu.kuaibu.bean.gson.CaigouItemGson.SupplyItem;
import com.yibu.kuaibu.bean.gson.CompanyListGson.ChildItem;
import com.yibu.kuaibu.data.Unit;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.ReqsyListener;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.net.model.gongying.GongYingRequest;
import com.yibu.kuaibu.ui.FilpperListvew;
import com.yibu.kuaibu.ui.LocalGridView;
import com.yibu.kuaibu.ui.LocalListView;
import com.yibu.kuaibu.ui.ModelPopup;
import com.yibu.kuaibu.ui.ScrollBottomScrollView;
import com.yibu.kuaibu.ui.ScrollLayout;
import com.yibu.kuaibu.ui.ModelPopup.OnDialogListener;
import com.yibu.kuaibu.ui.ScrollBottomScrollView.ScrollBottomListener;
import com.yibu.kuaibu.utils.AppToast;
import com.yibu.kuaibu.utils.GsonUtils;
import com.yibu.kuaibu.utils.HttpsUtil;
import com.yibu.kuaibu.utils.NetWorkUtil;
import com.yibu.kuaibu.utils.YhdConstant;

public class Ma extends Activity implements OnClickListener
{
	private glApplication appContext;

	private RadioGroup mRadioGroup;
	private RadioButton mShouYeRadioButton;
	private RadioButton mKCenterRadioButton;
	private RadioButton mGongLuRadioButton;
	private RadioButton mSheZhiRadioButton;
	private RadioButton mcartsButton;

	private RelativeLayout mheaderlayout;

	private TextView mHeadTitle;

	// private ImageView memailview;// 邮件消息
	private ScrollLayout mScrollLayout;

	private static final int BACK = 1;
	private static final int BACKGROUND = 2;

	// 个人中心
	private LinearLayout layout_user;

	private ModelPopup mPopup;

	// 个人中心， 我的采购按钮
	private ImageView mwodecaigou;
	// 个人中心， 我的供应按钮
	private ImageView mwodegongying;
	// 个人中心， 浏览商城按钮
	private ImageView mliulanstore;
	// 个人中心，我的头像
	private ImageView mwodeicon;
	private Uri photoUri;
	/***
	 * 使用照相机拍照获取图片
	 */
	public static final int SELECT_PIC_BY_TACK_PHOTO = 1;
	/***
	 * 使用相册中的图片
	 */
	public static final int SELECT_PIC_BY_PICK_PHOTO = 2;
	// cut
	private static final int CUT_PHOTO = 3;

	// 个人中心
	// 关于我们

	// // 关于我们
	RelativeLayout uGuanYu;
	// 我的认证
	RelativeLayout uRenzheng;
	// 收货地址
	RelativeLayout udeliveryAddress;
	// 店铺信息
	RelativeLayout uShopinfo;
	// 我的订单
	RelativeLayout uOrder;

	/* 我的购物车 */

	private FilpperListvew shopping_listview;
	private ShoppingAdapter shopping_adapter;

	/* 首页 */

	// 广告条
	private ViewPager view_pager;
	// private LinearLayout mbannerLayout;
	private MyAdapter adapter;
	private LayoutInflater inflater;
	private View item;

	// 商机推荐
	private GridView sbiaoqiangridview;
	private GridView schanpintuijiangridview;
	private GridView sshangjigridview;

	// 获取首页数据listener
	private ReqsyListener mshouyeListener; // 暂时不需要对外接口
	private TextView head_title_right;
	private TextView marktv;
	private CartListAdapter cartListAdapter;
	private List<GroupState> grouplist;
	private int selectall_flag = 0;
	private TextView moneytv;
	private Button sumbtn;
	private ImageButton selectall;

	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{

			case 3:

				// 刷新首页 产品推荐
				List<ImageAndText> apthis = (List<ImageAndText>) msg.obj;

				schanpintuijiangridview.setAdapter(new ImageAndTextListAdapter(
						Ma.this, apthis, schanpintuijiangridview));

				break;

			case 4:
				// 刷新首页 商机推荐
				Log.e("4", "4 = ");
				List<ShangJiTuiJianImageAndText> apthis2 = (List<ShangJiTuiJianImageAndText>) msg.obj;

				sshangjigridview
						.setAdapter(new ShangJiTuiJianImageAndTextListAdapter(
								Ma.this, apthis2, sshangjigridview));
				break;
			case 5:
				ArrayList<HashMap<String, Object>> rmbqItem = new ArrayList<HashMap<String, Object>>();
				rmbqItem = (ArrayList<HashMap<String, Object>>) msg.obj;
				SimpleAdapter rmbqadapter = new SimpleAdapter(Ma.this,
						rmbqItem, R.layout.srmbqgridview_item,

						new String[] { "rmbqname" },

						new int[] { R.id.Itemname });
				// 添加并且显示
				sbiaoqiangridview.setAdapter(rmbqadapter);

				break;
				
			case 6:
				
				MyAdapter adapteraa = (MyAdapter) msg.obj;
				view_pager.setAdapter(adapteraa);
//				
				adapter.notifyDataSetChanged();
				
				break;

			default:
				break;

			}
			super.handleMessage(msg);
		}
	};

	//
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		WindowManager wm = this.getWindowManager();

		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();

		Log.e("width", "is " + width);
		Log.e("height", "is " + height);

		appContext = (glApplication) getApplication();
		glApplication.getMwzuser();
		init();

		// 线程获取首页各项数据
		getshouyedata();


        ///////类目
        findViewById(R.id.leimu).setOnClickListener(this);
        findViewById(R.id.tuangou).setOnClickListener(this);
        findViewById(R.id.gongying).setOnClickListener(this);
	}

	private void getshouyedata()
	{

		if (NetWorkUtil.isNetWork(Ma.this))
		{

			HttpClientUtil httpClientUtil = new HttpClientUtil();
			httpClientUtil.setOnGetData(new OnGetResponseData()
			{

				@Override
				public void OnGetData(String result)
				{
					Log.i("首页", "获取数据result = " + result);

					if (result == null)
					{

						Toast.makeText(getApplicationContext(),
								R.string.server_connect_error, 0).show();
					} else
					{

						try
						{
							JSONObject ob1 = new JSONObject(result);
							String returncode = ob1.getString("result");
							if (returncode.equalsIgnoreCase("1"))
							{
								// 获取data的json object
								JSONObject obj2 = new JSONObject(ob1
										.getString("data"));

								
								// 横幅
								List<View> list5 = new ArrayList<View>();
								inflater = LayoutInflater.from(Ma.this);
								
								JSONArray bannerarray = new JSONArray(obj2
										.getString("slidelist"));
								int bannersize = bannerarray.length();

								String[] urls = new String[bannersize];

								if (bannersize > 0)
								{
									for (int tnum = 0; tnum < bannersize; tnum++)
									{
										JSONObject itemforslide = new JSONObject(bannerarray
												.get(tnum).toString());
										urls[tnum] = itemforslide.getString("thumb").toString();
										Log.i("Ma","pic: " + tnum +":" +urls[tnum] );
										item = inflater.inflate(R.layout.banneritem, null);
										
										((TextView) item.findViewById(R.id.text_view)).setText("第 " + tnum+ " 个 viewPager");
										
										list5.add(item);
										
										
									}
								}
								
								Log.i("now","now0-------: " + urls.length);
								adapter = new MyAdapter(list5,urls);

								view_pager.setAdapter(adapter);
//								
								adapter.notifyDataSetChanged();
								
								ImageView[] indicator_imgs = new ImageView[7];
								view_pager.setOnPageChangeListener(new MyListener(indicator_imgs));
								
								initIndicator(bannersize,indicator_imgs);
								
								
//								Message msg6 = handler.obtainMessage();
//								msg6.what = 6;
//								msg6.obj = adapter;
//								handler.sendMessage(msg6);
								

								// // 解析热门标签
								JSONArray obj5 = new JSONArray(obj2
										.getString("taglist"));
								ArrayList<HashMap<String, Object>> rmbqItem = new ArrayList<HashMap<String, Object>>();
								for (int i = 0; i < obj5.length(); i++)
								{
									String itemobj = obj5.get(i).toString();
									HashMap<String, Object> map = new HashMap<String, Object>();
									map.put("rmbqname", "" + itemobj);// 产品推荐id

									rmbqItem.add(map);
								}

								Message msg4 = handler.obtainMessage();
								msg4.what = 5;
								msg4.obj = rmbqItem;
								handler.sendMessage(msg4);

								// 解析产品推荐
								JSONArray obj3 = new JSONArray(obj2
										.getString("malllist"));
								List<ImageAndText> list = new ArrayList<ImageAndText>();
								for (int i = 0; i < obj3.length(); i++)
								{

									JSONObject itemobj = new JSONObject(obj3
											.get(i).toString());
									
									
									
									
									
									list.add(new ImageAndText(itemobj
											.getString("itemid"), itemobj
											.getString("thumb"), itemobj
											.getString("title"), "¥"
											+ itemobj.getString("price"), null));
								}
								
								 schanpintuijiangridview.setOnItemClickListener(new
										 OnItemClickListener()
										 {
										
										 @Override
										 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
										 long arg3)
										 {
										 // TODO Auto-generated method stub
										 Intent intent = new Intent(Ma.this, YhCPXiangQing.class);
										 startActivity(intent);
										 }
										 });

								Message msg1 = handler.obtainMessage();

								msg1.what = 3;
								msg1.obj = list;
								handler.sendMessage(msg1);

								// 解析商机推荐
								JSONArray obj4 = new JSONArray(obj2
										.getString("selllist"));
								List<ShangJiTuiJianImageAndText> list1 = new ArrayList<ShangJiTuiJianImageAndText>();
								for (int i = 0; i < obj4.length(); i++)
								{

									JSONObject itemobj = new JSONObject(obj4
											.get(i).toString());
									list1.add(new ShangJiTuiJianImageAndText(
											itemobj.getString("itemid"),
											itemobj.getString("thumb"), itemobj
													.getString("title"),
											itemobj.getString("editdate"),
											itemobj.getString("hits"), null));
								}
								
								
								sshangjigridview.setOnItemClickListener(new
										 OnItemClickListener()
										 {
										
										 @Override
										 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
										 long arg3)
										 {
										 // TODO Auto-generated method stub
										 Intent intent = new Intent(Ma.this, YhShangChengOne.class);
										 startActivity(intent);
										 }
										 });
								
								

								Message msg2 = handler.obtainMessage();

								msg2.what = 4;
								msg2.obj = list1;
								handler.sendMessage(msg2);

							} else if (returncode.equalsIgnoreCase("0"))
							{
								String errorstr = ob1.getString("error");
								Toast.makeText(getApplicationContext(),
										errorstr, 0).show();
							}

						} catch (JSONException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

				private void initIndicator(int bannersize, ImageView[] indicator_imgs){
					
					ImageView imgView;
					View v = findViewById(R.id.indicator);// 线性水平布局，负责动态调整导航图标
					
					for (int i = 0; i < bannersize; i++) {
						imgView = new ImageView(Ma.this);
						LinearLayout.LayoutParams params_linear = new LinearLayout.LayoutParams(10,10);
						params_linear.setMargins(7, 10, 7, 10);
						imgView.setLayoutParams(params_linear);
						indicator_imgs[i] = imgView;
						
						if (i == 0) { // 初始化第一个为选中状态
							
							indicator_imgs[i].setBackgroundResource(R.drawable.dot_selected);
						} else {
							indicator_imgs[i].setBackgroundResource(R.drawable.dot_unselected);
						}
//						((ViewGroup) v).addView(indicator_imgs[i]);
						
						((ViewGroup)v).addView(indicator_imgs[i]);
					}
					
				}
			});

			Map<String, String> datas = new HashMap<String, String>();
//			datas.put("token", glApplication.mwzuser.getUtoken());
			//TODO
			datas.put("token", glApplication.mwzuser.getUtoken());

			try
			{
				httpClientUtil.postRequest(YhdConstant.yhd_service_url
						+ YhdConstant.yhd_get_shouye, datas);

			} catch (Exception e)
			{
				e.printStackTrace();
			}

		} else
		{
			Toast.makeText(getApplicationContext(),
					R.string.network_not_connected, 0).show();
		}

		// // 线程获取数据
		// if (!Unit.getThreadPoolInstance().isShutdown())
		// {
		// Unit.getThreadPoolInstance().execute(new Runnable()
		// {
		// boolean mRun = true;
		// boolean isRequest = false;
		//
		// @Override
		// public void run()
		// {
		// while (mRun)
		// {
		//
		// if (NetStateManager.isOnline())
		// {
		// Reqsydata(new ReqsyListener()
		// {
		//
		// @Override
		// public void onGet(String response)
		// {
		// // TODO Auto-generated method stub
		// Log.i("tt", "onGet:" + response);
		// if (response != null
		// && !response.equalsIgnoreCase(""))
		// {
		// try
		// {
		// JSONObject obj1 = new JSONObject(
		// response);
		// String status = obj1
		// .getString("result");
		// if (status.equalsIgnoreCase("1"))
		// {
		// JSONObject obj2 = new JSONObject(
		// obj1.getString("data"));
		//
		// // 解析热门标签
		// JSONArray obj5 = new JSONArray(
		// obj2.getString("taglist"));
		// ArrayList<HashMap<String, Object>> rmbqItem = new
		// ArrayList<HashMap<String, Object>>();
		// for (int i = 0; i < obj5
		// .length(); i++)
		// {
		// String itemobj = obj5
		// .get(i).toString();
		// HashMap<String, Object> map = new HashMap<String, Object>();
		// map.put("rmbqname", ""
		// + itemobj);// 产品推荐id
		//
		// rmbqItem.add(map);
		// }
		// // 生成适配器的ImageItem <====>
		// // 动态数组的元素，两者一一对应
		// SimpleAdapter rmbqadapter = new SimpleAdapter(
		// Ma.this, // 没什么解释
		// rmbqItem,// 数据来源
		// R.layout.srmbqgridview_item,// night_item的XML实现
		//
		// // 动态数组与ImageItem对应的子项
		// new String[] { "rmbqname" },
		//
		// // ImageItem的XML文件里面的一个ImageView,两个TextView
		// // ID
		// new int[] { R.id.Itemname });
		// // 添加并且显示
		// sbiaoqiangridview
		// .setAdapter(rmbqadapter);
		//
		//
		//
		// // 解析产品推荐
		// JSONArray obj3 = new JSONArray(
		// obj2.getString("malllist"));
		// List<ImageAndText> list = new ArrayList<ImageAndText>();
		// for (int i = 0; i < obj3
		// .length(); i++)
		// {
		//
		// JSONObject itemobj = new JSONObject(
		// obj3.get(i)
		// .toString());
		// list.add(new ImageAndText(
		// itemobj.getString("itemid"),
		// YhdConstant.yhd_pic_rul
		// + itemobj
		// .getString("thumb"),
		// itemobj.getString("title"),
		// "¥"
		// + itemobj
		// .getString("price"),null));
		// }
		//
		// Message msg1 = handler
		// .obtainMessage();
		// ;
		// msg1.what = 3;
		// msg1.obj = list;
		// handler.sendMessage(msg1);
		//
		//
		// // schanpintuijiangridview
		// // .setAdapter(new ImageAndTextListAdapter(
		// // Ma.this, list,
		// // schanpintuijiangridview));
		//
		//
		//
		// // 解析商机推荐
		// JSONArray obj4 = new JSONArray(
		// obj2.getString("selllist"));
		// ArrayList<HashMap<String, Object>> sjtjItem = new
		// ArrayList<HashMap<String, Object>>();
		// for (int i = 0; i < obj4
		// .length(); i++)
		// {
		// JSONObject itemobj = new JSONObject(
		// obj4.get(i)
		// .toString());
		// HashMap<String, Object> map = new HashMap<String, Object>();
		// map.put("ItemImage",
		// R.drawable.test_yhdwodegongyingpic2);// 添加图像资源的ID
		// map.put("ItemText", "NO."
		// + String.valueOf(i));// 按序号做ItemText
		//
		// map.put("sjtjid",
		// ""
		// + itemobj
		// .getString("itemid"));// sj推荐id
		// map.put("sjtjtitle",
		// ""
		// + itemobj
		// .getString("title"));// sj推荐title
		// map.put("sjtjhits", itemobj
		// .getString("hits"));// sj推荐hits
		// map.put("sjtjedittime",
		// ""
		// + itemobj
		// .getString("editdate"));// sj推荐edittime
		// map.put("sjtjthumb",
		// ""
		// + itemobj
		// .getString("thumb"));// sj推荐thumb
		// sjtjItem.add(map);
		// }
		// // 生成适配器的ImageItem <====>
		// // 动态数组的元素，两者一一对应
		// SimpleAdapter sjtjadapter = new SimpleAdapter(
		// Ma.this, // 没什么解释
		// sjtjItem,// 数据来源
		// R.layout.ssjtjgridview_item1,// night_item的XML实现
		//
		// // 动态数组与ImageItem对应的子项
		// new String[] {
		// "ItemImage",
		// "sjtjtitle",
		// "sjtjedittime",
		// "sjtjhits" },
		//
		// // ImageItem的XML文件里面的一个ImageView,两个TextView
		// // ID
		// new int[] {
		// R.id.ItemImage,
		// R.id.ItemText,
		// R.id.ItemTime,
		// R.id.ItemLiulan });
		// // 添加并且显示
		// sshangjigridview
		// .setAdapter(sjtjadapter);
		//
		// }
		//
		// } catch (JSONException e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// mRun = false;
		// }
		//
		// @Override
		// public void onIOExc(IOException e)
		// {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onErr(Exception e)
		// {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// });
		// isRequest = true;
		// }
		// try
		// {
		// if (isRequest == true)
		// {
		// Thread.sleep(15000);// 15s
		// isRequest = false;
		// } else
		// {
		// Thread.sleep(2000);// 5s
		// }
		//
		// } catch (InterruptedException e1)
		// {
		// e1.printStackTrace();
		// }
		// }
		// }
		// });
		// }

	}

	protected void Reqsydata(final ReqsyListener ttreqsyListener)
	{
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
										+ YhdConstant.yhd_get_shouye
										+ YhdConstant.yhd_mock, mparams,
								"UTF-8");

						// Log.e("Ma","String = " + response);

						if (ttreqsyListener != null)
						{
							// Log.e("Ma","String1 = " + response);
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

	private void startActivitySafely(Intent intent)
	{
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		try
		{
			startActivity(intent);
		} catch (ActivityNotFoundException e)
		{
			Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
		} catch (SecurityException e)
		{
			Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onDestroy()
	{

		// closeApp();
		super.onDestroy();
	}

	protected void init()
	{
		findId();

		initHeadView();
		initScrollLayout();
		initFrames();
		initFootBar();

		initFrist();

		setWodelistener();

	}

	private void setWodelistener()
	{
		// TODO Auto-generated method stub

		// 个人中心 关于我们
		uGuanYu.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(Ma.this, YhAbout.class);
				startActivity(intent);

			}
		});

		// 个人中心 我的认证
		uRenzheng.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(Ma.this, YhRenzheng.class);
				startActivity(intent);
			}
		});

		// 个人中心 收货地址
		udeliveryAddress.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(Ma.this, YhDeliveryAddress.class);
				startActivity(intent);
			}
		});

		// 个人中心 我的店铺
		uShopinfo.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(Ma.this, YhWodeDianpu.class);
				startActivity(intent);
			}
		});

		// 个人中心 我的订单
		uOrder.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(Ma.this, YhWodeOrder.class);
				startActivity(intent);
			}
		});
	}

	private FrameLayout load_content;

	private ListView cartListView;

	private void initFrames()
	{

		// 个人中心的listview
		initMine();

		// 购物车
		initcart();

		// 搜索
		initSearch();
	}

	private ScrollBottomScrollView search_scrollview;
	private ProgressBar search_load_more;
	private LocalListView search_list;
	private LocalGridView search_grid;
	private CaigouItemGson caigouItemGson; // 采购gson
	private SupplyItemGson supplyItemGson; // 供应gson
	private CompanyListGson companyListGson; // 店铺gson
	private MallListGson mallListGson; // 产品gson
	private SearchTypeGridAdapter searchTypeGridAdapter;
	private CheckCaigouListAdapter checkCaigouListAdapter; // 采购adapter
	private CheckSupplyListAdapter checkSupplyListAdapter; // 供应adapter
	private CompanyListAdapter companyListAdapter; // 店铺adapter
	private MallListAdapter mallListAdapter; // 产品adapter
	private TextView search_caigou; // 采购
	private TextView search_supply; // 供应
	private TextView search_store; // 店铺
	private TextView search_product; // 产品
	private TextView search_part1;
	private TextView search_part2;
	private TextView search_part3;
	private TextView truetv;
	private FrameLayout search_load_content;
	private EditText search_keyword; // 输入关键字
	private int search_tag = 0; // 0:采购；1:供应；2:店铺；3：产品
	private ArrayList<String> sort = new ArrayList<String>(); // keywords
	private ArrayList<String> catid = new ArrayList<String>(); // catid
	private int pages = 1; // 当前页面
	private int pagetotal = 0; // 总页数
	private String isvip = null; // null 为全部 1 为vip或样板

	private void initSearch()
	{
		search_grid = (LocalGridView) findViewById(R.id.search_grid);
		search_list = (LocalListView) findViewById(R.id.search_list);
		search_caigou = (TextView) findViewById(R.id.search_caigou);
		search_supply = (TextView) findViewById(R.id.search_supply);
		search_store = (TextView) findViewById(R.id.search_store);
		search_product = (TextView) findViewById(R.id.search_product);
		search_part1 = (TextView) findViewById(R.id.search_part1);
		search_part2 = (TextView) findViewById(R.id.search_part2);
		search_part3 = (TextView) findViewById(R.id.search_part3);
		search_load_content = (FrameLayout) findViewById(R.id.search_load_content);
		search_keyword = (EditText) findViewById(R.id.search_keyword);
		truetv = (TextView) findViewById(R.id.truetv);
		search_scrollview = (ScrollBottomScrollView) findViewById(R.id.search_scrollview);
		search_load_more = (ProgressBar) findViewById(R.id.search_load_more);
		search_caigou.setOnClickListener(this);
		search_supply.setOnClickListener(this);
		search_store.setOnClickListener(this);
		search_product.setOnClickListener(this);
		search_part1.setOnClickListener(this);
		search_part2.setOnClickListener(this);
		search_part3.setOnClickListener(this);
		search_grid.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				sort.remove(position);
				searchTypeGridAdapter.setList(sort);
				searchTypeGridAdapter.notifyDataSetChanged();
			}
		});
		search_scrollview.setScrollBottomListener(new ScrollBottomListener()
		{
			@Override
			public void scrollBottom()
			{
				if (pages == pagetotal)
				{
				} else
				{
					pages = pages + 1;
					initSearchData(isvip, true);
				}
			}
		});
		truetv.setOnClickListener(this);
	}

	private void initcart()
	{
		// TODO Auto-generated method stub
		// shopping_listview = (FilpperListvew)
		// findViewById(R.id.shopping_listview);
		//
		// shopping_adapter = new ShoppingAdapter(Ma.this,
		// new ShoppingManage().getProducts(Ma.this, null));
		// shopping_listview.setAdapter(shopping_adapter);
		moneytv = (TextView) findViewById(R.id.moneytv);
		sumbtn = (Button) findViewById(R.id.sumbtn);
		sumbtn.setOnClickListener(this); // 结算|删除按钮
		marktv = (TextView) findViewById(R.id.marktv);
		load_content = (FrameLayout) findViewById(R.id.load_content);

		selectall = (ImageButton) findViewById(R.id.selectall);
		selectall.setOnClickListener(this);
		cartListView = (ListView) findViewById(R.id.cartlist);
	}

	private void initcartdata()
	{
		if (NetWorkUtil.isNetWork(Ma.this))
		{
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			httpClientUtil.setOnGetData(new OnGetResponseData()
			{
				@Override
				public void OnGetData(String result)
				{
					if (result == null)
					{
						load_content.setVisibility(View.GONE);
						Toast.makeText(getApplicationContext(),
								R.string.server_connect_error, 0).show();
						return;
					}
					CartItemGson item = GsonUtils.jsonToBean(result,
							CartItemGson.class);
					if (item.result == 1)
					{
						load_content.setVisibility(View.GONE);
						grouplist = initCartData(item);
						// cartListView = (ListView)
						// findViewById(R.id.cartlist);
						cartListView.setDivider(null);
						cartListAdapter = new CartListAdapter(grouplist,
								Ma.this);
						cartListAdapter
								.setOnChangeSelectListener(new OnChangeSelectListener()
								{
									@Override
									public void OnChange(double result)
									{
										String text = moneytv.getText()
												.toString().trim();
										String money = text.substring(4);
										String total = (Double
												.parseDouble(money) + result)
												+ "";
										moneytv.setText("合计：￥" + total);
									}
								});
						cartListView.setAdapter(cartListAdapter);
					} else
					{
						load_content.setVisibility(View.GONE);
						Toast.makeText(getApplicationContext(),
								R.string.server_connect_error, 0).show();
					}
				}
			});
			try
			{
				load_content.setVisibility(View.VISIBLE);
				Map<String, String> map = new HashMap<String, String>();
				map.put("token",
						"D2t2eFRjBGJtUngTexZ5FSgYZgclKWpUc3hvC2YBEHRSM2xSextVP2tSdkNxS0EgIBhBdDMGBz57GlA5VDIiFA9ldhBUMAQ6bQZ4T3tDeUMoTmZRJRNqAHNIbzZmBA");
				httpClientUtil.postRequest(
						"http://yihaobu.hu8hu.com/app/getCartList.php", map);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		} else
		{
			Toast.makeText(getApplicationContext(),
					R.string.network_not_connected, 0).show();
		}
	}

	// OnDialogListener 接口方法
	/**
	 * 
	 * 系统相机拍照
	 */
	private void takePhoto()
	{
		// TODO Auto-generated method stub
		// 执行拍照前，应该先判断SD卡是否存在
		String SDState = Environment.getExternalStorageState();
		if (!SDState.equals(Environment.MEDIA_MOUNTED))
		{
			AppToast.showShortText(Ma.this, "内存卡不存在");
			return;
		}
		try
		{
			photoUri = getContentResolver().insert(
					MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
					new ContentValues());
			if (photoUri != null)
			{
				Intent i = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoUri);
				startActivityForResult(i, SELECT_PIC_BY_TACK_PHOTO);

			} else
			{
				AppToast.showShortText(Ma.this, "发生意外，无法写入相册");
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			AppToast.showShortText(Ma.this, "发生意外，无法写入相册");
		}
	}

	private void choosePhoto()
	{

		Intent choosePictureIntent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(choosePictureIntent, SELECT_PIC_BY_PICK_PHOTO);

	}

	private void initMine()
	{

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

		mPopup = new ModelPopup(Ma.this, mlistener, false);

		// 我的采购
		mwodecaigou = (ImageView) findViewById(R.id.mine_wodecaigou);
		mwodecaigou.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				// Intent intent = new Intent(Ma.this, YhMyCaigou.class);
				// startActivity(intent);
				Intent intent = new Intent(Ma.this, MyCaigou.class);
				startActivity(intent);
			}
		});

		// 我的供应
		mwodegongying = (ImageView) findViewById(R.id.mine_wodegongying);
		mwodegongying.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				// Intent intent = new Intent(Ma.this, YhMyGongying.class);
				Intent intent = new Intent(Ma.this, MySupply.class);
				startActivity(intent);
			}
		});

		// 浏览商城
		mliulanstore = (ImageView) findViewById(R.id.mine_shangcheng);
		mliulanstore.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent(Ma.this, YhMyStore.class);
				startActivity(intent);
			}
		});

		layout_user = (LinearLayout) findViewById(R.id.mine_user_layout);
		// 个人中心，我的头像
		mwodeicon = (ImageView) findViewById(R.id.mine_icon);
		mwodeicon.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				mPopup.showAtLocation(layout_user, Gravity.BOTTOM, 0, 0);

			}
		});

	}

	private void initScrollLayout()
	{
		mScrollLayout = (ScrollLayout) findViewById(R.id.main_scrolllayout);
		mScrollLayout
				.SetOnViewChangeListener(new ScrollLayout.OnViewChangeListener()
				{

					@Override
					public void OnViewChange(int viewIndex)
					{
						switch (viewIndex)
						{
						case 1://

							Log.i("ma", "initScrollLayout");
							// if (lvKCenterData.isEmpty())
							// {
							// KCenter_foot_more.setText(R.string.load_ing);
							// KCenter_foot_progress
							// .setVisibility(View.VISIBLE);
							//
							// loadAdData(curKCenterCatalog, 0,
							// KCenterHandler,
							// Unit.LISTVIEW_ACTION_INIT);
							//
							// // q请求LISTVIEW_ACTION_INIT
							//
							// }
							break;
						}
						// setCurPoint(viewIndex);
					}

				});
	}

	private void initHeadView()
	{
		mHeadTitle = (TextView) findViewById(R.id.main_head_title);

	}

	private void initFrist()
	{
		if (mShouYeRadioButton == null)
		{
			Log.i("Ma", "mShouYeRadioButton null");
		} else
		{
			Log.i("Ma", "mShouYeRadioButton not null");
		}

		mRadioGroup.check(mShouYeRadioButton.getId());

	}

	private void initFootBar()
	{
		mRadioGroup = (RadioGroup) findViewById(R.id.main_footbar_group);
		mShouYeRadioButton = (RadioButton) findViewById(R.id.main_footbar_shouye);
		mKCenterRadioButton = (RadioButton) findViewById(R.id.main_footbar_kcenter);
		mGongLuRadioButton = (RadioButton) findViewById(R.id.main_footbar_gonglu);
		mSheZhiRadioButton = (RadioButton) findViewById(R.id.main_footbar_shezhi);
		mcartsButton = (RadioButton) findViewById(R.id.main_footbar_customer);

		mShouYeRadioButton.setTag(0);
		mKCenterRadioButton.setTag(1);
		mGongLuRadioButton.setTag(2);
		mSheZhiRadioButton.setTag(3);
		mcartsButton.setTag(4);

		mRadioGroup.setOnCheckedChangeListener(new FootRadioGroupListener());

	}

	DialogInterface.OnClickListener backgroudListener = new DialogInterface.OnClickListener()
	{

		public void onClick(DialogInterface dialog, int which)
		{
			PackageManager pm = getPackageManager();
			ResolveInfo homeInfo = pm.resolveActivity(new Intent(
					Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME), 0);

			ActivityInfo ai = homeInfo.activityInfo;
			Intent startIntent = new Intent(Intent.ACTION_MAIN);
			startIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			startIntent
					.setComponent(new ComponentName(ai.packageName, ai.name));
			startActivitySafely(startIntent);

		}
	};

	@Override
	protected void onStart()
	{
		super.onStart();

	}

	@Override
	protected void onRestart()
	{
		super.onRestart();

	}

	@Override
	protected void onResume()
	{

		super.onResume();
	}

	@Override
	protected void onPause()
	{
		super.onPause();

	}

	@Override
	protected void onStop()
	{
		super.onStop();

	}

	/*public boolean onKeyDown(int keyCode, KeyEvent event)
	{

		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			showDialog(BACKGROUND);
			return true;
		} else
			return super.onKeyDown(keyCode, event);

	}*/

	protected void findId()
	{

		findwode();

		mHeadTitle = (TextView) findViewById(R.id.main_head_title);
		head_title_right = (TextView) findViewById(R.id.head_title_right);

		initTestFirstUI();
		mheaderlayout = (RelativeLayout) findViewById(R.id.main_header);
		head_title_right.setOnClickListener(this);

		// 广告条
		// mbannerLayout = (LinearLayout) findViewById(R.id.bannerLayout);
		view_pager = (ViewPager) findViewById(R.id.view_pager);

		// 商机推荐
		sshangjigridview = (GridView) findViewById(R.id.shangjigridview);
		sbiaoqiangridview = (GridView) findViewById(R.id.biaoqiangridview);
		schanpintuijiangridview = (GridView) findViewById(R.id.chanpintuijiangridview);
	}

	private void findwode()
	{
		// TODO Auto-generated method stub
		// 个人中心 关于我们
		uGuanYu = (RelativeLayout) findViewById(R.id.mineguanyuwomen);
		// 个人中心 我的认证
		uRenzheng = (RelativeLayout) findViewById(R.id.minewoderenzheng);
		// 个人中心 收货地址
		udeliveryAddress = (RelativeLayout) findViewById(R.id.uMyAddress);
		// 个人中心 店铺信息
		uShopinfo = (RelativeLayout) findViewById(R.id.minemyshop);
		// 个人中心 我的订单
		uOrder = (RelativeLayout) findViewById(R.id.minemyorder);

	}

	private LinearLayout checksupply;
	private LinearLayout checkcaigou;
	private ImageView supplypublish;
	private ImageView caigoupublish;
	private ImageView syshangcheng;

	/* 首页控件 */
	private void initTestFirstUI()
	{
		checksupply = (LinearLayout) findViewById(R.id.gongying);
		// 商机推荐
		checkcaigou = (LinearLayout) findViewById(R.id.checkcaigou);
		supplypublish = (ImageView) findViewById(R.id.supplypublish);
		caigoupublish = (ImageView) findViewById(R.id.caigoupublish);
		syshangcheng = (ImageView) findViewById(R.id.s_shangcheng);

		checksupply.setOnClickListener(this);
		checkcaigou.setOnClickListener(this);
		supplypublish.setOnClickListener(this);
		caigoupublish.setOnClickListener(this);
		// 商城点击事件 ly
		syshangcheng.setOnClickListener(this);

	}

	class FootRadioGroupListener implements OnCheckedChangeListener
	{
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{
			int pos = 0;
			if (checkedId == mShouYeRadioButton.getId())
			{
				Log.i("test", "test");
				mHeadTitle.setText(R.string.shouye);
				mHeadTitle.setVisibility(View.VISIBLE);
				// mheaderlayout.setVisibility(View.GONE);
				pos = (Integer) mShouYeRadioButton.getTag();

			} else if (checkedId == mKCenterRadioButton.getId())
			{
				mHeadTitle.setText(R.string.serch);
				mHeadTitle.setVisibility(View.VISIBLE);
				pos = (Integer) mKCenterRadioButton.getTag();

			} else if (checkedId == mGongLuRadioButton.getId())
			{
				mHeadTitle.setText(R.string.msg);
				mHeadTitle.setVisibility(View.VISIBLE);
				pos = (Integer) mGongLuRadioButton.getTag();
			} else if (checkedId == mSheZhiRadioButton.getId())
			{
				mHeadTitle.setText(R.string.wode);
				mHeadTitle.setVisibility(View.VISIBLE);
				// mheaderlayout.setVisibility(View.VISIBLE);

				pos = (Integer) mSheZhiRadioButton.getTag();
			} else if (checkedId == mcartsButton.getId())
			{
				mHeadTitle.setText(R.string.mycarts);
				mHeadTitle.setVisibility(View.VISIBLE);
				head_title_right.setText(R.string.edit);
				pos = (Integer) mcartsButton.getTag();
				initcartdata();
			}
			mScrollLayout.snapToScreen(pos);
		}
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent)
	{
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == RESULT_OK)
		{
			switch (requestCode)
			{
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
		if (requestCode == 0 && resultCode == 100)
		{
			sort = intent.getStringArrayListExtra("sort");
			catid = intent.getStringArrayListExtra("catid");
			if (sort.size() > 0)
			{
				if (searchTypeGridAdapter == null)
				{
					searchTypeGridAdapter = new SearchTypeGridAdapter(sort,
							this);
					search_grid.setAdapter(searchTypeGridAdapter);
				} else
				{
					searchTypeGridAdapter.setList(sort);
					searchTypeGridAdapter.notifyDataSetChanged();
				}
			}

		}
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void beginCrop(Uri uri)
	{
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY
		// 是裁剪图片宽高，注意如果return-data=true情况下,其实得到的是缩略图，并不是真实拍摄的图片大小，
		// 而原因是拍照的图片太大，所以这个宽高当你设置很大的时候发现并不起作用，就是因为返回的原图是缩略图，但是作为头像还是够清晰了
		intent.putExtra("outputX", 150);
		intent.putExtra("outputY", 150);
		// 返回图片数据
		intent.putExtra("return-data", true);
		startActivityForResult(intent, CUT_PHOTO);
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param result
	 */
	private void handleCrop(Intent result)
	{
		Bundle extras = result.getExtras();
		if (extras != null)
		{
			Bitmap photo = extras.getParcelable("data");
			mwodeicon.setImageBitmap(photo);
		}
	}

	/**
	 * 点击监听处理事件
	 */
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.head_title_right:

			if (mHeadTitle.getText().equals("我的购物车")
					&& head_title_right.getText().equals("编辑"))
			{
				// Toast.makeText(getApplicationContext(), "bianji", 0).show();
				sumbtn.setText("删 除");
				moneytv.setVisibility(View.GONE);
				marktv.setVisibility(View.GONE);
				cartListSelectNo();
				if (selectall_flag == 1)
				{
					selectall.setImageResource(R.drawable.selectno);
					selectall_flag = 0;
				}

				((TextView) v).setText("完成");
			} else if (mHeadTitle.getText().equals("我的购物车")
					&& head_title_right.getText().equals("完成"))
			{
				// Toast.makeText(getApplicationContext(), "bianji", 0).show();

				sumbtn.setText("结 算");
				moneytv.setVisibility(View.VISIBLE);
				marktv.setVisibility(View.VISIBLE);
				moneytv.setText("合计：￥" + 0.0);
				cartListSelectNo();
				if (selectall_flag == 1)
				{
					selectall.setImageResource(R.drawable.selectno);
					selectall_flag = 0;
				}

				((TextView) v).setText("编辑");
			}
			break;
		case R.id.selectall: // 全选按钮
			if (selectall_flag == 0)
			{ // 正选
				double total = 0;
				for (int i = 0; i < grouplist.size(); i++)
				{
					grouplist.get(i).groupstate = 1;
					for (int j = 0; j < grouplist.get(i).childlist.size(); j++)
					{
						grouplist.get(i).childlist.get(j).childstate = 1;
						total += grouplist.get(i).childlist.get(j).childItem.price;
					}
				}
				cartListAdapter.setGrouplist(grouplist);
				cartListAdapter.notifyDataSetChanged();
				moneytv.setText("合计：￥" + total);
				((ImageButton) v).setImageResource(R.drawable.selected);
				selectall_flag = 1;
			} else
			{
				// double total = 0;
				cartListSelectNo();
				moneytv.setText("合计：￥" + 0.0);
				((ImageButton) v).setImageResource(R.drawable.selectno);
				selectall_flag = 0;
			}

			break;
		case R.id.checkcaigou:
		{ // 查看采购
            CaiGouActivity.startActivity(this);
			break;
		}
		case R.id.checksupply:
		{ // 查看供应
			Intent intent = new Intent(this, CheckSupply.class);
			this.startActivity(intent);
			break;
		}
		case R.id.supplypublish:
		{ // 找布（发布供应）
			Intent intent = new Intent(this, SupplyPublish.class);
			this.startActivity(intent);
			break;
		}
		case R.id.caigoupublish:
		{ // 卖布（发布采购）
			Intent intent = new Intent(this, CaigouPublish.class);
			this.startActivity(intent);
			break;
		}

		case R.id.s_shangcheng:
		{
			Intent intent = new Intent(this, YhShangCheng.class);
			this.startActivity(intent);
			break;

		}
		case R.id.search_caigou:
		{ // 采购
			search_scrollview.fullScroll(ScrollView.FOCUS_UP); // 滚动到顶部
			search_part1.setTextColor(getResources().getColor(R.color.black));
			search_part2.setTextColor(getResources().getColor(R.color.black));
			searchCaigouSelected();
			cleanSearchData();
			search_tag = 0;
			break;
		}
		case R.id.search_supply:
		{ // 供应
			search_scrollview.fullScroll(ScrollView.FOCUS_UP); // 滚动到顶部
			search_part1.setTextColor(getResources().getColor(R.color.black));
			search_part2.setTextColor(getResources().getColor(R.color.black));
			searchSupplySelected();
			cleanSearchData();
			search_tag = 1;
			break;
		}
		case R.id.search_store:
		{ // 店铺
			search_scrollview.fullScroll(ScrollView.FOCUS_UP); // 滚动到顶部
			search_part1.setTextColor(getResources().getColor(R.color.black));
			search_part2.setTextColor(getResources().getColor(R.color.black));
			searchStoreSelected();
			cleanSearchData();
			search_tag = 2;
			break;
		}
		case R.id.search_product:
		{ // 产品
			search_scrollview.fullScroll(ScrollView.FOCUS_UP); // 滚动到顶部
			search_part2.setText("样板");
			search_part1.setTextColor(getResources().getColor(R.color.black));
			search_part2.setTextColor(getResources().getColor(R.color.black));
			searchProductSelected();
			cleanSearchData();
			search_tag = 3;
			break;
		}
		case R.id.search_part1:
		{ // 查看全部
			search_scrollview.fullScroll(ScrollView.FOCUS_UP); // 滚动到顶部
			search_part1.setTextColor(getResources().getColor(R.color.redcode));
			search_part2.setTextColor(getResources().getColor(R.color.black));
			search_part3.setTextColor(getResources().getColor(R.color.black));
			initSearchData(null, false);
			isvip = null;
			break;
		}
		case R.id.search_part2: // 仅看VIP|样板（产品）
		{
			search_scrollview.fullScroll(ScrollView.FOCUS_UP); // 滚动到顶部
			search_part1.setTextColor(getResources().getColor(R.color.black));
			search_part2.setTextColor(getResources().getColor(R.color.redcode));
			search_part3.setTextColor(getResources().getColor(R.color.black));
			initSearchData("1", false);
			isvip = "1";
			break;
		}
		case R.id.search_part3: // 筛选
			search_scrollview.fullScroll(ScrollView.FOCUS_UP); // 滚动到顶部
			Intent intent = new Intent(this, SortType.class);
			this.startActivityForResult(intent, 0);
			break;
		case R.id.truetv: // 关键字搜索
			search_scrollview.fullScroll(ScrollView.FOCUS_UP); // 滚动到顶部
			initSearchData(isvip, false);
			pages = 1;
			break;
        ////类目
            case  R.id.leimu:
            LeiMuActivity.startActivity(this);
            break;
            case R.id.tuangou:
                 GongYingActivity.startActivity(this, GongYingRequest.CU_XIAO);
                break;
            case R.id.gongying:
                GongYingActivity.startActivity(this,GongYingRequest.ALL);
                break;
		}
	}

	private void searchProductSelected()
	{
		search_caigou
				.setBackgroundColor(getResources().getColor(R.color.white));
		search_caigou.setTextColor(getResources().getColor(R.color.black));
		search_supply
				.setBackgroundColor(getResources().getColor(R.color.white));
		search_supply.setTextColor(getResources().getColor(R.color.black));
		search_store.setBackgroundColor(getResources().getColor(R.color.white));
		search_store.setTextColor(getResources().getColor(R.color.black));
		search_product.setBackgroundColor(getResources().getColor(
				R.color.redcode));
		search_product.setTextColor(getResources().getColor(R.color.white));
	}

	private void searchStoreSelected()
	{
		search_caigou
				.setBackgroundColor(getResources().getColor(R.color.white));
		search_caigou.setTextColor(getResources().getColor(R.color.black));
		search_supply
				.setBackgroundColor(getResources().getColor(R.color.white));
		search_supply.setTextColor(getResources().getColor(R.color.black));
		search_store.setBackgroundColor(getResources()
				.getColor(R.color.redcode));
		search_store.setTextColor(getResources().getColor(R.color.white));
		search_product.setBackgroundColor(getResources()
				.getColor(R.color.white));
		search_product.setTextColor(getResources().getColor(R.color.black));
	}

	private void searchSupplySelected()
	{
		search_caigou
				.setBackgroundColor(getResources().getColor(R.color.white));
		search_caigou.setTextColor(getResources().getColor(R.color.black));
		search_supply.setBackgroundColor(getResources().getColor(
				R.color.redcode));
		search_supply.setTextColor(getResources().getColor(R.color.white));
		search_store.setBackgroundColor(getResources().getColor(R.color.white));
		search_store.setTextColor(getResources().getColor(R.color.black));
		search_product.setBackgroundColor(getResources()
				.getColor(R.color.white));
		search_product.setTextColor(getResources().getColor(R.color.black));
	}

	private void searchCaigouSelected()
	{
		search_caigou.setBackgroundColor(getResources().getColor(
				R.color.redcode));
		search_caigou.setTextColor(getResources().getColor(R.color.white));
		search_supply
				.setBackgroundColor(getResources().getColor(R.color.white));
		search_supply.setTextColor(getResources().getColor(R.color.black));
		search_store.setBackgroundColor(getResources().getColor(R.color.white));
		search_store.setTextColor(getResources().getColor(R.color.black));
		search_product.setBackgroundColor(getResources()
				.getColor(R.color.white));
		search_product.setTextColor(getResources().getColor(R.color.black));
	}

	private void cleanSearchData()
	{
		pages = 1;
		pagetotal = 0;
		isvip = null;
		switch (search_tag)
		{
		case 0:
			if (checkCaigouListAdapter != null)
			{
				caigouItemGson.data.rslist.clear();
				checkCaigouListAdapter.setCaigouItemGson(caigouItemGson);
				checkCaigouListAdapter.notifyDataSetChanged();
			}
			break;
		case 1:
			if (checkSupplyListAdapter != null)
			{
				supplyItemGson.data.rslist.clear();
				checkSupplyListAdapter.setSupplyItemGson(supplyItemGson);
				checkSupplyListAdapter.notifyDataSetChanged();
			}
			break;
		case 2:
			if (companyListAdapter != null)
			{
				companyListGson.data.rslist.clear();
				companyListAdapter.setCompanyListGson(companyListGson);
				companyListAdapter.notifyDataSetChanged();
			}
			break;
		case 3:
			if (mallListAdapter != null)
			{
				mallListGson.data.rslist.clear();
				mallListAdapter.setMallListGson(mallListGson);
				mallListAdapter.notifyDataSetChanged();
			}
			break;

		}
	}

	/**
	 * 购物车列表反选
	 */
	private void cartListSelectNo()
	{
		for (int i = 0; i < grouplist.size(); i++)
		{
			grouplist.get(i).groupstate = 0;
			for (int j = 0; j < grouplist.get(i).childlist.size(); j++)
			{
				grouplist.get(i).childlist.get(j).childstate = 0;
				// total+=grouplist.get(i).childlist.get(j).childItem.price;
			}
		}
		cartListAdapter.setGrouplist(grouplist);
		cartListAdapter.notifyDataSetChanged();
	}

	/**
	 * 整理封装解析完成的Cart列表数据
	 */
	private List<GroupState> initCartData(CartItemGson item)
	{
		List<GroupState> grouplist = new ArrayList<GroupState>();
		for (int i = 0; i < item.data.rslist.size(); i++)
		{
			GroupState groupState = new GroupState();
			groupState.groupItem = item.data.rslist.get(i);
			// ChildState childState = new ChildState();
			List<ChildState> childlist = new ArrayList<ChildState>();
			for (int j = 0; j < item.data.rslist.get(i).cartlist.size(); j++)
			{
				ChildState childState = new ChildState();
				childState.childItem = item.data.rslist.get(i).cartlist.get(j);
				childlist.add(childState);
			}
			groupState.childlist = childlist;
			grouplist.add(groupState);
		}
		return grouplist;
	}

	private void initSearchData(String isvip, final boolean loadmore)
	{
		if (NetWorkUtil.isNetWork(Ma.this))
		{
			final HttpClientUtil httpClientUtil = new HttpClientUtil();
			httpClientUtil.setOnGetData(new OnGetResponseData()
			{
				@Override
				public void OnGetData(String result)
				{
					if (result == null)
					{
						search_load_more.setVisibility(View.GONE);
						search_load_content.setVisibility(View.GONE);
						Toast.makeText(getApplicationContext(),
								R.string.server_connect_error, 0).show();
					} else
					{
						handlerSearchResult(result, loadmore);
					}
				}
			});
			try
			{
				if (loadmore)
				{
					search_load_more.setVisibility(View.VISIBLE);
				} else
				{
					search_load_content.setVisibility(View.VISIBLE);
				}
				Map<String, String> datas = new HashMap<String, String>();
				String urlpath = "";
				urlpath = initSearchRequestParameter(isvip, pages, datas,
						urlpath);
				httpClientUtil.postRequest(urlpath, datas);
			} catch (Exception e)
			{
				search_load_more.setVisibility(View.GONE);
				search_load_content.setVisibility(View.GONE);
				Toast.makeText(getApplicationContext(),
						R.string.server_connect_error, 0).show();
				e.printStackTrace();
			}
		} else
		{
			Toast.makeText(getApplicationContext(),
					R.string.network_not_connected, 0).show();
		}
	}

	private void handlerSearchResult(String result, boolean loadmore)
	{
		switch (search_tag)
		{
		case 0:
			if (loadmore)
			{
				CaigouItemGson newCaigouItemGson = GsonUtils.jsonToBean(result,
						CaigouItemGson.class);
				if (newCaigouItemGson.result == 1)
				{
					for (SupplyItem item : newCaigouItemGson.data.rslist)
					{
						caigouItemGson.data.rslist.add(item);
					}
					pagetotal = newCaigouItemGson.data.page.pagetotal;
					checkCaigouListAdapter.setCaigouItemGson(caigouItemGson);
					checkCaigouListAdapter.notifyDataSetChanged();
					search_load_more.setVisibility(View.GONE);
				} else
				{
					search_load_more.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(),
							R.string.server_connect_error, 0).show();
				}
			} else
			{
				caigouItemGson = GsonUtils.jsonToBean(result,
						CaigouItemGson.class);
				if (caigouItemGson.result == 1)
				{
					if (checkCaigouListAdapter == null)
					{
						checkCaigouListAdapter = new CheckCaigouListAdapter(
								Ma.this, caigouItemGson);
					} else
					{
						checkCaigouListAdapter
								.setCaigouItemGson(caigouItemGson);
					}
					search_list.setAdapter(checkCaigouListAdapter);
					search_load_content.setVisibility(View.GONE);
				} else
				{
					load_content.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(),
							R.string.server_connect_error, 0).show();
				}
			}
			break;
		case 1:
			if (loadmore)
			{
				SupplyItemGson newSupplyItemGson = GsonUtils.jsonToBean(result,
						SupplyItemGson.class);
				if (newSupplyItemGson.result == 1)
				{
					for (com.yibu.kuaibu.bean.gson.SupplyItemGson.SupplyItem item : newSupplyItemGson.data.rslist)
					{
						supplyItemGson.data.rslist.add(item);
					}
					pagetotal = newSupplyItemGson.data.page.pagetotal;
					checkSupplyListAdapter.setSupplyItemGson(supplyItemGson);
					checkSupplyListAdapter.notifyDataSetChanged();
					search_load_more.setVisibility(View.GONE);
				} else
				{
					search_load_more.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(),
							R.string.server_connect_error, 0).show();
				}
			} else
			{
				supplyItemGson = GsonUtils.jsonToBean(result,
						SupplyItemGson.class);
				if (supplyItemGson.result == 1)
				{
					if (checkSupplyListAdapter == null)
					{
						checkSupplyListAdapter = new CheckSupplyListAdapter(
								Ma.this, supplyItemGson);
					} else
					{
						checkSupplyListAdapter
								.setSupplyItemGson(supplyItemGson);
					}
					search_list.setAdapter(checkSupplyListAdapter);
					search_load_content.setVisibility(View.GONE);
				} else
				{
					load_content.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(),
							R.string.server_connect_error, 0).show();
				}
			}
			break;
		case 2:
			if (loadmore)
			{
				CompanyListGson newCompanyListGson = GsonUtils.jsonToBean(
						result, CompanyListGson.class);
				if (newCompanyListGson.result == 1)
				{
					for (ChildItem item : newCompanyListGson.data.rslist)
					{
						companyListGson.data.rslist.add(item);
					}
					pagetotal = newCompanyListGson.data.page.pagetotal;
					companyListAdapter.setCompanyListGson(companyListGson);
					companyListAdapter.notifyDataSetChanged();
					search_load_more.setVisibility(View.GONE);
				} else
				{
					search_load_more.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(),
							R.string.server_connect_error, 0).show();
				}
			} else
			{
				companyListGson = GsonUtils.jsonToBean(result,
						CompanyListGson.class);
				if (companyListGson.result == 1)
				{
					if (companyListAdapter == null)
					{
						companyListAdapter = new CompanyListAdapter(Ma.this,
								companyListGson);
					} else
					{
						companyListAdapter.setCompanyListGson(companyListGson);
					}
					search_list.setAdapter(companyListAdapter);
					search_load_content.setVisibility(View.GONE);
				} else
				{
					load_content.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(),
							R.string.server_connect_error, 0).show();
				}
			}
			break;
		case 3:
			if (loadmore)
			{
				MallListGson newMallListGson = GsonUtils.jsonToBean(result,
						MallListGson.class);
				if (newMallListGson.result == 1)
				{
					for (com.yibu.kuaibu.bean.gson.MallListGson.ChildItem item : newMallListGson.data.rslist)
					{
						mallListGson.data.rslist.add(item);
					}
					pagetotal = newMallListGson.data.page.pagetotal;
					mallListAdapter.setMallListGson(mallListGson);
					mallListAdapter.notifyDataSetChanged();
					search_load_more.setVisibility(View.GONE);
				} else
				{
					search_load_more.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(),
							R.string.server_connect_error, 0).show();
				}
			} else
			{
				mallListGson = GsonUtils.jsonToBean(result, MallListGson.class);
				if (mallListGson.result == 1)
				{
					if (mallListAdapter == null)
					{
						mallListAdapter = new MallListAdapter(Ma.this,
								mallListGson);
					} else
					{
						mallListAdapter.setMallListGson(mallListGson);
					}
					search_list.setAdapter(mallListAdapter);
					search_load_content.setVisibility(View.GONE);
				} else
				{
					load_content.setVisibility(View.GONE);
					Toast.makeText(getApplicationContext(),
							R.string.server_connect_error, 0).show();
				}
			}
			break;
		}
	}

	private String initSearchRequestParameter(String isvip, int pages,
			Map<String, String> datas, String urlpath)
	{
		switch (search_tag)
		{
		case 0:
		{ // 采购
			datas.put("token", "PFxTXXRLdw1eb........");
			datas.put("catid", arrayListToString());
			String keyword = search_keyword.getText().toString().trim();
			if (keyword == null || keyword.equals("")
					|| keyword.equals("请输入关键词"))
			{
				keyword = "";
			}
			datas.put("keyword", keyword);
			if (isvip == null)
			{
			} else
			{
				datas.put("vip", 1 + "");
			}
			datas.put("pageid", pages + "");
			datas.put("pagesize", 20 + "");
			urlpath = "http://yihaobu.hu8hu.com/app/getBuyList.php";
			break;
		}
		case 1:
		{ // 供应
			datas.put("token", "PFxTXXRLdw1eb........");
			datas.put("catid", arrayListToString());
			String keyword = search_keyword.getText().toString().trim();
			if (keyword == null || keyword.equals("")
					|| keyword.equals("请输入关键词"))
			{
				keyword = "";
			}
			datas.put("keyword", keyword);
			if (isvip == null)
			{
			} else
			{
				datas.put("vip", 1 + "");
			}
			datas.put("pageid", pages + "");
			datas.put("pagesize", 20 + "");
			urlpath = "http://yihaobu.hu8hu.com/app/getSellList.php";
			break;
		}
		case 2:
		{ // 店铺
			datas.put("token", "PFxTXXRLdw1eb........");
			datas.put("catid", arrayListToString());
			String keyword = search_keyword.getText().toString().trim();
			if (keyword == null || keyword.equals("")
					|| keyword.equals("请输入关键词"))
			{
				keyword = "";
			}
			datas.put("keyword", keyword);
			if (isvip == null)
			{
			} else
			{
				datas.put("vip", 1 + "");
			}
			datas.put("pageid", pages + "");
			datas.put("pagesize", 20 + "");
			urlpath = "http://yihaobu.hu8hu.com/app/getCompanyList.php";
			break;
		}
		case 3:
		{ // 产品
			datas.put("token", "PFxTXXRLdw1eb........");
			datas.put("catid", arrayListToString());
			String keyword = search_keyword.getText().toString().trim();
			if (keyword == null || keyword.equals("")
					|| keyword.equals("请输入关键词"))
			{
				keyword = "";
			}
			datas.put("keyword", keyword);
			if (isvip == null)
			{
			} else
			{
				datas.put("typeid", 1 + "");
			}
			datas.put("pageid", pages + "");
			datas.put("pagesize", 20 + "");
			urlpath = "http://yihaobu.hu8hu.com/app/getMallList.php";
			break;
		}
		}
		return urlpath;
	}

	public String arrayListToString()
	{
		StringBuffer sb = new StringBuffer();
		if (catid.size() == 0)
		{
			return "";
		}
		for (int i = 0; i < catid.size(); i++)
		{
			if (i == catid.size() - 1)
			{
				sb.append(catid.get(i));
			} else
			{
				sb.append(catid.get(i) + ",");
			}
		}
		return sb.toString();
	}
	
	
	
}
