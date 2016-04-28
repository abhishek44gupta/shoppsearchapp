package com.deut.shopsearchapp.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deut.shopsearchapp.domain.Shop;
import com.deut.shopsearchapp.domain.User;

@Component
public class HtmlRenderer {

	public String getAllShopLocationOnGoogleMap(List<Shop> shopList){
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><title>Google Maps Multiple Markers</title>");
		sb.append("<script src=\"http://maps.google.com/maps/api/js?sensor=false\" type=\"text/javascript\"></script>");
		sb.append("</head><body><div id=\"map\" style=\"height: 800px; width: 900px;\"></div>\n\n");
		sb.append("<script type=\"text/javascript\">\n var locations = [\n");
		int index=1;
		for(Shop shop : shopList){
			sb.append("['").append(shop.getShopName()).append("',").append(shop.getLatitude());
			sb.append(",").append(shop.getLongitude()).append(",").append(index++).append("],");
			sb.append("\n");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("];").append("\n\n");
		
		sb.append("var map = new google.maps.Map(document.getElementById('map'), {\n");   
		sb.append("zoom: 10,\n");
		String[] latlong = getMedian(shopList);
		sb.append("center: new google.maps.LatLng("+latlong[0]+","+ latlong[1] +"),\n");
		sb.append("mapTypeId: google.maps.MapTypeId.ROADMAP\n");
		sb.append("});\n");
		
		
		sb.append("var infowindow = new google.maps.InfoWindow();\n");
		sb.append("var marker, i;\n");
		sb.append("var blueMarker = new google.maps.MarkerImage(\"http://maps.google.com/mapfiles/ms/icons/blue-dot.png\");\n");
		sb.append("for (i = 0; i < locations.length; i++) { \n");
		sb.append("marker = new google.maps.Marker({\n");
		sb.append("position: new google.maps.LatLng(locations[i][1], locations[i][2]),\n");
		sb.append("map: map,\n");
		sb.append("animation:google.maps.Animation.BOUNCE\n");
		sb.append("});\n");
		sb.append("google.maps.event.addListener(marker, 'click', (function(marker, i) {\n");
		sb.append(" return function() {\n");
		sb.append("infowindow.setContent(locations[i][0]);\n");
		sb.append("infowindow.open(map, marker);\n");
		sb.append(" }\n");
		sb.append("})(marker, i));\n");
		sb.append("}\n");//end of for loop
		sb.append("</script></body></html>");
		
		return sb.toString();
	}
	
	private String[] getMedian(List<Shop> shopList){
		int i = 0;
		Double lati = 0d;
		Double longi = 0d;
		for(Shop shop : shopList){
			 try{
			 lati = lati + Double.parseDouble(shop.getLatitude());
			 longi = longi + Double.parseDouble(shop.getLongitude());	
			 }catch(NumberFormatException  nfe){
				 lati = lati + 0.0;
				 longi = longi + 0.0;
			 }
			 i++;
		}
		lati = lati/(double)i;
		longi = longi/(double)i;
		String[] latlong = new String[]{lati.toString(),longi.toString()};
		return latlong;
	}
	
	public String getUserAndAllShopLocationOnGoogleMap(List<Shop> shopList,User user, int indexOfNearestShop){
		StringBuilder sb = new StringBuilder();
		sb.append("<html><head><title>Google Maps Multiple Markers</title>");
		sb.append("<script src=\"http://maps.google.com/maps/api/js?sensor=false\" type=\"text/javascript\"></script>");
		sb.append("</head><body><div id=\"map\" style=\"height: 800px; width: 900px;\"></div>\n\n");
		sb.append("<script type=\"text/javascript\">\n var locations = [\n");
		int index=1;
		for(Shop shop : shopList){
			sb.append("['").append(shop.getShopName()).append("',").append(shop.getLatitude());
			sb.append(",").append(shop.getLongitude()).append(",").append(index++).append("],");
			sb.append("\n");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("];").append("\n\n");
		
		sb.append("var map = new google.maps.Map(document.getElementById('map'), {\n");   
		sb.append("zoom: 10,\n");
		sb.append("center: new google.maps.LatLng("+user.getUserLatitude()+","+ user.getUserLongitude() +"),\n");
		sb.append("mapTypeId: google.maps.MapTypeId.ROADMAP\n");
		sb.append("});\n");
		
		
		sb.append("var infowindow = new google.maps.InfoWindow();\n");
		sb.append("var marker, i;\n");
		sb.append("var blueMarker = new google.maps.MarkerImage(\"http://maps.google.com/mapfiles/ms/icons/blue-dot.png\");\n");
		
		//for loop beginning
		//sb.append("var i=0;");
		sb.append("for (i = 0; i < locations.length; i++) { \n");
		
		sb.append("if(i=="+indexOfNearestShop+"){");
		sb.append("marker = new google.maps.Marker({\n");
		//sb.append("icon: blueMarker,");
		sb.append("position: new google.maps.LatLng(locations[i][1], locations[i][2]),\n");
		sb.append("map: map,\n");
		sb.append("animation:google.maps.Animation.BOUNCE\n");
		sb.append("});\n");
		sb.append("google.maps.event.addListener(marker, 'click', (function(marker, i) {\n");
		sb.append(" return function() {\n");
		sb.append("infowindow.setContent(locations[i][0]);\n");
		sb.append("infowindow.open(map, marker);\n");
		sb.append(" }\n");
		sb.append("})(marker, i));\n");
		sb.append("}");//end of if
		
		sb.append("else{");
		sb.append("marker = new google.maps.Marker({\n");
		sb.append("position: new google.maps.LatLng(locations[i][1], locations[i][2]),\n");
		sb.append("map: map,\n");
		//sb.append("animation:google.maps.Animation.BOUNCE\n");
		sb.append("});\n");
		sb.append("google.maps.event.addListener(marker, 'click', (function(marker, i) {\n");
		sb.append(" return function() {\n");
		sb.append("infowindow.setContent(locations[i][0]);\n");
		sb.append("infowindow.open(map, marker);\n");
		sb.append(" }\n");
		sb.append("})(marker, i));\n");
		sb.append("}");//end of else
		
		sb.append("}\n");//end of for loop
		
		//adding user
		
		
		sb.append("marker = new google.maps.Marker({\n");
		sb.append("icon: blueMarker,");
		sb.append("position: new google.maps.LatLng("+user.getUserLatitude()+","+ user.getUserLongitude()+"),\n");
		sb.append("map: map,\n");
		sb.append("animation:google.maps.Animation.BOUNCE\n");
		sb.append("});\n");
		sb.append("google.maps.event.addListener(marker, 'click', (function(marker, i) {");
		sb.append("return function() {");
		sb.append("infowindow.setContent('"+user.getUserName()+"');");
		sb.append("infowindow.open(map, marker);");
		sb.append("}");
		sb.append("})(marker, i));");
		
		sb.append("</script></body></html>");
		
		return sb.toString();
	}
}
