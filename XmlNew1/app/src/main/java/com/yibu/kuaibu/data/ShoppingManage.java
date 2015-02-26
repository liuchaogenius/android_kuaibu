package com.yibu.kuaibu.data;

import java.util.ArrayList;
import java.util.List;

import com.yibu.kuaibu.bean.Product;

import android.content.ContentValues;
import android.content.Context;

public class ShoppingManage
{
	// /**
	// * 向数据库中加入一条记录
	// *
	// * @param product
	// * @param context
	// */
	// public void addProduct(Product product, Context context) {
	// SQLiteDatabase sqLiteDatabase = DataBaseHelper.getInstance(context)
	// .getWritableDatabase();
	// ContentValues contentValues = new ContentValues();
	// contentValues.put("productName", product.getProductName());
	// contentValues.put("productPrice", product.getProductPrice());
	// contentValues.put("productDescribe", product.getProductDescribe());
	// contentValues.put("imageUrl", product.getImageUrl());
	// contentValues.put("url", product.getUrl());
	// if (sqLiteDatabase != null) {
	// sqLiteDatabase.insert("ShoppingList", null, contentValues);
	// sqLiteDatabase.close();
	// DataBaseHelper.closeDB();
	// }
	// }

	/**
	 * 取出Product集合数据
	 * 
	 * @param context
	 * @return
	 */
	// public List<Product> getProducts(Context context, String type) {
	// List<Product> products = new ArrayList<Product>();
	// SQLiteDatabase sqLiteDatabase = DataBaseHelper.getInstance(context)
	// .getReadableDatabase();
	//
	// if (sqLiteDatabase != null) {
	// Cursor cursor = sqLiteDatabase.query("ShoppingList", null, null,
	// null, null, null, null);
	// while (cursor.moveToNext()) {
	// Product product = new Product();
	//
	// product.setProductName(cursor.getString(cursor
	// .getColumnIndex("productName")));
	// product.setProductPrice(cursor.getString(cursor
	// .getColumnIndex("productPrice")));
	// product.setProductDescribe(cursor.getString(cursor
	// .getColumnIndex("productDescribe")));
	// product.setImageUrl(cursor.getString(cursor
	// .getColumnIndex("imageUrl")));
	// product.setUrl(cursor.getString(cursor.getColumnIndex("url")));
	//
	// products.add(product);
	// }
	// sqLiteDatabase.close();
	// DataBaseHelper.closeDB();
	// }
	// return products;
	// }

	public List<Product> getProducts(Context context, String type)
	{
		List<Product> products = new ArrayList<Product>();

		for (int i = 0; i < 10; i++)
		{
			Product product = new Product();

			product.setProductName("productName" + i);
			product.setProductPrice("1000" + i);
			product.setProductDescribe("productDescribe" + i);
			product.setImageUrl("imageUrl" + i);
			product.setUrl("url" + i);
			
			
//			private Boolean checkstate;
//			private String title;
//			private String leibie;
//			private int price;
//			private int Num;
			product.setCheckstate(false);
			product.setTitle("title"+i);
			product.setLeibie("leibie"+i);
			product.setPrice((i+1)*200);
			product.setNum(i+5);
			
			
			products.add(product);
		}

		return products;
	}

}
