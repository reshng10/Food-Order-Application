package com.codedecode.UserMS.service;

import com.codedecode.UserMS.dto.UserDto;
import com.codedecode.UserMS.entity.Users;
import com.codedecode.UserMS.mapper.UserMapper;
import com.codedecode.UserMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;


    public UserDto addUser(UserDto userDTO) {
        Users savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);

    }

    public ResponseEntity<UserDto> fetchUserDetailsById(Integer userId) {
        Optional<Users> fetchedUser =  userRepo.findById(userId);
        return fetchedUser.map(user -> new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(user), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }
}
