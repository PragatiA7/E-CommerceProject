package com.lcwd.electronic.storef.controllers;

import com.lcwd.electronic.storef.dtos.UserDto;
import com.lcwd.electronic.storef.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService ;

    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userDto1 = userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId,
                                              @RequestBody UserDto userDto){
        UserDto updatedUserDto = userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    //delete

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){

        userService.deleteUser(userId);
        return  new ResponseEntity<>("User is deleted Successfully !!",HttpStatus.OK);

    }


    //get all
    @GetMapping
      public ResponseEntity<List<UserDto>> getAllUsers(@PathVariable String userId){
        return  new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
      }

    //get single
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable String userId){

        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    //get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email ){

        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }
    //search user

    @GetMapping("/search/{keywords}")
    public ResponseEntity<UserDto> SearchUser(@PathVariable String keywords ){

        return new ResponseEntity<>(userService.getUserByEmail(keywords),HttpStatus.OK);
    }
}
