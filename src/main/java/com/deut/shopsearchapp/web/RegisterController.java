package com.deut.shopsearchapp.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.deut.shopsearchapp.domain.Shop;
import com.deut.shopsearchapp.service.ShopSearchService;
 
@Controller
@RequestMapping(value = "/shopsearchapp/register")
public class RegisterController {
 
	@Autowired
	private ShopSearchService shopSearchService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        Shop shopForm = new Shop();    
        model.put("shopForm", shopForm);
        return "shopRegistration";
    }
     
    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("shopForm") Shop shop,
            Map<String, Object> model) { 
        shopSearchService.addShopsDetails(shop);
        return "shopRegistrationSuccess";
    }
}
