package com.yibu.kuaibu.adapterly;


import java.util.List;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.adapterly.AsyncImageLoader.ImageCallback;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class PopUpGridOfCPXQAdapter extends ArrayAdapter<Cpxiangqingpopgridlist> {

        private GridView gridView;
        private AsyncImageLoader asyncImageLoader;
        public PopUpGridOfCPXQAdapter(Context activity, List<Cpxiangqingpopgridlist> minelistItem, GridView minelist) {
            super(activity, 0, minelistItem);
            this.gridView = minelist;
            asyncImageLoader = new AsyncImageLoader();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Activity activity = (Activity) getContext();

            // Inflate the views from XML
            View rowView = convertView;
            ViewCache viewCache;
            if (rowView == null) {
            	Log.e("PopUpGridOfCPXQAdapter","null, create");
                LayoutInflater inflater = activity.getLayoutInflater();
                rowView = inflater.inflate(R.layout.schanpintuijiangridview_item, null);
                viewCache = new ViewCache(rowView);
                rowView.setTag(viewCache);
            } else {
            	Log.e("PopUpGridOfCPXQAdapter","not null, won't create");
                viewCache = (ViewCache) rowView.getTag();
            }
            Cpxiangqingpopgridlist imageAndText = getItem(position);

            // Load the image and set it on the ImageView
            String imageUrl = imageAndText.getImageUrl();
            ImageView imageView = viewCache.getImageView();
            
            Log.i("imageUrl","-----imageUrl = " + imageUrl);
            if(imageView == null){
            	Log.i("PopUpGridOfCPXQAdapter","imageView null = ");
            	
            }else{
            	Log.i("PopUpGridOfCPXQAdapter","imageView not null= ");
            	imageView.setOnClickListener(imageAndText.getmOnclick());
            	
            }
            imageView.setTag(imageUrl);
            Drawable cachedImage = asyncImageLoader.loadDrawable(imageUrl, new ImageCallback() {
                public void imageLoaded(Drawable imageDrawable, String imageUrl) {
                    ImageView imageViewByTag = (ImageView) gridView.findViewWithTag(imageUrl);
                    if (imageViewByTag != null) {
                        imageViewByTag.setImageDrawable(imageDrawable);
                    }
                }
            });
            if (cachedImage == null) {
                imageView.setImageResource(R.drawable.test_yhdwodegongyingpic2);
            }else{
                imageView.setImageDrawable(cachedImage);
            }
            // Set the text on the TextView
            TextView textView = viewCache.getTextViewfortitle();
            textView.setText(imageAndText.getTitletext());
            
            TextView textView2 = viewCache.getTextViewforprice();
            textView2.setText(imageAndText.getPricetext());
            
           
            return rowView;
        }

}