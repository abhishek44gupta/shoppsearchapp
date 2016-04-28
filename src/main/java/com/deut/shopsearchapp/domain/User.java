package com.deut.shopsearchapp.domain;

public class User {
    
	/**
	 * user name
	 */
	private String userName;
	
	/**
	 * user postal code
	 */
	
	/**
	 * user current location - latitude
	 */
	private String userLatitude;
	
	/**
	 * user current location - longitude
	 */
	private String userLongitude;
	
	/**
	 * user's current location - post code
	 */
	private String userPostCode;
	
	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return userPostCode
	 */
	public String getUserPostCode() {
		return userPostCode;
	}
	
	/**
	 * @param userPostCode
	 */
	public void setUserPostCode(String userPostCode) {
		this.userPostCode = userPostCode;
	}

	/**
	 * 
	 * @return userLatitude
	 */
	public String getUserLatitude() {
		return userLatitude;
	}

	/**
	 * 
	 * @param userLatitude
	 */
	public void setUserLatitude(String userLatitude) {
		this.userLatitude = userLatitude;
	}

	/**
	 * 
	 * @return
	 */
	public String getUserLongitude() {
		return userLongitude;
	}

	public void setUserLongitude(String userLongitude) {
		this.userLongitude = userLongitude;
	}
	
}
