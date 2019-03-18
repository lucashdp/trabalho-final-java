package com.javaee.stockmarket.api.v1.mapper;

import org.springframework.stereotype.Component;

import com.javaee.stockmarket.api.v1.model.UserDTO;
import com.javaee.stockmarket.domain.User;;

@Component
public class UserMapper {
    public UserDTO userToUserDTO(User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        return userDTO;
    }

    public User userDTOToUser(UserDTO userDTO) {
        final User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        return user;
    }
}