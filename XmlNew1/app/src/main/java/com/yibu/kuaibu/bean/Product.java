package com.yibu.kuaibu.bean;


public class Product  {
	
	private String url;
	private String productName;
	//private SoftReference<Bitmap> productImage;
	private String productPrice;
	private String imageUrl;
	private String productDescribe;
	
	private Boolean checkstate;
	private String title;
	private String leibie;
	private int price;
	private int Num;
	

	public int getNum()
	{
		return Num;
	}

	public void setNum(int num)
	{
		Num = num;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public String getLeibie()
	{
		return leibie;
	}

	public void setLeibie(String leibie)
	{
		this.leibie = leibie;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Boolean getCheckstate()
	{
		return checkstate;
	}

	public void setCheckstate(Boolean checkstate)
	{
		this.checkstate = checkstate;
	}

	public String getProductDescribe() {
		return productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

}
