package com.yibu.kuaibu.adapterly;

import java.util.List;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapterly.SYBannerImageLoader.ImageCallback;




import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 适配器，负责装配 、销毁  数据  和  组件 。
 */
public class MyAdapter extends PagerAdapter {

	private List<View> mList;
	private String[] urls;
	private ImageView image;

	
	private SYBannerImageLoader asyncImageLoader;
	
	public MyAdapter(List<View> list, String[] urlsa) {
		mList = list;
		asyncImageLoader = new SYBannerImageLoader();  
		urls= urlsa;
		
		
		
		
		
	}

	
	
	/**
	 * Return the number of views available.
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	
	/**
	 * Remove a page for the given position.
	 * 滑动过后就销毁 ，销毁当前页的前一个的前一个的页！
	 * instantiateItem(View container, int position)
	 * This method was deprecated in API level . Use instantiateItem(ViewGroup, int)
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(mList.get(position));
		
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}

	
	/**
	 * Create the page for the given position.
	 */
	@Override
	public Object instantiateItem(final ViewGroup container, final int position) {
		
		
		Drawable cachedImage = asyncImageLoader.loadDrawable(
				urls[position], new ImageCallback() {
					
					public void imageLoaded(Drawable imageDrawable,
							String imageUrl) {
						Log.i("now","call");
						View view = mList.get(position);
						image = ((ImageView) view.findViewById(R.id.imageimage));
						image.setBackground(imageDrawable);
						container.removeView(mList.get(position));
						container.addView(mList.get(position));
						// adapter.notifyDataSetChanged();

					}
				});

		View view = mList.get(position);
		image = ((ImageView) view.findViewById(R.id.imageimage));
		image.setBackground(cachedImage);

		container.removeView(mList.get(position));
		container.addView(mList.get(position));
		// adapter.notifyDataSetChanged();
//		container.addView(view);	

		return mList.get(position);

	}
	
	

}
