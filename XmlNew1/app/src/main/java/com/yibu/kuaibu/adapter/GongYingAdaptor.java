package com.yibu.kuaibu.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.app.glApplication;
import com.yibu.kuaibu.net.helper.NetImageHelper;
import com.yibu.kuaibu.net.model.gongying.RsDo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shanksYao on 2/24/15.
 */
public class GongYingAdaptor extends BaseAdapter {


    private List<RsDo> list=new ArrayList<RsDo>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RsDo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(RsDo[] rsDos){
        list.addAll(Arrays.asList(rsDos) );
        notifyDataSetChanged();
    }

    public void add(RsDo rsDo){
        list.add(rsDo);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(glApplication.getAppContext()).inflate(R.layout.item_gong_ying,parent,false);
            ViewHolder viewHolder=new ViewHolder();
            viewHolder.pic= (NetworkImageView) convertView.findViewById(R.id.pic);
            viewHolder.title= (TextView) convertView.findViewById(R.id.title);
            viewHolder.fenlei= (TextView) convertView.findViewById(R.id.fenlei_text);
            viewHolder.vip= (TextView) convertView.findViewById(R.id.title_label);
            viewHolder.state= (TextView) convertView.findViewById(R.id.state);
            viewHolder.date= (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(viewHolder);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        ViewHolder viewHolder1= (ViewHolder) v.getTag();
                         ///goto detail TODO
                        String id=viewHolder1.itemId;
                }
            });
        }
        RsDo rsDo=list.get(position);
        ViewHolder viewHolder= (ViewHolder) convertView.getTag();

        NetImageHelper.setImageUrl(viewHolder.pic,rsDo.thumb,R.drawable.error,R.drawable.error);
        viewHolder.title.setText(rsDo.title);
        viewHolder.fenlei.setText(rsDo.catname);
        if(rsDo.vip==1)
        viewHolder.vip.setVisibility(View.VISIBLE);
        else
        viewHolder.vip.setVisibility(View.GONE);

        viewHolder.state.setText("状态："+rsDo.typename);
        viewHolder.date.setText(rsDo.editdate);

        viewHolder.itemId=rsDo.itemid;
        return convertView;
    }
    class ViewHolder{
        NetworkImageView pic;
        TextView title;
        TextView fenlei;
        TextView vip;
        TextView state;
        TextView date;
        String itemId;
    }
}
