package com.yibu.kuaibu.adapterly.shangjituijian;

import android.view.View.OnClickListener;

public class ShangJiTuiJianImageAndText {
    private String imageUrl;  //图片地址
    private String titletext; //商品名称
    private String pricetext; //发布时间
    private String itemid;
    private String liulantext;  //浏览数
    private OnClickListener mOnclick;
    

    public ShangJiTuiJianImageAndText(String ids,String imageUrl, String text, String text2, String string3, OnClickListener abenClick) {
    	this.itemid = ids;
        this.imageUrl = imageUrl;
        this.titletext = text;
        this.pricetext = text2;
        this.mOnclick = abenClick;
        this.liulantext=string3;
    }
    public String getImageUrl() {
        return imageUrl;
    }
	public String getTitletext()
	{
		return titletext;
	}
	public String getPricetext()
	{
		return pricetext;
	}
	public String getItemid()
	{
		return itemid;
	}
	public OnClickListener getmOnclick()
	{
		return mOnclick;
	}
	public String getLiulantext()
	{
		return liulantext;
	}
	public void setLiulantext(String liulantext)
	{
		this.liulantext = liulantext;
	}
    
	
}