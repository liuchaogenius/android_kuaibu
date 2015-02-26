package com.yibu.kuaibu.adapterly.shangjituijian;

import com.yibu.kuaibu.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewCache {

    private View baseView;
    private TextView textViewfortitle;
    private TextView textViewforprice;
    private ImageView imageView;
    
    private TextView textViewforhints;

    public ViewCache(View baseView) {
        this.baseView = baseView;
    }

//    public TextView getTextView() {
//        if (textView == null) {
//            textView = (TextView) baseView.findViewById(R.id.text);
//        }
//        return textView;
//    }

    public ImageView getImageView() {
        if (imageView == null) {
            imageView = (ImageView) baseView.findViewById(R.id.ItemImage);
        }
        return imageView;
    }

	public TextView getTextViewfortitle()
	{
		if (textViewfortitle == null) {
			textViewfortitle = (TextView) baseView.findViewById(R.id.ItemText);
        }
		return textViewfortitle;
	}

	public TextView getTextViewforprice()
	{
		if (textViewforprice == null) {
			textViewforprice = (TextView) baseView.findViewById(R.id.ItemTime);
        }
		return textViewforprice;
	}

	public TextView getTextViewforhints()
	{
		if (textViewforhints == null) {
			textViewforhints = (TextView) baseView.findViewById(R.id.ItemLiulan);
        }
		
		return textViewforhints;
	}

	
	
    
}