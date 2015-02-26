package com.yibu.kuaibu.app;

import com.yibu.kuaibu.R;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class MyListener implements OnPageChangeListener{
	private ImageView[] indicator_imgs ;

	public MyListener(ImageView[] indicator_imgsa)
	{
		// TODO Auto-generated constructor stub
		indicator_imgs = indicator_imgsa;
	}


	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub
		if (state == 0) {
			//new MyAdapter(null).notifyDataSetChanged();
		}
	}

	
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		
		// �ı����е����ı���ͼƬΪ��δѡ��
		for (int i = 0; i < indicator_imgs.length; i++) {
			
			indicator_imgs[i].setBackgroundResource(R.drawable.dot_unselected);
			
		}
		
		// �ı䵱ǰ����ͼƬΪ��ѡ��
		indicator_imgs[position].setBackgroundResource(R.drawable.dot_selected);
	}
	
	
}
