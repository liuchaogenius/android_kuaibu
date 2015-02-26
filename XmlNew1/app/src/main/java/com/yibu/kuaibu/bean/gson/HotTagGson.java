/**
 * 
 */
package com.yibu.kuaibu.bean.gson;

import java.util.List;

/**
 * @author liujian
 *
 */
public class HotTagGson
{
	public int result;
	public DataContent data;
	
	public static class DataContent{
		public List<String> taglist;
	}

}
