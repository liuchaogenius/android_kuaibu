/**
 * 
 */
package com.yibu.kuaibu.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author liujian
 *
 */
public class ScrollBottomScrollView extends ScrollView {  
	  
	  
	private ScrollBottomListener scrollBottomListener;  
	  
	public ScrollBottomScrollView(Context context) {  
	super(context);  
	}  
	  
	public ScrollBottomScrollView(Context context, AttributeSet attrs) {  
	super(context, attrs);  
	}  
	  
	public ScrollBottomScrollView(Context context, AttributeSet attrs,int defStyle) {  
	super(context, attrs, defStyle);  
	}  
	  
	@Override  
	protected void onScrollChanged(int l, int t, int oldl, int oldt){  
	if(t + getHeight() >=  computeVerticalScrollRange()){  
	//ScrollView滑动到底部了  
	scrollBottomListener.scrollBottom();  
	}  
	}  
	  
	/**
	 * 设置滑到底部监听事件
	 * @param scrollBottomListener
	 */
	public void setScrollBottomListener(ScrollBottomListener scrollBottomListener){  
	this.scrollBottomListener = scrollBottomListener;  
	}  
	  
	public interface ScrollBottomListener{  
	public void scrollBottom();  
	}  
	  
	}  
