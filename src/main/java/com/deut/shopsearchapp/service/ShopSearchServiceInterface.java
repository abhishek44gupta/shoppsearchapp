package com.deut.shopsearchapp.service;

import java.util.List;

import com.deut.shopsearchapp.domain.Shop;

/**
 * 
 * @author IBM_ADMIN
 *
 */
public interface ShopSearchServiceInterface {
     /**
      * 
      * @return
      */
	 public List<Shop> getAllShopsDetails();
	 
	 /**
	  * 
	  * @return
	  */
	 public Shop getShopDetails();
}
