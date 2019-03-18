package com.javaee.stockmarket.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.stockmarket.api.v1.model.UserDTO;
import com.javaee.stockmarket.domain.User;
import com.javaee.stockmarket.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This id User API")
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

	public static final String BASE_URL = "/api/v1/users";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "View List of Users", notes="These endpoint will return all users")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAll(){
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Get User by Id")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @ApiOperation(value = "Create a new User")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO user){
        return userService.createNew(user);
    }

    @ApiOperation(value = "Update an existing User")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO user){
        return userService.save(id, user);
    }

    @ApiOperation(value = "Delete a User")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        userService.deleteById(id);
    }
}