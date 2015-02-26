/**
 * 
 */
package com.yibu.kuaibu.utils;

import com.google.gson.Gson;

/**
 * @author liujian
 *
 */
public class GsonUtils
{
   public static <T> T jsonToBean(String jsonResult,Class<T> clz){
	   Gson gson = new Gson();
	   T t = gson.fromJson(jsonResult, clz);
	   return t;
   }
}
