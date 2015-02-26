package com.yibu.kuaibu.bean;

import android.content.Context;
import android.widget.Toast;

public class CustomException extends Exception
{
	public final static byte TYPE_CUSTOM_ERROR = 0x25;

	private byte type;
	private int code;
	private String messge = "";

	private CustomException(byte type, int code, String messge)
	{
		this.type = type;
		this.code = code;
		this.messge = messge;
	}

	public int getCode()
	{
		return this.code;
	}

	public int getType()
	{
		return this.type;
	}

	public String getMessge()
	{
		return this.messge;
	}

	public void makeToast(Context ctx)
	{
		switch (this.getType())
		{
		case TYPE_CUSTOM_ERROR:
			Toast.makeText(ctx, this.messge, Toast.LENGTH_SHORT).show();
		}
	}

	public static CustomException json(int code, String messge)
	{
		return new CustomException(TYPE_CUSTOM_ERROR, code, messge);
	}

}
