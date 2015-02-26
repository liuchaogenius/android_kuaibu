/**
 * 
 */
package com.yibu.kuaibu.ui.photoalbum;

import java.io.File;
import java.io.FileNotFoundException;

import com.yibu.kuaibu.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * @author liujian
 * 
 */
public class Utils {

	private Context context;

	public Utils(Context context) {
		super();
		this.context = context;
	}

	public void imgExcute(ImageView imageView, ImgCallBack icb,
			String... params) {
		LoadBitAsynk loadBitAsynk = new LoadBitAsynk(imageView, icb);
		loadBitAsynk.execute(params);
	}

	public class LoadBitAsynk extends AsyncTask<String, Integer, Bitmap> {

		ImageView imageView;
		ImgCallBack icb;

		LoadBitAsynk(ImageView imageView, ImgCallBack icb) {
			this.imageView = imageView;
			this.icb = icb;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = null;
			try {
				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						bitmap = getPathBitmap(context,
								Uri.fromFile(new File(params[i])), 200, 200);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			if (result != null) {
				// imageView.setImageBitmap(result);
				icb.resultImgCall(imageView, result);
			}
		}

	}

	// ��ʾԭ��ͼƬ�ߴ��С
	public static Bitmap getPathBitmap(Context context,Uri imageFilePath, int dw, int dh)
			throws FileNotFoundException {
		// ��ȡ��Ļ�Ŀ�͸�
		/**
		 * Ϊ�˼������ŵı�����������Ҫ��ȡ����ͼƬ�ĳߴ磬������ͼƬ
		 * BitmapFactory.Options������һ�������ͱ���inJustDecodeBounds����������Ϊtrue
		 * ���������ǻ�ȡ���ľ���ͼƬ�ĳߴ磬�����ü���ͼƬ�ˡ�
		 * �������������ֵ��ʱ�����ǽ��žͿ��Դ�BitmapFactory.Options��outWidth��outHeight�л�ȡ��ֵ
		 */
		BitmapFactory.Options op = new BitmapFactory.Options();
		op.inJustDecodeBounds = true;
		// ����ʹ����MediaStore�洢���������URI��ȡ����������ʽ
		Bitmap pic = BitmapFactory.decodeStream(context.getContentResolver()
				.openInputStream(imageFilePath), null, op);

		int wRatio = (int) Math.ceil(op.outWidth / (float) dw); // �����ȱ���
		int hRatio = (int) Math.ceil(op.outHeight / (float) dh); // ����߶ȱ���

		/**
		 * �����������Ǿ���Ҫ�ж��Ƿ���Ҫ�����Լ����׶Կ��Ǹ߽������š� ����ߺͿ���ȫ����������Ļ����ô�������š�
		 * ����ߺͿ���������Ļ��С�������ѡ�������ء� ����Ҫ�ж�wRatio��hRatio�Ĵ�С
		 * ���һ���������ţ���Ϊ���Ŵ��ʱ��С��Ӧ���Զ�����ͬ�������š� ����ʹ�õĻ���inSampleSize����
		 */
		if (wRatio > 1 && hRatio > 1) {
			if (wRatio > hRatio) {
				op.inSampleSize = wRatio;
			} else {
				op.inSampleSize = hRatio;
			}
		}
		op.inJustDecodeBounds = false; // ע�����һ��Ҫ����Ϊfalse����Ϊ�������ǽ�������Ϊtrue����ȡͼƬ�ߴ���
		pic = BitmapFactory.decodeStream(context.getContentResolver()
				.openInputStream(imageFilePath), null, op);

		return pic;
	}

	public static int changeImage(int id) {
		int res = 0;
		switch (id) {
		case 1:
			res = R.drawable.sum1;
			break;
		case 2:
			res =  R.drawable.sum2;
			break;
		case 3:
			res = R.drawable.sum3;
			break;
		case 4:
			res = R.drawable.sum4;
			break;
		case 5:
			res = R.drawable.sum5;
			break;

		}
		return res;
	}
}
