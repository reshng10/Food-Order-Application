package com.codedecode.UserMS.controller;

import com.codedecode.UserMS.dto.UserDto;
import com.codedecode.UserMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDTO) {
        UserDto savedUser = userService.addUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/fetchUserById/{userId}")
    public ResponseEntity<UserDto> fetchUserDetailsById(@PathVariable Integer userId) {
        return userService.fetchUserDetailsById(userId);
    }

}
