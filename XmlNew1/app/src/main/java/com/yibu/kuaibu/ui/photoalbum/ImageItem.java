/**
 * 
 */
package com.yibu.kuaibu.ui.photoalbum;

import android.graphics.Bitmap;

/**
 * @author liujian
 *
 */
public class ImageItem {
	
	private String path;
	private Bitmap bitmap;
	private int state = 0;   //0:δѡ�У�1��ѡ��
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	
	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	

}
