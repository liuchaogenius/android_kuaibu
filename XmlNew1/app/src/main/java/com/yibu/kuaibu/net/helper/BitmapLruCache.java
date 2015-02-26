package com.yibu.kuaibu.net.helper;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


/**
 */
public class BitmapLruCache extends LruCache<String, Bitmap> {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public BitmapLruCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        if (value == null)
            return 0;
        else
            return value.getRowBytes()*value.getHeight();
    }

    @Override
    protected Bitmap create(String key) {
        return super.create(key);
    }

    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
//        if(oldValue!=null)
//            oldValue.recycle();

    }

}