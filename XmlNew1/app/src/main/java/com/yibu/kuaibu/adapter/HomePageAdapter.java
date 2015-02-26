/**
 * 
 */
package com.yibu.kuaibu.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author liujian
 *
 */
public class HomePageAdapter extends PagerAdapter {
	private Context ct;
	private List<View> list;

	public HomePageAdapter(Context ct, List<View> list) {
		this.ct = ct;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// super.destroyItem(container, position, object);
		((ViewPager) container)
				.removeView(list.get(position));
		
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		((ViewPager) container)
				.addView(list.get(position), 0);
		return list.get(position);
	}

}
