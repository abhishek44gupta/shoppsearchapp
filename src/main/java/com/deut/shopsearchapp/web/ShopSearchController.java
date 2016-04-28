package com.deut.shopsearchapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deut.shopsearchapp.domain.Shop;
import com.deut.shopsearchapp.domain.User;
import com.deut.shopsearchapp.service.ShopSearchService;
import com.deut.shopsearchapp.util.CoordinateLocator;
import com.deut.shopsearchapp.util.HtmlRenderer;

@RestController
@EnableAutoConfiguration
@Import({ShopSearchService.class, CoordinateLocator.class, HtmlRenderer.class})
public class ShopSearchController {

	@Autowired
	private ShopSearchService shopSearchService;

	@Autowired
	private CoordinateLocator coordinateLocator;
	
	@Autowired
	private HtmlRenderer htmlRenderer;
	
	@RequestMapping("/shopsearch")
	public Shop searchShop() {
		return shopSearchService.getShopDetails();
	}

	@RequestMapping(path = "/allshops", method = RequestMethod.GET)
	public String getAllShopsDetails() {
		List<Shop> shopList = shopSearchService.getAllShopsDetails();
		for(Shop shop: shopList){
			try{
			String[] latlong = coordinateLocator.getLatLongPositions(shop.getShopAddressPostCode());
			shop.setLatitude(latlong[0]);
			shop.setLongitude(latlong[1]);
			}catch(Exception e){
				shop.setLatitude("0");
				shop.setLongitude("0");
			}
		}		
		return htmlRenderer.getAllShopLocationOnGoogleMap(shopList);
	}

	
	@RequestMapping(path = "/alluserandshops", method = RequestMethod.GET)
	public String getUserAllShopsDetails() {
		List<Shop> shopList = shopSearchService.getAllShopsDetails();
		for(Shop shop: shopList){
			try{
			String[] latlong = coordinateLocator.getLatLongPositions(shop.getShopAddressPostCode());
			shop.setLatitude(latlong[0]);
			shop.setLongitude(latlong[1]);
			}catch(Exception e){
				shop.setLatitude("0");
				shop.setLongitude("0");
			}
		}	
		
		User user = new User();
		user.setUserName("Abhishek Gupta");
		user.setUserPostCode("SW1A 0AA");
		try{
		String[] latlong = coordinateLocator.getLatLongPositions(user.getUserPostCode());
		user.setUserLatitude(latlong[0]);
		user.setUserLongitude(latlong[1]);
		}catch(Exception e){
			user.setUserLatitude("0");
			user.setUserLongitude("0");
		}
		int indexNearestShop = coordinateLocator.getNearestShop(shopList, user);
		return htmlRenderer.getUserAndAllShopLocationOnGoogleMap(shopList,user,indexNearestShop);
	}
	
	
	@RequestMapping(path = "/addshop", method = RequestMethod.PUT)
	public void addShopsDetails(Shop s) {
		shopSearchService.addShopsDetails(s);
	}
}
