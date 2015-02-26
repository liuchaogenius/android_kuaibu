/**
 * 
 */
package com.yibu.kuaibu.net;

import java.io.File;
import java.util.Map;

import com.google.gson.JsonObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;

/**
 * @author liujian
 * 
 */
public class HttpClientUtil
{
	private OnGetResponseData onGetResponseData;

	public void setOnGetData(OnGetResponseData onGetResponseData)
	{
		this.onGetResponseData = onGetResponseData;
	}

	public void postRequest(String url, Map<String, String> datas)
			throws Exception
	{
		RequestParams params = null;
		params = new RequestParams();
		if(datas!=null){
			for (String key : datas.keySet())
			{
				params.addBodyParameter(key, datas.get(key));
			}
		}
		
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>()
		{

			@Override
			public void onStart()
			{
				LogUtils.d("conn...");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading)
			{
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				onGetResponseData.OnGetData(responseInfo.result);
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
				onGetResponseData.OnGetData(null);
			}
		});

	}

	/**
	 * 表单提交
	 * 
	 * @param url
	 *            路径
	 * @param datas
	 *            参数数据
	 * @param files
	 *            文件数据
	 * @return result 返回数据
	 * @throws Exception
	 */
	public void postRequestByXUtils(String url, Map<String, String> datas,
			Map<String, String> files) throws Exception
	{
		// final String result = null;
		RequestParams params = null;
		if (datas == null || files == null)
		{

		} else
		{
			params = new RequestParams();
			for (String key : datas.keySet())
			{
				params.addBodyParameter(key, datas.get(key));
			}
			for (String key : files.keySet())
			{
				if ("".equals(files.get(key)) || files.get(key) == null)
					continue;
				params.addBodyParameter(key, new File(files.get(key)));
			}
		}

		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>()
		{

			@Override
			public void onStart()
			{
				LogUtils.d("conn...");
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading)
			{
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				onGetResponseData.OnGetData(responseInfo.result);
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
			}
		});

	}

	public interface OnGetResponseData
	{
		public void OnGetData(String result);
	}
}
