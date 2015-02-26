/**
 * 
 */
package com.yibu.kuaibu.ui.photoalbum;

import java.util.List;

import com.yibu.kuaibu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * @author liujian
 *
 */
public class MyGridViewAdapter extends BaseAdapter implements OnClickListener{
	private Context context;
	private List<ImageItem> items;
	private Utils util;
	private int sum = 0;
	private OnSelectImageChangeListener onSelectImageChangeListener;
	private int canselect;
	

	

	public MyGridViewAdapter(Context context, List<ImageItem> items,int canselect) {
		super();
		this.context = context;
		this.items = items;
		this.canselect = canselect;
		util = new Utils(context);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
//		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.gridview_item, null);
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.stateimage = (ImageView) convertView.findViewById(R.id.stateiamge);
	        //convertView.setTag(holder);
//		}else{
//			holder = (ViewHolder) convertView.getTag();
//		}
		if(items.get(position).getBitmap() == null){
			//����ͼƬ
			util.imgExcute(holder.image,new ImgClallBackLisner(position), items.get(position).getPath());	
		}else {
			holder.image.setImageBitmap(items.get(position).getBitmap());
			if(items.get(position).getState() == 0){	
				holder.stateimage.setImageResource(R.drawable.selectno2);
			}else{
				holder.stateimage.setImageResource(R.drawable.selected2);
			}
		}
		holder.stateimage.setOnClickListener(this);
		holder.stateimage.setTag(position);
		
		return convertView;
	}
	
	private static class ViewHolder{
		ImageView image;
		ImageView stateimage;
	}
	
	public class ImgClallBackLisner implements ImgCallBack{
		int num;
		public ImgClallBackLisner(int num) {
			this.num=num;
		}
		
		@Override
		public void resultImgCall(ImageView imageView, Bitmap bitmap) {
			items.get(num).setBitmap(bitmap);
			imageView.setImageBitmap(bitmap);
		}
	}
		
	public void changeSelectState(int state,int position){
		items.get(position).setState(state);
	}
	
	public int getSelectState(int position){
		return items.get(position).getState();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.stateiamge:
			int position = (Integer) v.getTag();
			if(getSelectState(position) == 0){
				if(sum >= canselect){
					Toast.makeText(context, "您最多只能选择"+canselect+"张图片！", 0).show();
					return;
				}
				ImageView image = (ImageView) v;
				image.setImageResource(R.drawable.selected2);
				changeSelectState(1, position);
				sum+=1;
				onSelectImageChangeListener.onSelectImageChange(sum,position,1);
			}else{
				ImageView image = (ImageView) v;
				image.setImageResource(R.drawable.selectno2);
				changeSelectState(0, position);
				sum-=1;
				onSelectImageChangeListener.onSelectImageChange(sum,position,-1);
			}
			break;

		}
	}
	
	
	public void setOnSelectImageChangeListener(
			OnSelectImageChangeListener onSelectImageChangeListener) {
		this.onSelectImageChangeListener = onSelectImageChangeListener;
	}

	public interface OnSelectImageChangeListener{
		/**
		 * 
		 * @param sum 已选数量
		 * @param position 当前选择条目
		 * @param state 1 选择；-1 取消
		 */
		public void onSelectImageChange(int sum,int position,int state);
	}

	public int getSum()
	{
		return sum;
	}

	public void setSum(int sum)
	{
		this.sum = sum;
	}
	
}
