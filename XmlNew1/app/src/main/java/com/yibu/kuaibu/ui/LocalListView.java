package com.yibu.kuaibu.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class LocalListView extends ListView
{

	public LocalListView(Context context)
	{
		super(context);
	}

	public LocalListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public LocalListView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	/**
	 * 设置不滚动 ???
	 */
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);

	}

}
