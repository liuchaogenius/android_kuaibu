/**
 * 
 */
package com.yibu.kuaibu.bean.gson;

import java.util.List;

/**
 * 7.查看/搜索供应列表
 * @author liujian
 *
 */
public class SupplyItemGson
{
	public DataContent data;
	public int result;
	
	public static class DataContent{
		public PageContent page;
		public List<SupplyItem> rslist;
		public List<String> vip;
	}
	
	public static class SupplyItem{
		public String catname;
		public String editdate;
		public String edittime;
		public int itemid;
		public String thumb;
		public String title;
		public String typename;
		public int vip;
	}
	

	public static class PageContent{
		public int pageid;
		public int pagetotal;
	}
}
