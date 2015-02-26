/**
 * 
 */
package com.yibu.kuaibu.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetWorkUtil {

	/**
	 * 判断是否有网络，不返回网络类型 false 不可用
	 * @param context
	 * @return
	 */
	public static boolean isNetWork(Context context) {

		// �õ�ϵͳ�������������
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();  // ��ü����������Ϣ
		if (info != null) { 
			boolean isavailable = info.isAvailable(); // ��ǰ�����Ƿ���Ч
			boolean isconnected = info.isConnected(); // ��ǰ�����Ƿ�������
			if (isavailable && isconnected) {	
				//�����ǿ��õ�
				return true;
			}else{
				//���粻����
				return false;
			}
		}
		return false;
	}
	
	/**
	 * �ж��Ƿ������磬���������������͡�null:������   WIFI��MOBILE:������
	 * @param context
	 * @return
	 */
	public static String getNetWorkType(Context context) {

		// �õ�ϵͳ�������������
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();  // ��ü����������Ϣ
		if (info != null) { 
			boolean isavailable = info.isAvailable(); // ��ǰ�����Ƿ���Ч
			boolean isconnected = info.isConnected(); // ��ǰ�����Ƿ�������
			if (isavailable && isconnected) {	
				//�����ǿ��õ�
				String netType = info.getTypeName();  // ��ǰ��������� for example "WIFI" or "MOBILE".
				return netType;
			}else{
				//���粻����
				return null;
			}
		}
		return null;
	}

	/**
	 * �ж��Ƿ�Ϊ��������
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isMOBBLEConnectivity(Context context) {
		// �õ�ϵͳ�����������
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		// WIFI����������Ϣ
		NetworkInfo networkinfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkinfo != null) {
			return networkinfo.isConnected();
		}
		return false;
	}

	/**
	 * �ж��Ƿ�Ϊ��������
	 * 
	 * @param context
	 * @return
	 */
	private static boolean isWIFIConnectivity(Context context) {
		// �õ�ϵͳ�����������
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		// MOBBLE����������Ϣ
		NetworkInfo networkinfo = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkinfo != null) {
			return networkinfo.isConnected();
		}
		return false;
	}

}
