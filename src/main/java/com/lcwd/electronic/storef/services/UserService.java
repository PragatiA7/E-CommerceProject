package com.lcwd.electronic.storef.services;

import com.lcwd.electronic.storef.dtos.UserDto;
import com.lcwd.electronic.storef.entities.User;

import java.util.List;

public interface UserService {

    //create
   UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userId);

    //delete
    void deleteUser(String userId);

    //get all users
    List<UserDto> getAllUser(int pageNumber,int pageSize);

    //get single user by id
    UserDto getUserById(String userId);

    //get single user by email
    UserDto getUserByEmail(String email);

    //search user
    List<UserDto> searchUser(String keyword);
}
