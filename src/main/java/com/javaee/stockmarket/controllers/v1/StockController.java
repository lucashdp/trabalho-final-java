package com.javaee.stockmarket.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.stockmarket.api.v1.model.*;
import com.javaee.stockmarket.services.StockService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This id Stock API")
@RestController
@RequestMapping(StockController.BASE_URL)
public class StockController {

	public static final String BASE_URL = "/api/v1/stock";

    private final StockService companyService;

    public StockController(StockService companyService) {
        this.companyService = companyService;
    }

    @ApiOperation(value = "View List of Stock", notes="These endpoint will return all stock")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StockViewDTO> getAll(){
        return companyService.getAllStock();
    }

    @ApiOperation(value = "Get Stock by Id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StockViewDTO getById(@PathVariable Long id){
        return companyService.getById(id);
    }

    @ApiOperation(value = "Create a new Stock")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockViewDTO create(@RequestBody StockDTO stock){
        return companyService.createNew(stock);
    }
}