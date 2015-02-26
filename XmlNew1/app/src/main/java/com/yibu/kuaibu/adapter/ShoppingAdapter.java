package com.yibu.kuaibu.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.yibu.kuaibu.R;
import com.yibu.kuaibu.bean.Product;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ShoppingAdapter extends BaseAdapter {
	List<Product> products;
	private Context context;
	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;

	public ShoppingAdapter(Context context, List<Product> products) {
		super();
		this.context = context;
		this.products = products;
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));

		options = new DisplayImageOptions.Builder()
				.displayer(new RoundedBitmapDisplayer(0xff000000, 10))
				.cacheInMemory().cacheOnDisc().build();
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LinearLayout relativeLayout;
		if (convertView != null) {
			relativeLayout = (LinearLayout) convertView;
		} else {
			relativeLayout = (LinearLayout) View.inflate(context,
					R.layout.shopping_item, null);
		}
		ImageView imageView = (ImageView) relativeLayout
				.findViewById(R.id.cart_icon);

		imageLoader.displayImage(products.get(position).getImageUrl(),
				imageView, options);
//		((TextView) relativeLayout.findViewById(R.id.shopping_item_name))
//				.setText(products.get(position).getProductName());
//		((TextView) relativeLayout.findViewById(R.id.shopping_item_describe))
//				.setText(products.get(position).getProductDescribe());

		
		((CheckBox)relativeLayout.findViewById(R.id.cart_checkbox)).setChecked(products.get(position).getCheckstate());
		
		
		((TextView) relativeLayout.findViewById(R.id.cart_title))
		.setText(products.get(position).getTitle());
		
		((TextView) relativeLayout.findViewById(R.id.cart_leibie))
		.setText(products.get(position).getLeibie());
		
		((TextView) relativeLayout.findViewById(R.id.cart_price))
		.setText(""+products.get(position).getPrice());
		
		((TextView) relativeLayout.findViewById(R.id.cart_num))
		.setText(""+products.get(position).getNum());
		
		((TextView) relativeLayout.findViewById(R.id.cart_total))
		.setText(""+(products.get(position).getPrice()*products.get(position).getNum()));
		
//		CheckBox abc = (CheckBox)relativeLayout.findViewById(R.id.cart_checkbox);
//		abc.setChecked(checked)
		
		return relativeLayout;
	}

}
