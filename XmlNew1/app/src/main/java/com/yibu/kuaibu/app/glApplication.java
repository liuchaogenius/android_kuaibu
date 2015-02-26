/*****************************************************************************
 * VLCApplication.java
 *****************************************************************************
 * Copyright © 2010-2012 VLC authors and VideoLAN
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston MA 02110-1301, USA.
 *****************************************************************************/
package com.yibu.kuaibu.app;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.lidroid.xutils.BitmapUtils;

public class glApplication extends Application
{

	public static WzUser mwzuser;
	public String urlparams;
	private HttpClient httpClient;

    public static String HOST="http://yihaobu.hu8hu.com";


    public static WzUser getMwzuser()
	{
		return mwzuser;
	}

	public static void setMwzuser(WzUser mwzuser)
	{
		glApplication.mwzuser = mwzuser;
	}

	public static class WzUser
	{

		public String utoken;
		// 顺序是按 http://yihaobu.hu8hu.com/app/tool.php?file=getUser.php 返回的参数顺序写的
		public String userid;
		public String avatar;
		public String truename;
		public int groupid;
		public int areaid;
		public String area;
		public String mobile;
		public int credit;                 //int还是double？
		public double money;
		private double locking;
		private String company;

		private String thumb;
		private String telephone;
		private String catid;
		private String catname;
		private String business;
		private String address;
		private String introduce;

		private int friendtotal;
		private int selltotal;

		private int buytotal;
		private int malltotal;
		private int mall0total;
		private int mall1total;
		private double star1;
		private double star2;

		private boolean friend;
		private int vmobile;
		private String vtruename;
		private String vcompany;
		private String empass;

		public void clean()
		{

		}

		public String getUtoken()
		{
            if(utoken==null)
                return "token";
			return utoken;
		}

		public synchronized void setUtoken(String utoken)
		{
			this.utoken = utoken;
		}

		

		public synchronized String getUserid()
		{
			return userid;
		}

		public synchronized void setUserid(String userid)
		{
			this.userid = userid;
		}

		public String getAvatar()
		{
			return avatar;
		}

		public void setAvatar(String avatar)
		{
			this.avatar = avatar;
		}

		public String getTruename()
		{
			return truename;
		}

		public void setTruename(String truename)
		{
			this.truename = truename;
		}

		public int getGroupid()
		{
			return groupid;
		}

		public void setGroupid(int groupid)
		{
			this.groupid = groupid;
		}

		public int getAreaid()
		{
			return areaid;
		}

		public void setAreaid(int areaid)
		{
			this.areaid = areaid;
		}

		public String getArea()
		{
			return area;
		}

		public void setArea(String area)
		{
			this.area = area;
		}

		public String getMobile()
		{
			return mobile;
		}

		public void setMobile(String mobile)
		{
			this.mobile = mobile;
		}

		public int getCredit()
		{
			return credit;
		}

		public void setCredit(int credit)
		{
			this.credit = credit;
		}

		public double getMoney()
		{
			return money;
		}

		public void setMoney(double money)
		{
			this.money = money;
		}

		public double getLocking()
		{
			return locking;
		}

		public void setLocking(double locking)
		{
			this.locking = locking;
		}

		public String getCompany()
		{
			return company;
		}

		public void setCompany(String company)
		{
			this.company = company;
		}

		public String getThumb()
		{
			return thumb;
		}

		public void setThumb(String thumb)
		{
			this.thumb = thumb;
		}

		public String getTelephone()
		{
			return telephone;
		}

		public void setTelephone(String telephone)
		{
			this.telephone = telephone;
		}

		public String getCatid()
		{
			return catid;
		}

		public void setCatid(String catid)
		{
			this.catid = catid;
		}

		public String getCatname()
		{
			return catname;
		}

		public void setCatname(String catname)
		{
			this.catname = catname;
		}

		public String getBusiness()
		{
			return business;
		}

		public void setBusiness(String business)
		{
			this.business = business;
		}

		public String getAddress()
		{
			return address;
		}

		public void setAddress(String address)
		{
			this.address = address;
		}

		public String getIntroduce()
		{
			return introduce;
		}

		public void setIntroduce(String introduce)
		{
			this.introduce = introduce;
		}

		public int getFriendtotal()
		{
			return friendtotal;
		}

		public void setFriendtotal(int friendtotal)
		{
			this.friendtotal = friendtotal;
		}

		public int getSelltotal()
		{
			return selltotal;
		}

		public void setSelltotal(int selltotal)
		{
			this.selltotal = selltotal;
		}

		public int getBuytotal()
		{
			return buytotal;
		}

		public void setBuytotal(int buytotal)
		{
			this.buytotal = buytotal;
		}

		public int getMalltotal()
		{
			return malltotal;
		}

		public void setMalltotal(int malltotal)
		{
			this.malltotal = malltotal;
		}

		public int getMall0total()
		{
			return mall0total;
		}

		public void setMall0total(int mall0total)
		{
			this.mall0total = mall0total;
		}

		public int getMall1total()
		{
			return mall1total;
		}

		public void setMall1total(int mall1total)
		{
			this.mall1total = mall1total;
		}

		public double getStar1()
		{
			return star1;
		}

		public void setStar1(double star1)
		{
			this.star1 = star1;
		}

		public double getStar2()
		{
			return star2;
		}

		public void setStar2(double star2)
		{
			this.star2 = star2;
		}

		public boolean isFriend()
		{
			return friend;
		}

		public void setFriend(boolean friend)
		{
			this.friend = friend;
		}

		public int getVmobile()
		{
			return vmobile;
		}

		public void setVmobile(int vmobile)
		{
			this.vmobile = vmobile;
		}

		

		

		public String getVtruename()
		{
			return vtruename;
		}

		public void setVtruename(String vtruename)
		{
			this.vtruename = vtruename;
		}

		

		public String getVcompany()
		{
			return vcompany;
		}

		public void setVcompany(String vcompany)
		{
			this.vcompany = vcompany;
		}

		public String getEmpass()
		{
			return empass;
		}

		public void setEmpass(String empass)
		{
			this.empass = empass;
		}

		
		

	};

	private static glApplication instance;

	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
		httpClient = this.createHttpClient();
	}

	private HttpClient createHttpClient()
	{
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params,
				HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpConnectionParams.setConnectionTimeout(params, 20 * 1000);
		HttpConnectionParams.setSoTimeout(params, 20 * 1000);
		HttpConnectionParams.setSocketBufferSize(params, 8192);
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schReg.register(new Scheme("https",
				SSLSocketFactory.getSocketFactory(), 443));

		ClientConnectionManager connMgr = new ThreadSafeClientConnManager(
				params, schReg);

		return new DefaultHttpClient(connMgr, params);
	}

	private void shutdownHttpClient()
	{
		if (httpClient != null && httpClient.getConnectionManager() != null)
		{
			httpClient.getConnectionManager().shutdown();
		}
	}

	// 把图片转换成字节
	public byte[] img(Bitmap mBitmap)
	{
		if (mBitmap != null)
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			return baos.toByteArray();
		} else
		{
			return null;
		}
	}

	public Bitmap getBmp(byte[] in)
	{
		if (in == null)
		{
			return null;
		}
		Bitmap bmpout = BitmapFactory.decodeByteArray(in, 0, in.length);
		return bmpout;
	}

	/**
	 * 返回当前程序版本名
	 */
	public static String getAppVersionName(Context context)
	{
		String versionName = "";
		try
		{
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0)
			{
				return "";
			}
		} catch (Exception e)
		{
			Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}

	// 当前网络是否可用
	public static boolean isOnline()
	{
		ConnectivityManager connMgr = (ConnectivityManager) instance
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	/**
	 * Called when the overall system is running low on memory
	 */
	@Override
	public void onLowMemory()
	{
		super.onLowMemory();

		// BitmapCache.getInstance().clear();
	}

	/**
	 * @return the main context of the Application
	 */
	public static glApplication getAppContext()
	{
		return instance;
	}

	/**
	 * @return the main resources from the Application
	 */
	public static Resources getAppResources()
	{
		if (instance == null)
			return null;
		return instance.getResources();
	}

	public HttpClient getHttpClient()
	{
		return httpClient;
	}

	public static void saveMyBitmap(Bitmap mBitmap, String bitName)
	{
		File f = new File(bitName);
		Log.i("application", "" + bitName);
		FileOutputStream fOut = null;
		try
		{
			fOut = new FileOutputStream(f);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
		try
		{
			fOut.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		try
		{
			fOut.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
