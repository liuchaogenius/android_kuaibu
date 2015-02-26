package com.yibu.kuaibu.net.model.leimu;

import com.lidroid.xutils.http.RequestParams;
import com.yibu.kuaibu.net.helper.XHttpRequest;

/**
 * Created by shanksYao on 2/21/15.
 */
public class LeiMuRequest extends XHttpRequest{
    @Override
    public String getUrl() {
        return "http://yihaobu.hu8hu.com/app/getCategory.php?";
    }

    @Override
    public RequestParams getParams() {
        RequestParams params= new RequestParams();
        addToken(params);
        return params;
    }
}
