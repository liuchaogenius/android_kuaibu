/**
 * 
 */
package com.yibu.kuaibu.bean.gson;

import java.util.List;

/**
 * @author liujian
 *
 */
public class CaigouDetailGson
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
		public String title;
		public String truename;
		public String typeid;
		public String typename;
		public int userid;
		public int vip;
		
		
	}
	
	public static class Pics{
		public String large;
		public String middle;
		public String thumb;
	}

}
