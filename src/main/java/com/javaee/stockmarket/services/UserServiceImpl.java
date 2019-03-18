package com.javaee.stockmarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.javaee.stockmarket.domain.User;

import com.javaee.stockmarket.api.v1.model.*;
import com.javaee.stockmarket.repositories.*;
import com.javaee.stockmarket.api.v1.mapper.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return StreamSupport.stream(this.userRepository.findAll().spliterator(), false).map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getById(Long id) {
        User user = getUserById(id);
        return userMapper.userToUserDTO(user);
    }

    private User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User Not Found For ID value: " + id.toString());
        }
        return userOptional.get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO createNew(UserDTO userDTO) {
        User detachedUser = userMapper.userDTOToUser(userDTO);
        User userSaved = userRepository.save(detachedUser);
        return userMapper.userToUserDTO(userSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO save(Long id, UserDTO userDTO) {
        User detachedUser = userMapper.userDTOToUser(userDTO);
        detachedUser.setId(id);
        User userSaved = userRepository.save(detachedUser);
        return userMapper.userToUserDTO(userSaved);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}