package com.javaee.stockmarket.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import com.javaee.stockmarket.domain.Shopping;
import com.javaee.stockmarket.services.ShoppingService;

@RestController
@RequestMapping(ShoppingController.BASE_URL)
public class ShoppingController {

	public static final String BASE_URL = "/api/v1/shopping";
	
	private ShoppingService shoppingService;
	
	public ShoppingController(ShoppingService shoppingService) {
		this.shoppingService = shoppingService;
	}

    @ApiOperation(value = "Post a purchase transaction")	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public String requestPurchase(@RequestBody Shopping shopping){
		shoppingService.requestPurchase(shopping);
        return "Purchase requested";
    }
}
