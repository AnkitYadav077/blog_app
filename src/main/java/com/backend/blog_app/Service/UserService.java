package com.backend.blog_app.Service;

import com.backend.blog_app.Entity.User;
import com.backend.blog_app.Payloads.UserDto;

import java.util.List;

public interface UserService {


    UserDto registerNewUser(UserDto user);

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);
}
