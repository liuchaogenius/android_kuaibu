/**
 * 
 */
package com.yibu.kuaibu.bean;

import java.util.List;

import com.yibu.kuaibu.bean.gson.CartItemGson.ChildItem;
import com.yibu.kuaibu.bean.gson.CartItemGson.GroupItem;

/**
 * @author liujian
 * 
 */
public class CartListItem
{
	public static class GroupState{
		public GroupItem groupItem;
		public List<ChildState> childlist;
		public int groupstate = 0;
	}

	public static class ChildState{
		public ChildItem childItem;
		public int childstate = 0;
	}
}
