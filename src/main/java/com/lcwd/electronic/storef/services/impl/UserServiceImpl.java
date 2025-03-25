package com.lcwd.electronic.storef.services.impl;

import com.lcwd.electronic.storef.dtos.UserDto;
import com.lcwd.electronic.storef.entities.User;
import com.lcwd.electronic.storef.repositories.UserRepository;
import com.lcwd.electronic.storef.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public UserDto createUser(UserDto userDto) {

        //generate unique id in string format

        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);

        //dto->entity
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);

        //entity->dto
         UserDto newDto =  entityToDto(savedUser);

        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public List<UserDto> getAllUser() {

        return null;
    }

    @Override
    public UserDto getUserById(String userId) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found eith given email id and password !!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {


        return null;
    }



    private UserDto entityToDto(User savedUser) {

//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .imageName(savedUser.getImageName()).build();

        return  mapper.map(savedUser,UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {

//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getName())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(user Dto.getGender())
//                .imageName(userDto.getImageName()).build();

        return mapper.map(userDto,User.class);

    }
}
