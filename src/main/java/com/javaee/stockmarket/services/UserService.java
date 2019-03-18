package com.javaee.stockmarket.services;

import java.util.List;

import com.javaee.stockmarket.api.v1.model.UserDTO;
import com.javaee.stockmarket.domain.User;

public interface UserService {

	UserDTO getById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO createNew(UserDTO user);

    UserDTO save(Long id, UserDTO user);

    void deleteById(Long id);
}