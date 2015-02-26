/**
 * 
 */
package com.yibu.kuaibu.bean.gson;

import java.util.List;

/**
 * 25.获取购物车列表
 * @author liujian
 *
 */
public class CartItemGson
{
	public DataContent data;
	public int result;
	
	public static class DataContent{
		public PageContent page;
		public List<GroupItem> rslist;
		
		
	}
	
	public static class PageContent{
		public int pageid;
		public int pagetotal;
		
	}
	
	public static class GroupItem{
		public List<ChildItem> cartlist;
		public String company;
		public String truename;
		public int userid;
	}
	
	public static class ChildItem{
		public List<Express> express;
		public int itemid;
		public double number;
		public double price;
		public int skuid;
		public String skuname;
		public String thumb;
		public String title;
		public String catname;
		
	}
	
	public static class Express{
		public String name;
		public int start;
		public int step;
	}

}
