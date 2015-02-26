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
import com.yibu.kuaibu.ui.UIHelper;
import com.yibu.kuaibu.utils.NetWorkUtil;
import com.yibu.kuaibu.utils.StringUtils;
import com.yibu.kuaibu.utils.YhdConstant;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class YhFindpass extends Activity
{

	

	private EditText eregname;
	private EditText eregcheck;
	private EditText eregpass;
	private EditText eregpasscheck;
	private Button btgetcheck;
	private Button btreg;

	private TextView tfindpass;
	
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
		setContentView(R.layout.yhfindpass);

		((TextView) findViewById(R.id.main_head_title)).setText("找回密码");
		((TextView) findViewById(R.id.head_title_right))
				.setVisibility(View.GONE);

		findViewById(R.id.head_title_left).setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		findids();
		
		setonclicklisteners();

	}

	
	private void findids()
	{
		// TODO Auto-generated method stub

		tfindpass = (TextView) findViewById(R.id.find_pass_text);
				
		eregname = (EditText) findViewById(R.id.reg_user_name);
		eregcheck = (EditText) findViewById(R.id.reg_user_check);
		eregpass = (EditText) findViewById(R.id.reg_user_pass);
		eregpasscheck = (EditText) findViewById(R.id.reg_user_pass_conf);
		
		btgetcheck = (Button) findViewById(R.id.get_check);
		btreg = (Button) findViewById(R.id.reg_conform);

	}
	
	private void setonclicklisteners()
	{
		// TODO Auto-generated method stub
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
						String pwdconf = eregpasscheck.getText().toString();
						
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
						
						if (StringUtils.isEmpty(pwdconf))
						{
							UIHelper.ToastMessage(v.getContext(), "请输入确认密码!");
							return;
						}
						
						if (! pwd.equalsIgnoreCase(pwdconf))
						{
							UIHelper.ToastMessage(v.getContext(), "密码输入前后不一致，请检查!");
							return;
						}
						
						
						if (NetWorkUtil.isNetWork(YhFindpass.this))
						{

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
												
												
												
												
												

											} else if (returncode.equalsIgnoreCase("0"))
											{
												String errorstr = ob1
														.getString("error");
												Toast.makeText(getApplicationContext(),
														errorstr, 0).show();
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
											}
											
											else if (returncode
													.equalsIgnoreCase("-2"))
											{
												String errorstr = ob1
														.getString("error");
												Toast.makeText(getApplicationContext(),
														errorstr, 0).show();
											}
											
											else if (returncode
													.equalsIgnoreCase("-4"))
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
							datas.put("mobile", telno);
							datas.put("password", pwd);
							datas.put("verifycode", checkno);

							try
							{
								httpClientUtil.postRequest(YhdConstant.yhd_service_url
										+ YhdConstant.yhd_findpass, datas);

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
		
		if (NetWorkUtil.isNetWork(YhFindpass.this))
		{

			HttpClientUtil httpClientUtil = new HttpClientUtil();
			httpClientUtil.setOnGetData(new OnGetResponseData()
			{

				@Override
				public void OnGetData(String result)
				{
					Log.i("找回密码", "result = " + result);

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
			datas.put("smstpl", "findpassword");

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

	


}
