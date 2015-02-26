package com.yibu.kuaibu.net.helper;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yibu.kuaibu.utils.GsonUtils;

/**
 * Created by shanksYao on 2/20/15.
 */
public class HttpHelper {

    public static void request(XHttpRequest request, final Class dataClazz,final Callback callback){
       HttpUtils http = new HttpUtils();
       http.send(HttpRequest.HttpMethod.POST,request.getUrl(),request.getParams(),new RequestCallBack<String>() {
           @Override
           public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                   XResponse xResponse=GsonUtils.jsonToBean(objectResponseInfo.result,XResponse.class);

                   if(xResponse.result==1){
                        Gson gson=new Gson();
                        String data= gson.toJson(xResponse.data);
                        callback.onSuccess(GsonUtils.jsonToBean(data, dataClazz));

                   }else{
                        callback.onFailure(xResponse.result,xResponse.error);
                   }
           }

           @Override
           public void onFailure(HttpException e, String s) {
                        callback.onFailure(-10,s);
           }
       });
    }

  public interface Callback<T>{
      void onSuccess(T t);
      void onFailure(int errorCode,String msg);
  }

}
