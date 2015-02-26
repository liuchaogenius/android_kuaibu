package com.yibu.kuaibu.app.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapter.GongYingAdaptor;
import com.yibu.kuaibu.app.activity.GongYingActivity;
import com.yibu.kuaibu.net.helper.HttpHelper;
import com.yibu.kuaibu.net.helper.XHttpRequest;
import com.yibu.kuaibu.net.model.gongying.CaiGouRequest;
import com.yibu.kuaibu.net.model.gongying.GongYingDo;
import com.yibu.kuaibu.net.model.gongying.GongYingRequest;

/**
 * Created by shanksYao on 2/24/15.
 */
public class GongYingFragment extends Fragment {


    private int pageNo=1;
    private boolean hasMore=true;
    private ListView listView;
    private GongYingAdaptor adaptor;
    private boolean  isLoading=false;

    private int typeId;

    private int requestType;
    private static final int CAI_GOU=1;
    private int vip;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gongying,container,false);
        listView= (ListView) view.findViewById(R.id.listView);
        adaptor=new GongYingAdaptor();
        listView.setAdapter(adaptor);

        typeId=getArguments().getInt("typeid");
        requestType=getArguments().getInt("requestType",-1);
        vip=getArguments().getInt("vip",0);
        requestData();

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                       if(!isLoading&&view.getLastVisiblePosition()>adaptor.getCount()+4){
                            requestData();
                       }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(!isLoading&&view.getLastVisiblePosition()>adaptor.getCount()+4){
                    requestData();
                }
            }
        });
        return view;
    }

    public static GongYingFragment getFragment(int typeid){
        GongYingFragment gongYingFragment=new GongYingFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("typeid",typeid);
        gongYingFragment.setArguments(bundle);
        return gongYingFragment;
    }

    public static GongYingFragment getCaiGouFragment(int vip){
        GongYingFragment gongYingFragment=new GongYingFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("vip",vip);
        bundle.putInt("requestType",CAI_GOU);
        gongYingFragment.setArguments(bundle);
        return gongYingFragment;
    }

    private void requestData() {


        if(hasMore){
            XHttpRequest xHttpRequest;
            if(requestType==CAI_GOU){
                xHttpRequest=new CaiGouRequest(pageNo,vip);
            }else{
                xHttpRequest=new GongYingRequest(pageNo, typeId);
            }
            HttpHelper.request(xHttpRequest, GongYingDo.class, new HttpHelper.Callback<GongYingDo>() {
                @Override
                public void onSuccess(GongYingDo gongYingDo) {
                    pageNo = gongYingDo.page.pageid;
                    if (pageNo >= gongYingDo.page.pagetotal) {
                        hasMore = false;
                    }
                    adaptor.addAll(gongYingDo.rslist);
                    isLoading=false;
                }

                @Override
                public void onFailure(int errorCode, String msg) {
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
                    isLoading=false;
                }
            });
        }

    }
}
