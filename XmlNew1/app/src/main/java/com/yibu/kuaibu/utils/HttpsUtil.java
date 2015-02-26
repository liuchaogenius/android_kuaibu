package com.yibu.kuaibu.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.util.Log;

public class HttpsUtil {
//	static TrustManager[] xtmArray = new MytmArray[] { new MytmArray() };
	private final static int CONNENT_TIMEOUT = 15000;
	private final static int READ_TIMEOUT = 15000;
	private static String mCookie;
	public static int httpsResponseCode;

	static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};

//	private static void trustAllHosts() {
//		try {
//			SSLContext sc = SSLContext.getInstance("TLS");
//			sc.init(null, xtmArray, new java.security.SecureRandom());
//			HttpsURLConnection
//					.setDefaultSSLSocketFactory(sc.getSocketFactory());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@SuppressLint("DefaultLocale")
	public static String HttpsPost(String httpsurl, String data) {
		String result = null;
		HttpURLConnection http = null;
		URL url;
		try {
			url = new URL(httpsurl);

				http = (HttpURLConnection) url.openConnection();
			

			http.setConnectTimeout(CONNENT_TIMEOUT);
			http.setReadTimeout(READ_TIMEOUT);
			if (data == null) {
				http.setRequestMethod("GET");
				http.setDoInput(true);
				if (mCookie != null)
					http.setRequestProperty("Cookie", mCookie);
			} else {
				http.setRequestMethod("POST");
				http.setDoInput(true);
				http.setDoOutput(true);
				if (mCookie != null && mCookie.trim().length() > 0)
					http.setRequestProperty("Cookie", mCookie);

				DataOutputStream out = new DataOutputStream(
						http.getOutputStream());
				out.writeBytes(data);
				out.flush();
				out.close();
			}

			httpsResponseCode = http.getResponseCode();
			BufferedReader in = null;
			if (httpsResponseCode == 200) {
				
				in = new BufferedReader(new InputStreamReader(
						http.getInputStream(), "UTF-8"));
			} else
				in = new BufferedReader(new InputStreamReader(
						http.getErrorStream()));
			String temp = in.readLine();
			while (temp != null) {
				if (result != null)
					result += temp;
				else
					result = temp;
				temp = in.readLine();
			}
			in.close();
			http.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	public static String sendPostRequest(String path,   
	        Map<String, String> params, String enc) throws Exception{  
		
		String result = null;
//		HttpURLConnection http = null;
		URL url;
	      
	    // title=dsfdsf&timelength=23&method=save  
	    StringBuilder sb = new StringBuilder();  
	    if(params!=null && !params.isEmpty()){  
	        //迭代Map拼接请求参数  
	        for(Map.Entry<String, String> entry : params.entrySet()){  
	            sb.append(entry.getKey()).append('=')  
	                .append(URLEncoder.encode(entry.getValue(), enc)).append('&');  
	        }  
	        sb.deleteCharAt(sb.length()-1);//删除最后一个"&"  
	    }  
	    byte[] entitydata = sb.toString().getBytes();//得到实体的二进制数据  
	    url = new URL(path);  
	    HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	    conn.setRequestMethod("POST");  
	    conn.setConnectTimeout(5 * 1000);  
	    //如果通过post提交数据，必须设置允许对外输出数据  
	    conn.setDoOutput(true);  
	    //此两参数必须设置  
	    //Content-Type: application/x-www-form-urlencoded  
	    //Content-Length: 38  
	    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
	    conn.setRequestProperty("Content-Length", String.valueOf(entitydata.length));  
	    OutputStream outStream = conn.getOutputStream();  
	    outStream.write(entitydata);  
	    outStream.flush();  
	    outStream.close();  
	    if(conn.getResponseCode()==200){  
	    	
	        InputStream is = conn.getInputStream();
	          int ch;
	          StringBuffer b =new StringBuffer();
	          while( ( ch = is.read() ) !=-1 )
	          {
	            b.append( (char)ch );
	          }
	          /* 将Response显示于Dialog */
//	          showDialog("上传成功"+b.toString().trim());
	          
	          result = b.toString().trim();
		        return result;  
	    }  
	    return result;  
	}



	public static String sendPostRequestnew(String string,
			List<NameValuePair> mparams2, String string2)
	{
		// TODO Auto-generated method stub
		String result = null;
		HttpResponse httpResponse = null;
		
		HttpPost httpPost = new HttpPost(string);
		Log.i("HttpsUtil url", ""+string);
		
		try{
			httpPost.setEntity(new UrlEncodedFormEntity(mparams2, HTTP.UTF_8));
	
			httpResponse = new DefaultHttpClient().execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(httpResponse
						.getEntity());
				Log.i("HttpsUtil result", " "+result);
				
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}  


}