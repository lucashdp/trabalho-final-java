package com.javaee.stockmarket.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.stockmarket.api.v1.model.CompanyDTO;
import com.javaee.stockmarket.services.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This id Company API")
@RestController
@RequestMapping(CompanyController.BASE_URL)
public class CompanyController {

	public static final String BASE_URL = "/api/v1/companies";

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ApiOperation(value = "View List of Companies", notes="These endpoint will return all companies")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDTO> getAll(){
        return companyService.getAllCompanies();
    }

    @ApiOperation(value = "Get Company by Id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO getById(@PathVariable Long id){
        return companyService.getById(id);
    }

    @ApiOperation(value = "Create a new Company")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO create(@RequestBody CompanyDTO company){
        return companyService.createNew(company);
    }

    @ApiOperation(value = "Update an existing Company")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO update(@PathVariable Long id, @RequestBody CompanyDTO company){
        return companyService.save(id, company);
    }

    @ApiOperation(value = "Delete a Company")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        companyService.deleteById(id);
    }
}