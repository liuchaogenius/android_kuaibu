package com.yibu.kuaibu.adapterly;

import android.view.View.OnClickListener;

public class Cpxiangqingpopgridlist {
    private String iconurl;   //头像
    private String username;  //username
    private String contenttext;  //content
    private String itemid;
    private String pltime;       //评论时间
    private String guigefenlei;    //规格分类
    private OnClickListener mOnclick;
    

    public Cpxiangqingpopgridlist(String ids,String iconurl, String musername, String mcontenttext,String mpltime, String mguigefenlei,OnClickListener abenClick) {
    	this.itemid = ids;
        this.iconurl = iconurl;
        this.username = musername;
        this.contenttext = mcontenttext;
        this.mOnclick = abenClick;
        this.pltime=mpltime;
        this.guigefenlei = mguigefenlei;
    }
    public String getImageUrl() {
        return iconurl;
    }
	public String getTitletext()
	{
		return username;
	}
	public String getPricetext()
	{
		return contenttext;
	}
	public String getItemid()
	{
		return itemid;
	}
	public OnClickListener getmOnclick()
	{
		return mOnclick;
	}
	public String getGuigefenlei()
	{
		return guigefenlei;
	}
	public String getPltime()
	{
		return pltime;
	}
	
	
}