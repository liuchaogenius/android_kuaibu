/**
 * 
 */
package com.yibu.kuaibu.bean.gson;

import java.util.List;

/**
 * 40.获取/搜索产品列表
 * @author liujian
 *
 */
public class MallListGson
{
	public int result;
	public DataContent data;
	
	public static class DataContent{
		public PageContent page;
		public List<ChildItem> rslist;
		public List<String> typeid;
	}
    
	public static class PageContent{
		public int pageid;
		public int pagetotal;
	}
	public static class ChildItem{
		public String catname;
		public String editdate;
		public String edittime;
		public int itemid;
		public String price;
		public String thumb;
		public String title;
		public String typename;
	}
}
