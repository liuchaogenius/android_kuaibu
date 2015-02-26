/**
 * 
 */
package com.yibu.kuaibu.bean.gson;

import java.util.List;

/**
 * 43.获取/搜索商城列表
 * @author liujian
 *
 */
public class CompanyListGson
{
   public int result;
   public DataContent data;
   
   public static class DataContent{
	   public PageContent page;
	   public List<ChildItem> rslist;
	   
   }
   
   public static class ChildItem{
	   public String avatar;
	   public String company;
	   public int groupid;
	   public String star1;
	   public String star2;
	   public String truename;
	   public int userid;
   }
   
   public static class PageContent{
	   public int pageid;
	   public int pagetotal;
   }
}
