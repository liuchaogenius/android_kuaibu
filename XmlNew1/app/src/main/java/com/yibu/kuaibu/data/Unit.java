package com.yibu.kuaibu.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Unit
{

	private static Context mContext;
	private static ExecutorService threadPool = null;
	
	
	
	

	public static int regisurl;
	public static int serverurl;
	
	
	

	// 请求的状态
	public final static int LISTVIEW_ACTION_INIT = 0x01;
	public final static int LISTVIEW_ACTION_REFRESH = 0x02;
	public final static int LISTVIEW_ACTION_SCROLL = 0x03;
	public final static int LISTVIEW_ACTION_CHANGE_CATALOG = 0x04;

	public final static int LISTVIEW_DATATYPE_POST = 0x01;

	public Unit(Context context)
	{
		mContext = context.getApplicationContext();
		threadPool = Executors.newCachedThreadPool();
	}

	public static synchronized ExecutorService getThreadPoolInstance()
	{

		if (threadPool == null)
		{
			

			threadPool = Executors.newCachedThreadPool();
		}
		return threadPool;
	}

	public static void destroy()
	{
		if (threadPool != null)
		{
			if (!threadPool.isShutdown())
			{
				threadPool.shutdown();
			}

			threadPool = null;
		}
	}

}
