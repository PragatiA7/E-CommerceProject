package com.lcwd.electronic.storef.services.impl;

import com.lcwd.electronic.storef.dtos.PageableResponse;
import com.lcwd.electronic.storef.dtos.UserDto;
import com.lcwd.electronic.storef.entities.User;
import com.lcwd.electronic.storef.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.storef.helper.Helper;
import com.lcwd.electronic.storef.repositories.UserRepository;
import com.lcwd.electronic.storef.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User not found with given id !!"));

        user.setName(userDto.getName());
        //email update
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        //save user
        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("User not found with given id !!"));

        //delete user
        userRepository.delete(user);


    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc"))? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        //pageNumber default starts from 0

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<User> page = userRepository.findAll(pageable);

        PageableResponse<UserDto> response = Helper.getPageableResponse(page, UserDto.class);

        return response;
    }

    @Override
    public UserDto getUserById(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with given id !!"));

        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found eith given email id and password !!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {

        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream()
                .map(user -> entityToDto(user))
                .collect(Collectors.toList());
        return dtoList;
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
