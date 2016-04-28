package com.deut.shopsearchapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.deut.shopsearchapp.domain.Shop;
import com.deut.shopsearchapp.domain.ShopSender;

@Service
@Controller
public class ShopSearchService implements ShopSearchServiceInterface {
	
	@Value("${shopsearchapp.restservice.server}")
	private String server;
	
	@Value("${shopsearchapp.restservice.port}")
	private String port;
	
	public ShopSearchService() {
	}

	public List<Shop> getAllShopsDetails() {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://"+server+":"+port+"/rest/allshops";
		ResponseEntity<List<Shop>> responseEntity = restTemplate.exchange(
				uri, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Shop>>() {
				});
		List<Shop> shopList = responseEntity.getBody();
		return shopList;
	}

	public Shop getShopDetails() {
		RestTemplate restTemplate = new RestTemplate();
		Shop s = new Shop();
		s.setShopName("PVC Jewellers");
		String uri = "http://"+server+":"+port+"/rest/shopsearch";
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		System.out.println("response:" + response);
		return s;
	}

	public void addShopsDetails(Shop shop) {
		ShopSender sp = new ShopSender();
		sp.setShopName(shop.getShopName());
		sp.setShopAddressLine1(shop.getShopAddressLine1());
		sp.setShopCity(shop.getShopCity());
		sp.setShopAddressPostCode(shop.getShopAddressPostCode());
		String uri = "http://"+server+":"+port+"/rest/addshop";
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> vars = new HashMap<String, String>();
		vars.put("id", "JS01");
		restTemplate.put(uri, sp,vars);
	}
}
