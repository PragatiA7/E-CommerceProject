package com.lcwd.electronic.storef.controllers;

import com.lcwd.electronic.storef.dtos.ApiResponseMessage;
import com.lcwd.electronic.storef.dtos.UserDto;
import com.lcwd.electronic.storef.services.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
        UserDto userDto1 = userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") String userId,
                                             @Valid @RequestBody UserDto userDto){
        UserDto updatedUserDto = userService.updateUser(userDto,userId);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    //delete

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){

        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage.builder().message("User is deleted Successfully !!").success(true).status(HttpStatus.OK).build();
        return  new ResponseEntity<>(message,HttpStatus.OK);

    }


    //get all
    @GetMapping()
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
