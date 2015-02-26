package com.yibu.kuaibu.utils;

import java.io.File;

import android.os.Environment;

/**
 * ���÷����� ����: Contact</br> ����cn.mama.baby.util</br> ����: </br> �����汾�ţ� </br>
 * ������Ա:�ν�ϲ</br> ����ʱ�䣺 2013-4-9
 * 
 * @author HeJx
 */

public class CopyOfYhdConstant {

	/**
	 * ����Ŀ¼
	 */
	public final static String DIR = Environment.getExternalStorageDirectory()
			+ "/YihaoBu/";
	public static final String IMGDIR = DIR+"pic"+File.separator;
	public final static String WELCOME_PIC = DIR+"welcome_pic.jpg";
	
	public final static String SOURCE = "1";  //�ͻ���4Դ��1=android, 2=iphone
	
	public final static int USER = 1; //SharedPreferences �û����ļ�
	public final static int CACHE = 2;//
	public final static int SYSTEM = 3;//
	
	public final static int BABYDETAIL = 1;  
	public final static int BABYDFIRSTETAIL = 2;
	public final static int BABYDOTHERETAIL = 3;
	
}
