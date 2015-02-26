package com.yibu.kuaibu.adapterly;

import android.view.View.OnClickListener;

public class Orderlist {
//    private String iconurl;   //头像
//    private String username;  //username
//    private String contenttext;  //content
//    private String itemid;
//    private String pltime;       //评论时间
//    private String guigefenlei;    //规格分类
//    private OnClickListener mOnclick;
    
	
		private String mitemid;
		private String morderid;
		private String msellid;
		private String mseller;
		private String msellcom;
		private String mtitle;
		private String mthumb;
		private String mprice;
		private String mnumber;
		private String mfee;
		private String mfee_name;
		private String mamount;
		private String maddtime;
		private int mstatus;
		private String mdstatus;
		private OnClickListener mOnclick;
		
		public Orderlist(String mitemid, String morderid, String msellid,
				String mseller, String msellcom, String mtitle, String mthumb,
				String mprice, String mnumber, String mfee, String mfee_name,
				String mamount, String maddtime, int mstatus, String mdstatus,OnClickListener abenClick)
		{
			super();
			this.mitemid = mitemid;
			this.morderid = morderid;
			this.msellid = msellid;
			this.mseller = mseller;
			this.msellcom = msellcom;
			this.mtitle = mtitle;
			this.mthumb = mthumb;
			this.mprice = mprice;
			this.mnumber = mnumber;
			this.mfee = mfee;
			this.mfee_name = mfee_name;
			this.mamount = mamount;
			this.maddtime = maddtime;
			this.mstatus = mstatus;
			this.mdstatus = mdstatus;
			this.mOnclick=abenClick;
		}
		public String getMitemid()
		{
			return mitemid;
		}
		public String getMorderid()
		{
			return morderid;
		}
		public String getMsellid()
		{
			return msellid;
		}
		public String getMseller()
		{
			return mseller;
		}
		public String getMsellcom()
		{
			return msellcom;
		}
		public String getMtitle()
		{
			return mtitle;
		}
		public String getMthumb()
		{
			return mthumb;
		}
		public String getMprice()
		{
			return mprice;
		}
		public String getMnumber()
		{
			return mnumber;
		}
		public String getMfee()
		{
			return mfee;
		}
		public String getMfee_name()
		{
			return mfee_name;
		}
		public String getMamount()
		{
			return mamount;
		}
		public String getMaddtime()
		{
			return maddtime;
		}
		public int getMstatus()
		{
			return mstatus;
		}
		public String getMdstatus()
		{
			return mdstatus;
		}
		public OnClickListener getmOnclick()
		{
			return mOnclick;
		}
		
		
		
		
		
		
		
		

//    public Orderlist(String ids,String iconurl, String musername, String mcontenttext,String mpltime, String mguigefenlei,OnClickListener abenClick) {
//    	this.itemid = ids;
//        this.iconurl = iconurl;
//        this.username = musername;
//        this.contenttext = mcontenttext;
//        this.mOnclick = abenClick;
//        this.pltime=mpltime;
//        this.guigefenlei = mguigefenlei;
//    }
    
    
//    public String getImageUrl() {
//        return iconurl;
//    }
//	public String getTitletext()
//	{
//		return username;
//	}
//	public String getPricetext()
//	{
//		return contenttext;
//	}
//	public String getItemid()
//	{
//		return itemid;
//	}
//	public OnClickListener getmOnclick()
//	{
//		return mOnclick;
//	}
//	public String getGuigefenlei()
//	{
//		return guigefenlei;
//	}
//	public String getPltime()
//	{
//		return pltime;
//	}
	
	
}