package com.deut.shopsearchapp.domain;

public class ShopSender {

	/**
	 * name of the shop
	 */
	private String shopName;
	
	/**
	 * Address of shop
	 */
	private String shopAddressLine1;
	
	/**
	 * 
	 */
	private String shopCity;
	
	/**
	 * 
	 * @return
	 */
	public String getShopCity() {
		return shopCity;
	}

	/**
	 * 
	 * @param shopCity
	 */
	public void setShopCity(String shopCity) {
		this.shopCity = shopCity;
	}


	/**
	 * postal code of shop
	 */
	private String shopAddressPostCode;
	
	/**
	 * longitude
	 */
	private double longitude;
	
	/**
	 * latitude
	 */
	private double latitude;

	/**
	 * 
	 * @return shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @param shopName
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * 
	 * @return shopAddressLine1
	 */
	public String getShopAddressLine1() {
		return shopAddressLine1;
	}

	/**
	 * 
	 * @param shopAddressLine1
	 */
	public void setShopAddressLine1(String shopAddressLine1) {
		this.shopAddressLine1 = shopAddressLine1;
	}

	/**
	 * 
	 * @return shopAddressPostCode
	 */
	public String getShopAddressPostCode() {
		return shopAddressPostCode;
	}

	/**
	 * @param shopAddressPostCode
	 */
	public void setShopAddressPostCode(String shopAddressPostCode) {
		this.shopAddressPostCode = shopAddressPostCode;
	}

	/**
	 * @return longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
	/**
	 * if shopname, shopaddressline1 & shop postcode are same, it is referring to same shop
	 * @param Object
	 * @return boolean
	 */
	public boolean equals(Object shopObj){
		if(shopObj == null || !(shopObj instanceof Shop)) return false;
		ShopSender shop = (ShopSender) shopObj;
		if(this.getShopName().equals(shop.getShopName())
				&& this.getShopAddressLine1().equals(shop.getShopAddressLine1())
				  && this.getShopAddressPostCode().equals(shop.getShopAddressPostCode())
				    && this.getShopCity().equals(shop.getShopCity())){
			return true;
		}
		return false;
	}
	
	/**
	 * @return String
	 */
	public String toString(){
		return "{"+this.getShopName()+","+this.getShopAddressLine1()+","+this.getShopCity()
				 +","+this.getShopAddressPostCode()+"}";
	}
}
