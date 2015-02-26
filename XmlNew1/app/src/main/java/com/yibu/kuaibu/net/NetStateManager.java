/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yibu.kuaibu.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetStateManager
{
	private static Context mContext;

	public static NetState CUR_NETSTATE = NetState.Mobile;

	public enum NetState
	{
		Mobile, WIFI, NOWAY
	}
	
	public NetStateManager(Context context)
	{
		mContext=context.getApplicationContext();
	}

	// ��ǰ�����Ƿ����
	public static boolean isOnline()
	{
		ConnectivityManager connMgr = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	public class NetStateReceive extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent)
		{
			mContext = context;
			if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent
					.getAction()))
			{
				WifiManager wifiManager = (WifiManager) context
						.getSystemService(Context.WIFI_SERVICE);
				WifiInfo info = wifiManager.getConnectionInfo();
				if (!wifiManager.isWifiEnabled() || -1 == info.getNetworkId())
				{
					CUR_NETSTATE = NetState.Mobile;
				}
			}
		}
	}

}
