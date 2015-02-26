/**
 * 
 */
package com.yibu.kuaibu.bean.gson;

/**
 * 6.发布供应
 * @author liujian
 *
 */
public class SupplyResultGson
{
  public int result;
  public DataContent data;
  
  public static class DataContent{
	  public int itemid;
	  public int moduleid;
  }
  
}
