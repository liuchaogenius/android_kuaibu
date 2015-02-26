package com.yibu.kuaibu.net.helper;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yibu.kuaibu.app.glApplication;

/**
 * Created by shanksYao on 2/20/15.
 */
public abstract class XHttpRequest {

    protected RequestParams params;

    public abstract String getUrl();

    public abstract RequestParams getParams();

    public HttpRequest.HttpMethod getHttpMethod(){
        return HttpRequest.HttpMethod.POST;
    }

    protected void  addToken(RequestParams params){
        params.addBodyParameter("token", glApplication.mwzuser.getUtoken());
        //TODO  正式发布的时候去掉mock
       // params.addBodyParameter("mock", "1");
    }
}
