package com.yibu.kuaibu.net.helper;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.yibu.kuaibu.app.glApplication;


public class NetImageHelper {
    private RequestQueue mRequestQueue;
    private static NetImageHelper mHttpHelper;
    private static Object lock = new Object();
    private final int maxSize;
    public LruCache<String, Bitmap> mLruCache;
    private static ImageLoader mImageLoader;///mImageLoader
    private NetImageHelper() {
        ActivityManager activityManager = (ActivityManager) glApplication.getAppContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        maxSize = (int) (activityManager.getMemoryClass() * 1024 * 1024 * 0.33);
        mLruCache = new BitmapLruCache(maxSize);
        mRequestQueue = Volley.newRequestQueue(glApplication.getAppContext());
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {

            @Override
            public Bitmap getBitmap(String url) {
                return mLruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mLruCache.put(url, bitmap);
            }
        };
        mImageLoader = new ImageLoader(mRequestQueue, imageCache);
       // mRequestQueue.getCache().clear();
    }

    public static ImageLoader imageLoader() {
        if (mHttpHelper == null) {
            synchronized (lock) {
                if (mHttpHelper == null)
                    mHttpHelper = new NetImageHelper();
            }
        }
        return mImageLoader;
    }

    public static void  getBitmap(String url,ImageLoader.ImageListener listener){
        if(TextUtils.isEmpty(url))
            return;
        imageLoader().get(url,listener);
    }


    //
    public static void setImageUrl(NetworkImageView imageView,String url,int errorImage,int defaultImage){
       // url=filterPicUrl(url);
       //如果是空的
        if(TextUtils.isEmpty(url)){
            imageView.setDefaultImageResId(defaultImage);
            return;
        }
        imageView.setImageUrl(url, NetImageHelper.imageLoader());
        imageView.setErrorImageResId(errorImage);
        imageView.setDefaultImageResId(defaultImage);

    }





}