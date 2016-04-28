package com.deut.shopsearchapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * @author IBM_ADMIN
 *
 */
@JsonIgnoreProperties(ignoreUnknown=false)
public class Shop{

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
	 * postal code of shop
	 */
	private String shopAddressPostCode;
	
	/**
	 * longitude
	 */
	private String longitude;
	
	/**
	 * latitude
	 */
	private String latitude;

	
	
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
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	/**
	 * if shopname, shopaddressline1 & shop postcode are same, it is referring to same shop
	 * @param Object
	 * @return boolean
	 */
	public boolean equals(Object shopObj){
		if(shopObj == null || !(shopObj instanceof Shop)) return false;
		Shop shop = (Shop) shopObj;
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
