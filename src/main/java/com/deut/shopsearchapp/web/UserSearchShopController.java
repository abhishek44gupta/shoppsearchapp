package com.deut.shopsearchapp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deut.shopsearchapp.domain.Shop;
import com.deut.shopsearchapp.domain.User;
import com.deut.shopsearchapp.service.ShopSearchService;
import com.deut.shopsearchapp.util.CoordinateLocator;
import com.deut.shopsearchapp.util.HtmlRenderer;


@Controller
@RequestMapping(value = "/shopsearchapp/searchnearestshop")
@Import({ShopSearchService.class, CoordinateLocator.class, HtmlRenderer.class})
public class UserSearchShopController {

	@Autowired
	private ShopSearchService shopSearchService;

	@Autowired
	private CoordinateLocator coordinateLocator;
	
	@Autowired
	private HtmlRenderer htmlRenderer;

    @RequestMapping(method = RequestMethod.GET)
    public String viewSearchNearestShop(Map<String, Object> model) {
        User userForm = new User();    
        model.put("userForm", userForm);
        return "searchNearestShop";
    }
     
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String searchShop(@ModelAttribute("userForm") User user,
            Map<String, Object> model) { 
    	System.out.println("Calling search shop based on user input");
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
}
