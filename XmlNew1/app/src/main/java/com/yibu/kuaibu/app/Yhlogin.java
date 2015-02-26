package com.yibu.kuaibu.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.net.HttpClientUtil;
import com.yibu.kuaibu.net.HttpClientUtil.OnGetResponseData;
import com.yibu.kuaibu.ui.Mdialog;
import com.yibu.kuaibu.ui.UIHelper;
import com.yibu.kuaibu.utils.NetWorkUtil;
import com.yibu.kuaibu.utils.StringUtils;
import com.yibu.kuaibu.utils.YhdConstant;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Yhlogin extends Activity
{

	

	private TabHost tabhost;

	private final int status_login = 1;
	private final int status_regis = 2;

	private EditText elogname;
	private EditText elogpass;
	private Button btlog;

	private EditText eregname;    //注册页面 手机号输入
	private EditText eregcheck;   //注册页面 验证码输入
	private EditText eregpass;    //注册页面  密码输入
	private Button btgetcheck;    //注册页面 获取验证码
	private Button btreg;         //注册页面  注册按钮

	private TextView tfindpass;
	
	private TextView titletext;
	
	private Mdialog mds;

//	private RequestListener mListener;
	
	private int t;	
	private Timer timer;
	private TimerTask task;
	final Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case 1:

				if (t > 0)
				{
					String tempstr = "" + t-- +"s后重发";
					btgetcheck.setText(tempstr);
				} else
				{

					timer.cancel();
					btgetcheck.setText("获取验证码");
					btgetcheck.setClickable(true);
				}

				
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
		setContentView(R.layout.demo);

		titletext = (TextView) findViewById(R.id.main_head_title);
		titletext.setText("登录");
		((TextView) findViewById(R.id.head_title_right))
				.setVisibility(View.GONE);
		
		findViewById(R.id.head_title_left).setVisibility(View.GONE);

		tabhost = (TabHost) findViewById(R.id.mytab);

		tabhost.setup();

		RelativeLayout articleTab = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.tab_top, null);
		TextView articleTabLabel = (TextView) articleTab
				.findViewById(R.id.tab_label);
		articleTabLabel.setText("账号登录");
		articleTabLabel.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
//				((TextView) findViewById(R.id.main_head_title)).setText("注册");
				titletext.setText("登录");
			}
		});

		RelativeLayout articleTab2 = (RelativeLayout) LayoutInflater.from(this)
				.inflate(R.layout.tab_top1, null);
		TextView articleTabLabe2 = (TextView) articleTab2
				.findViewById(R.id.tab_label);
		articleTabLabe2.setText("账号注册");
		articleTabLabe2.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
//				((TextView) findViewById(R.id.main_head_title)).setText("注册");
				titletext.setText("注册");
			}
		});
		

		tabhost.addTab(tabhost.newTabSpec("one").setIndicator(articleTab)
				.setContent(R.id.widget_layout_red));
		tabhost.addTab(tabhost.newTabSpec("two").setIndicator(articleTab2)
				.setContent(R.id.widget_layout_yellow));

		findids();

		setonclicklisteners();

	}

	private void findids()
	{
		// TODO Auto-generated method stub

		tfindpass = (TextView) findViewById(R.id.find_pass_text);

		elogname = (EditText) findViewById(R.id.log_user_name);
		elogpass = (EditText) findViewById(R.id.log_user_pass);
		btlog = (Button) findViewById(R.id.log_conform);

		eregname = (EditText) findViewById(R.id.reg_user_name);
		eregcheck = (EditText) findViewById(R.id.reg_user_check);
		eregpass = (EditText) findViewById(R.id.reg_user_pass);
		btgetcheck = (Button) findViewById(R.id.get_check);
		btreg = (Button) findViewById(R.id.reg_conform);

	}

	private void setonclicklisteners()
	{
		
		
		//获取验证码
		btgetcheck.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
				final String telno = eregname.getText().toString();

				if (StringUtils.isEmpty(telno))
				{
					UIHelper.ToastMessage(v.getContext(), "请输入手机号!");
					return;
				}
				
				

					t = 60;
					
					timer = new Timer();
					task = new TimerTask()
					{
						public void run()
						{
							
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);

						}
					};
					timer.schedule(task, 1000, 1000);
					
					btgetcheck.setClickable(false);

					getcheckfromsev();
				
				
			}
		});
		
		
		
		
		// regis  注册按钮
		btreg.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{				
				final String telno = eregname.getText().toString();
				String checkno = eregcheck.getText().toString();
				final String pwd = eregpass.getText().toString();
				Log.i("telno","" + telno);
				Log.i("pwd","" + pwd);
				
				// 判断输入
				if (StringUtils.isEmpty(telno))
				{
					UIHelper.ToastMessage(v.getContext(), "请输入手机号!");
					return;
				}
				
				if (StringUtils.isEmpty(checkno))
				{
					UIHelper.ToastMessage(v.getContext(), "请输入验证码！");
					return;
				}
				if (StringUtils.isEmpty(pwd))
				{
					UIHelper.ToastMessage(v.getContext(), "请输入密码!");
					return;
				}
				
				
				if (NetWorkUtil.isNetWork(Yhlogin.this))
				{

					mds = new Mdialog(Yhlogin.this, R.style.mFullHeightDialog);
					mds.getMtv().setText("正在注册，请稍候...");
					mds.show();
					
					HttpClientUtil httpClientUtil = new HttpClientUtil();
					httpClientUtil.setOnGetData(new OnGetResponseData()
					{

						@Override
						public void OnGetData(String result)
						{
							Log.i("regis", "result = " + result);

							if (result == null)
							{

								Toast.makeText(getApplicationContext(),
										R.string.server_connect_error, 0)
										.show();
							} else
							{

								try
								{
									JSONObject ob1 = new JSONObject(result);
									String returncode = ob1.getString("result");
									if (returncode.equalsIgnoreCase("1"))
									{

										String tokensstr = (new JSONObject(ob1
												.getString("data")))
												.getString("token");

										glApplication.mwzuser
												.setUtoken(tokensstr);
										
										mds.dismiss();
										
										getuserdata(tokensstr,"all",null);

									} else if (returncode.equalsIgnoreCase("0"))
									{
										String errorstr = ob1
												.getString("error");
										Toast.makeText(getApplicationContext(),
												errorstr, 0).show();
										mds.dismiss();
									} else if (returncode
											.equalsIgnoreCase("-1"))
									{
										String errorstr = ob1
												.getString("error");
										Toast.makeText(getApplicationContext(),
												errorstr, 0).show();
										mds.dismiss();
									} else if (returncode
											.equalsIgnoreCase("-3"))
									{
										String errorstr = ob1
												.getString("error");
										Toast.makeText(getApplicationContext(),
												errorstr, 0).show();
										mds.dismiss();
									}
									
									else if (returncode
											.equalsIgnoreCase("-2"))
									{
										String errorstr = ob1
												.getString("error");
										Toast.makeText(getApplicationContext(),
												errorstr, 0).show();
										mds.dismiss();
									}
									
									else if (returncode
											.equalsIgnoreCase("-4"))
									{
										String errorstr = ob1
												.getString("error");
										Toast.makeText(getApplicationContext(),
												errorstr, 0).show();
										mds.dismiss();
									}

								} catch (JSONException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
									mds.dismiss();
								}
							}

						}
					});				
					
					
										
					
					Map<String, String> datas = new HashMap<String, String>();
					datas.put("mobile", telno);
					datas.put("password", pwd);
					datas.put("verifycode", checkno);

					try
					{
						httpClientUtil.postRequest(YhdConstant.yhd_service_url
								+ YhdConstant.yhd_regis, datas);

						// httpClientUtil.postRequest(
						// YhdConstant.yhd_service_url+YhdConstant.yhd_login+"&mock=1",
						// datas);
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
		});

		// 寻找密码
		tfindpass.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(Yhlogin.this, YhFindpass.class);
				startActivity(intent);
			}
		});

		// login 登录按钮
		btlog.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				// Intent mintent = new Intent();
				// Intent intent = new Intent(Yhlogin.this, Ma.class);
				// startActivity(intent);
				
				

				final String account = elogname.getText().toString();
				final String pwd = elogpass.getText().toString();
				// 判断输入
				if (StringUtils.isEmpty(account))
				{
					UIHelper.ToastMessage(v.getContext(), "请输入手机号");
					return;
				}
				if (StringUtils.isEmpty(pwd))
				{
					UIHelper.ToastMessage(v.getContext(), "请输入密码");
					return;
				}

				if (NetWorkUtil.isNetWork(Yhlogin.this))
				{

					mds = new Mdialog(Yhlogin.this, R.style.mFullHeightDialog);
					mds.getMtv().setText("正在登陆，请稍候...");
					mds.show();
					
					HttpClientUtil httpClientUtil = new HttpClientUtil();
					httpClientUtil.setOnGetData(new OnGetResponseData()
					{

						@Override
						public void OnGetData(String result)
						{
							Log.i("login", "result = " + result);

							if (result == null)
							{

								Toast.makeText(getApplicationContext(),
										R.string.server_connect_error, 0)
										.show();
							} else
							{

								try
								{
									JSONObject ob1 = new JSONObject(result);
									String returncode = ob1.getString("result");
									if (returncode.equalsIgnoreCase("1"))
									{

										String tokensstr = (new JSONObject(ob1
												.getString("data")))
												.getString("token");

										glApplication.mwzuser
												.setUtoken(tokensstr);
										
										mds.dismiss();
										
										getuserdata(tokensstr,"all",null);
										
									

									} else if (returncode.equalsIgnoreCase("0"))
									{
										mds.dismiss();
										String errorstr = ob1
												.getString("error");
										Toast.makeText(getApplicationContext(),
												errorstr, 0).show();
									} else if (returncode
											.equalsIgnoreCase("-1"))
									{
										mds.dismiss();
										String errorstr = ob1
												.getString("error");
										Toast.makeText(getApplicationContext(),
												errorstr, 0).show();
									} else if (returncode
											.equalsIgnoreCase("-3"))
									{
										mds.dismiss();
										String errorstr = ob1
												.getString("error");
										Toast.makeText(getApplicationContext(),
												errorstr, 0).show();
									}

								} catch (JSONException e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
									mds.dismiss();
								}
							}

						}

						
					});
					Map<String, String> datas = new HashMap<String, String>();
					datas.put("mobile", account);
					datas.put("password", pwd);

					try
					{
						httpClientUtil.postRequest(YhdConstant.yhd_service_url
								+ YhdConstant.yhd_login, datas);

						// httpClientUtil.postRequest(
						// YhdConstant.yhd_service_url+YhdConstant.yhd_login+"&mock=1",
						// datas);
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
		});

	}

	protected void getcheckfromsev()
	{
		
		if (NetWorkUtil.isNetWork(Yhlogin.this))
		{

			HttpClientUtil httpClientUtil = new HttpClientUtil();
			httpClientUtil.setOnGetData(new OnGetResponseData()
			{

				@Override
				public void OnGetData(String result)
				{
					Log.i("获取验证码", "result = " + result);

					if (result == null)
					{

						Toast.makeText(getApplicationContext(),
								R.string.server_connect_error, 0)
								.show();
					} else
					{

						try
						{
							JSONObject ob1 = new JSONObject(result);
							String returncode = ob1.getString("result");
							if (returncode.equalsIgnoreCase("1"))
							{

								
							} else if (returncode
									.equalsIgnoreCase("-1"))
							{
								String errorstr = ob1
										.getString("error");
								Toast.makeText(getApplicationContext(),
										errorstr, 0).show();
							} else if (returncode
									.equalsIgnoreCase("-3"))
							{
								String errorstr = ob1
										.getString("error");
								Toast.makeText(getApplicationContext(),
										errorstr, 0).show();
							}else if (returncode
									.equalsIgnoreCase("-2"))
							{
								String errorstr = ob1
										.getString("error");
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
			});
			Map<String, String> datas = new HashMap<String, String>();
			datas.put("mobile", eregname.getText().toString());
			datas.put("smstpl", "register");

			try
			{
				httpClientUtil.postRequest(YhdConstant.yhd_service_url
						+ YhdConstant.yhd_sendsms, datas);

				// httpClientUtil.postRequest(
				// YhdConstant.yhd_service_url+YhdConstant.yhd_login+"&mock=1",
				// datas);
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

	/*
	参数
	tokensstr:必须
	action:  em|all   数据返回动作
	userid: 使用userid获取其他人的信息，若是获取自己的信息，userid null or ""
	*/
	private void getuserdata(String tokensstr, String action, String userid)
	{
		// TODO Auto-generated method stub
		if (NetWorkUtil.isNetWork(Yhlogin.this))
		{

			mds = new Mdialog(Yhlogin.this, R.style.mFullHeightDialog);
			mds.getMtv().setText("正在读取用户信息，请稍候...");
			mds.show();
			
			HttpClientUtil httpClientUtil = new HttpClientUtil();
			httpClientUtil.setOnGetData(new OnGetResponseData()
			{

				@Override
				public void OnGetData(String result)
				{
					Log.i("Yhlogin", "获取用户信息result = " + result);

					if (result == null)
					{

						Toast.makeText(getApplicationContext(),
								R.string.server_connect_error, 0)
								.show();
					} else
					{

						try
						{
							JSONObject ob1 = new JSONObject(result);
							String returncode = ob1.getString("result");
							if (returncode.equalsIgnoreCase("1"))
							{


//								glApplication.mwzuser
//										.setUtoken(tokensstr);
//								
//								mds.dismiss();
//								
//								getuserdata(tokensstr);
//								
//								Intent intent = new Intent(Yhlogin.this, Ma.class);
//								startActivity(intent);
								
//								MycpManager.setScrollnum(obj.has("scrollnum") ? obj
//										.getInt("scrollnum") : 3);
								
								JSONObject dataobj = new JSONObject(ob1.getString("data"));																
								glApplication.WzUser muser = glApplication.getMwzuser();	
								
								muser.setUserid(dataobj.getString("userid"));
								
								muser.setAvatar(dataobj.getString("avatar"));
								muser.setTruename(dataobj.getString("truename"));
								muser.setGroupid(dataobj.getInt("groupid"));
								muser.setAreaid(dataobj.getInt("areaid"));
								muser.setArea(dataobj.getString("area"));
								muser.setMobile(dataobj.getString("mobile"));
								muser.setCredit(dataobj.getInt("credit"));
								muser.setMoney(dataobj.getDouble("money"));
								muser.setLocking(dataobj.getDouble("locking"));
								muser.setCompany(dataobj.getString("company"));
								muser.setThumb(dataobj.getString("thumb"));
								muser.setTelephone(dataobj.getString("telephone"));
								muser.setCatid(dataobj.getString("catid"));
								muser.setCatname(dataobj.getString("catname"));
								muser.setBusiness(dataobj.getString("business"));
								muser.setAddress(dataobj.getString("address"));
								muser.setIntroduce(dataobj.getString("introduce"));
								muser.setFriendtotal(dataobj.getInt("friendtotal"));
								muser.setSelltotal(dataobj.getInt("selltotal"));
								muser.setBuytotal(dataobj.getInt("buytotal"));
								
//								dataobj.has("friend") ? dataobj
//										.getDouble("friend") : 0
										
										
								muser.setMalltotal(dataobj.has("malltotal") ? dataobj
										.getInt("malltotal") : 0);
								muser.setMall0total(dataobj.has("mall0total") ? dataobj
										.getInt("mall0total") : 0);
								
								muser.setMall1total(dataobj.has("mall1total") ? dataobj
										.getInt("mall1total") : 0);
								
								muser.setFriendtotal(dataobj.getInt("friendtotal"));
								muser.setStar1(dataobj.has("star1") ? dataobj
										.getDouble("star1") : 0);
								muser.setStar2(dataobj.has("star2") ? dataobj
										.getDouble("star2") : 0);
								
								//需要根据身份做判断
								muser.setFriend(dataobj.has("friend") ? dataobj
										.getBoolean("friend") : false);
								
								muser.setVmobile(dataobj.getInt("vmobile"));
								
								muser.setVtruename(dataobj.getString("vtruename"));
								muser.setVcompany(dataobj.getString("vcompany"));
								muser.setEmpass(dataobj.getString("empass"));
								
								
								mds.dismiss();
								Intent intent = new Intent(Yhlogin.this, Ma.class);
								startActivity(intent);

							} else
							{
								String errorstr = ob1
										.getString("error");
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

				
			});
			
			
			String tokensstr1 = glApplication.mwzuser
					.getUtoken();
			Map<String, String> datas = new HashMap<String, String>();
			datas.put("token", tokensstr1);
			datas.put("action", action);
			if(userid!=null &&!userid.equalsIgnoreCase("")){
				datas.put("userid", userid);
			}
			
			try
			{
				httpClientUtil.postRequest(YhdConstant.yhd_service_url
						+ YhdConstant.yhd_getuserinfo, datas);

				
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


}
