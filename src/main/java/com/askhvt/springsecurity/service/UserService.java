package com.askhvt.springsecurity.service;

import com.askhvt.springsecurity.entity.User;
import com.askhvt.springsecurity.dto.UserDto;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);


    User findUserByEmail(String email);


//    User currentUser();

    List<UserDto> findAllUsers();
}