
package com.yibu.kuaibu.bean.gson;

import java.util.List;

/**
 * 8.显示供应详情
 * @author liujian
 *
 */
public class SupplyDetailGson
{
	public DataContent data;
	public int result;
	
	public static class DataContent{
		public String adddate;
		public String addtime;
		public String catid;
		public String catname;
		public String content;
		public String editdate;
		public String edittime;
		public int favorite;
		public int hits;
		public int itemid;
		public String mobile;
		public List<Pics> pic;
		public String price;
		public String title;
		public String truename;
		public String typeid;
		public String typename;
		public String unit;
		public int userid;
		public int vip;
	}
	
	public static class Pics{
		public String large;
		public String middle;
		public String thumb;
	}

}
