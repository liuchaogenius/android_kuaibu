package com.yibu.kuaibu.utils;

import java.io.File;

import android.os.Environment;


public class YhdConstant {

	
	public final static String DIR = Environment.getExternalStorageDirectory()
			+ "/YihaoBu/";
	public static final String IMGDIR = DIR+"pic"+File.separator;
	
	public static final String yhd_pic_rul = "http://yihaobu.hu8hu.com";    //首页 产品推荐 及 商城首页 促销产品，布局解析获得的服务器url，所缺少之前缀
	
	public static final String yhd_service_url = "http://yihaobu.hu8hu.com/app/";
	public static final String yhd_mock = "mock=1&";
	public static final String yhd_login = "login.php?";	
	public static final String yhd_regis = "register.php?";
	public static final String yhd_sendsms = "sendSms.php?";   // 发送短信
	public static final String yhd_findpass = "findPassword.php";   //找回密码
	public static final String yhd_getuserinfo = "getUser.php?";         //获取用户信息
	
	
	public static final String yhd_get_shouye="getIndex.php?";
	public static final String yhd_get_shangcheng = "getCompanyIndex.php?";
	public static final String yhd_get_address  = "getAddressList.php?";
	public static final String yhd_get_chanpin = "getMallDetail.php?";      //产品详情
	public static final String yhd_get_orderlist = "getOrderList.php?";   //订单列表
	
	
	public static String yhd_get_gongying = "getSellList.php?";            //供应列表n
	
}
