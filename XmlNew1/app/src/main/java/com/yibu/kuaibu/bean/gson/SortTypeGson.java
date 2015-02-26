/**
 * 
 */
package com.yibu.kuaibu.bean.gson;

import java.util.List;

/**
 * 34.获取分类列表
 * @author liujian
 *
 */
public class SortTypeGson
{
	public int result;
	public List<BigType> data;
	

	public static class BigType{
		public int catid;
		public String catname;
		public List<SmallType> subcate;
	}
	
	public static class SmallType{
		public int catid;
		public String catname;
	}
	

}
