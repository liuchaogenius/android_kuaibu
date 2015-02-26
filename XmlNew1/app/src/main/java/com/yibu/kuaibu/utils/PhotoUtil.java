package com.yibu.kuaibu.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

/**
 * 
 * @author Hejx
 *ͼƬ������ ���ṩѹ��������ͼƬ�Ƕ�
 */
public class PhotoUtil {
	public static String name =YhdConstant.DIR+ "temp.jpg";  //�洢���ļ����
	
	public static List<String> files  = new ArrayList<String>();
	
	
	public static Uri u;

	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}


	public static String fileName ="";
	//ת��ѹ������ļ�
	public static File transImage(String fromFile) {
		Bitmap bitmap = null;
		File myCaptureFile = null;
		try {
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;

			BitmapFactory.decodeFile(fromFile, opts);

			opts.inSampleSize = computeSampleSize(opts, -1, 560 * 700);
			opts.inJustDecodeBounds = false;

			bitmap = BitmapFactory.decodeFile(fromFile, opts);
			//bitmap = turnImage(fromFile, bitmap);
			// save file
		
			 File dir = new File(YhdConstant.DIR);    
		       if(!dir.exists()){    
		    	   dir.mkdir();    
		       }    
		       File dirFile = new File(YhdConstant.IMGDIR);    
		       if(!dirFile.exists()){    
		    	   dirFile.mkdir();    
		       }    
			 myCaptureFile = new File(name);
			FileOutputStream out = new FileOutputStream(myCaptureFile);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
				out.flush();
				out.close();
			}
			if (!bitmap.isRecycled()) {
				bitmap.recycle();// �ǵ��ͷ���Դ��������ڴ����
			}
		//	myCaptureFile = new File(name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return myCaptureFile;

	}
	
	 /**  
     * ���ֽ����鱣��Ϊһ���ļ�  
     *   
     * @param b  
     * @param outputFile  
     * @return  
     */  
    public static File getFileFromBytes(byte[] b, String outputFile) {   
    	
     
        File ret = null;   
        BufferedOutputStream stream = null;   
        try { 
        	 File dir = new File(YhdConstant.DIR);    
		       if(!dir.exists()){    
		    	   dir.mkdir();    
		       }    
		       File dirFile = new File(YhdConstant.IMGDIR);    
		       if(!dirFile.exists()){    
		    	   dirFile.mkdir();    
		       }    
		       
            ret = new File(outputFile);   
            FileOutputStream fstream = new FileOutputStream(ret);   
            stream = new BufferedOutputStream(fstream);   
            stream.write(b);   
        } catch (Exception e) {   
           // log.error("helper:get file from byte process error!");   
            e.printStackTrace();   
        } finally {   
            if (stream != null) {   
                try {   
                    stream.close();   
                } catch (IOException e) {   
                   //log.error("helper:get file from byte process error!");   
                    e.printStackTrace();   
                }   
            }   
        }   
        if(ret!=null){
        	ret = transImage(outputFile);
        	PhotoUtil.files.add(outputFile);
        }
        return ret;   
    }  
    
	//��ȡ����ͼ
	public static  Bitmap extractThumbnail(String fileName) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//��ʼ����ͼƬ����ʱ��options.inJustDecodeBounds ���true��
		newOpts.inJustDecodeBounds = true;
		//Bitmap bitmap = BitmapFactory.(bit,newOpts);;//��ʱ����bmΪ��
		BitmapFactory.decodeFile(fileName,newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//���������ֻ�Ƚ϶���800*480�ֱ��ʣ����ԸߺͿ���������Ϊ
		float hh = 60f;//
		float ww = 60f;//
		//���űȡ������ǹ̶��������ţ�ֻ�ø߻��߿�����һ����ݽ��м��㼴��
		int be = 1;//be=1��ʾ������
		if (w > h && w > ww) {//����ȴ�Ļ���ݿ�ȹ̶���С����
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//���߶ȸߵĻ���ݿ�ȹ̶���С����
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//�������ű���
		//���¶���ͼƬ��ע���ʱ�Ѿ���options.inJustDecodeBounds ���false��
		Bitmap bitmap = BitmapFactory.decodeFile(fileName,newOpts);
//		return compressImage(bitmap);//ѹ���ñ����С���ٽ�������ѹ��
		return bitmap;
	}
	//����ϵͳ�����ķ���
	public static File camera(Activity ac){
/*		Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
		startActivityForResult(it,  Activity.DEFAULT_KEYS_DIALER);*/
		File f = null;
		String name = "";
		if(storageState())
		{
		try {
			name = callTime();
		File dir=new File(YhdConstant.DIR);
		if(!dir.exists())dir.mkdirs();
		 
		Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		 f=new File(dir, name+".jpeg");//localTempImgDir��localTempImageFileName���Լ����������
		u=Uri.fromFile(f);
		intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
		fileName = f.getAbsolutePath();
		ac.startActivityForResult(intent,  2);
		} catch (ActivityNotFoundException  e) {
		// TODO Auto-generated catch block
		Toast.makeText(ac, "û���ҵ�����Ŀ¼", Toast.LENGTH_SHORT);
		}
		}else{
			Toast.makeText(ac, "û�д��濨", Toast.LENGTH_SHORT);
		}
		return f;
	}
	
	public static boolean storageState(){
		String status=Environment.getExternalStorageState();
		if(status.equals(Environment.MEDIA_MOUNTED))
		{
			return true;
		}else{
			return false;
		}
	}
	
	 public  static  String callTime() {

	        long backTime = new Date().getTime();

	        Calendar cal = Calendar.getInstance();

	        cal.setTime(new Date(backTime));

	        int year = cal.get(Calendar.YEAR);

	        int month = cal.get(Calendar.MONTH) + 1;

	        int date = cal.get(Calendar.DAY_OF_MONTH);

	        int hour = cal.get(Calendar.HOUR_OF_DAY);

	        int minute = cal.get(Calendar.MINUTE);

	        int second = cal.get(Calendar.SECOND);

	        String time = "" + year + month + date + hour + minute + second;

	        return time;

	  }
	/**
	 * ��̬����ͼƬ���Ŵ�С�ķ���
	 * 
	 * @param options
	 * @param minSideLength
	 * @param maxNumOfPixels
	 * @return
	 */
	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	
   //����ͼƬ�ĽǶ�
	private static Bitmap turnImage(String fileName, Bitmap bitmap) {
		Bitmap result = null;
		ExifInterface exifInterface = null;
		try {
			exifInterface = new ExifInterface(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int orc = exifInterface.getAttributeInt(
                 ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
		// Ȼ����ת��
		int degree = 0;
		if (orc == ExifInterface.ORIENTATION_ROTATE_90) {
			degree = 0;
		} else if (orc == ExifInterface.ORIENTATION_ROTATE_180) {
			degree = 0;
		} else if (orc == ExifInterface.ORIENTATION_ROTATE_270) {
			degree = 0;
		}else{
			degree = 0;
		}
		if (degree!=0&&bitmap != null) {
			Matrix m = new Matrix();
			m.setRotate(degree, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
			result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), m, true);

			return result;
		} else

			return bitmap;
	}
	
	/**
	 * ��ʾѡ�����Ի���
	 * @param context
	 */
	public static void showPhotoDialog(final Activity context) {
		new AlertDialog.Builder(context)
		.setTitle("ѡ��ʽ")
		/*.setPositiveButton("���", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				PhotoUtil.camera(context);
			}
		})
		.setNeutralButton("���", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				intent.setType("image/*");
				
//				Intent localIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				
				context.startActivityForResult(Intent.createChooser(intent, "ѡ��ͼƬ"),	1);
			}
		})
		.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		}).create();  // �����Ի���
		alertDialog.show(); // ��ʾ�Ի���
*/		
		 .setItems(new String[]{"������һ��","�����ѡȡ","ȡ��"}, new DialogInterface.OnClickListener(){


				@Override
				public void onClick(DialogInterface dialog, int which) {
					  dialog.dismiss();
					 switch (which) {
					case 0:
						PhotoUtil.camera(context);
						
						break;
					case 1:
						Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
						intent.addCategory(Intent.CATEGORY_OPENABLE);
						intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						intent.setType("image/*");
						
//						Intent localIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						
						context.startActivityForResult(Intent.createChooser(intent, "ѡ��ͼƬ"),	1);
						break;
					case 2:
					
					break;

					default:
						break;
						
					}
					
				}
				
			}).show();
	}
	/**
	 * ��ʾѡ�����Ի���
	 * @param context
	 */
	public static void showPhotoDialog2(final Activity context) {
		new AlertDialog.Builder(context)
		.setTitle("ѡ��ʽ")
	
		.setItems(new String[]{"������һ��","�����ѡȡ","ȡ��"}, new DialogInterface.OnClickListener(){
			
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent;
				switch (which) {
				case 0:
				//	PhotoUtil.camera(context);
					 intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "avatar.jpg")));
					context.startActivityForResult(intent, 2);
					break;
				case 1:
					 intent = new Intent(Intent.ACTION_GET_CONTENT);
					intent.addCategory(Intent.CATEGORY_OPENABLE);
					intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					
					context.startActivityForResult(Intent.createChooser(intent, "ѡ��ͼƬ"),	1);
					break;
				case 2:
					
					break;
					
				default:
					break;
					
				}
				
				
			}
			
		}).show();
	}
	
	public static  String getFilePath(Activity context,Uri uri){
		 String name = uri.toString();
		    if(name.contains("content:")){
			    String[] proj = { MediaStore.Images.Media.DATA };   
			    Cursor actualimagecursor = context.managedQuery(uri,proj,null,null,null);  
			    int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);   
			    actualimagecursor.moveToFirst();   
			     name = actualimagecursor.getString(actual_image_column_index); 
		    }else{
		    	   name = uri.getPath();
		    }
		    
			return name;
	}
}
