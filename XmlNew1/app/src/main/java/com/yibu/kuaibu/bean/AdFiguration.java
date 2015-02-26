package com.yibu.kuaibu.bean;

public class AdFiguration
{
	String adId;
	String adName;// 多盟,趣米
	String adModule;// 热门推荐,精选软件
	String adModuleOrder;// 模块内序号
	String adSort;// 内部排序方式

	// 普通积分墙广告
	String adIconUrl;
	String adDescription;
	String adPoint;

	float Ratio;

	// 0是没的

	public float getRatio()
	{
		return Ratio;
	}

	public void setRatio(float ratio)
	{
		Ratio = ratio;
	}

	public String getAdIconUrl()
	{
		return adIconUrl;
	}

	public void setAdIconUrl(String adIconUrl)
	{
		this.adIconUrl = adIconUrl;
	}

	public String getAdDescription()
	{
		return adDescription;
	}

	public void setAdDescription(String adDescription)
	{
		this.adDescription = adDescription;
	}

	public String getAdPoint()
	{
		return adPoint;
	}

	public void setAdPoint(String adPoint)
	{
		this.adPoint = adPoint;
	}

	public String getAdId()
	{
		return adId;
	}

	public void setAdId(String adId)
	{
		this.adId = adId;
	}

	public String getAdName()
	{
		return adName;
	}

	public void setAdName(String adName)
	{
		this.adName = adName;
	}

	/*
	 * public String getAdType() { return adType; }
	 * 
	 * public void setAdType(String adType) { this.adType = adType; }
	 */

	public String getAdModule()
	{
		return adModule;
	}

	public void setAdModule(String adModule)
	{
		this.adModule = adModule;
	}

	public String getAdModuleOrder()
	{
		return adModuleOrder;
	}

	public void setAdModuleOrder(String adModuleOrder)
	{
		this.adModuleOrder = adModuleOrder;
	}

	public String getAdSort()
	{
		return adSort;
	}

	public void setAdSort(String adSort)
	{
		this.adSort = adSort;
	}

}
