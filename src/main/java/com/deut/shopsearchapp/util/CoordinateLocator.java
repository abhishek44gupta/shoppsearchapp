package com.deut.shopsearchapp.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import com.deut.shopsearchapp.domain.Shop;
import com.deut.shopsearchapp.domain.User;

@Component
public class CoordinateLocator {

	  /**
	   * 
	   * @param address
	   * @return
	   * @throws Exception
	   */
	  public  String[] getLatLongPositions(String address) throws Exception
	  {
	    int responseCode = 0;
	    String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
	    URL url = new URL(api);
	    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	    httpConnection.connect();
	    responseCode = httpConnection.getResponseCode();
	    if(responseCode == 200)
	    {
	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	      Document document = builder.parse(httpConnection.getInputStream());
	      XPathFactory xPathfactory = XPathFactory.newInstance();
	      XPath xpath = xPathfactory.newXPath();
	      XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	      String status = (String)expr.evaluate(document, XPathConstants.STRING);
	      if(status.equals("OK"))
	      {
	         expr = xpath.compile("//geometry/location/lat");
	         String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         expr = xpath.compile("//geometry/location/lng");
	         String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
	         return new String[] {latitude, longitude};
	      }
	      else
	      {
	         throw new Exception("Error from the API - response status: "+status);
	      }
	    }
	    return null;
	  }
	  
	  
	  /**
	   * 
	   * @param shopList
	   * @param user
	   * @return
	   */
	  public int getNearestShop(List<Shop> shopList,User user){
		  if(user.getUserLatitude()==null && user.getUserLongitude() == null){
			  return -1;
		  }
		  Double userLatitude = Double.parseDouble(user.getUserLatitude());
		  Double userLongitude = Double.parseDouble(user.getUserLongitude());
		  int shopIndexWithMinDestance = -1;
		  int index = -1;
		  Double minDistance = -1.0d;
		  for(Shop shop : shopList){
			  try{
				  Double shopLatitude = Double.parseDouble(shop.getLatitude());
				  Double shopLongitude = Double.parseDouble(shop.getLongitude());
				  Double distance = Math.sqrt((Math.pow((userLatitude - shopLatitude),2.0) + Math.pow((userLongitude - shopLongitude),2.0)));
				  index++;
				  if(minDistance<0.0){
					  minDistance = distance;
					  shopIndexWithMinDestance = index;
				  }else{
					  if(minDistance>distance){
						  minDistance = distance;
						  shopIndexWithMinDestance = index;
					  }
				  }
			  }catch(NumberFormatException nfe){
				  
			  }
		  }
		  
		  return shopIndexWithMinDestance;
	  }
}
