package com.yibu.kuaibu.app;

import android.view.View.OnClickListener;

public class ImageAndText {
    private String imageUrl;
    private String titletext;
    private String pricetext;
    private String itemid;
    private OnClickListener mOnclick;
    

    public ImageAndText(String ids,String imageUrl, String text, String text2, OnClickListener abenClick) {
    	this.itemid = ids;
        this.imageUrl = imageUrl;
        this.titletext = text;
        this.pricetext = text2;
        this.mOnclick = abenClick;
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
    
	
}