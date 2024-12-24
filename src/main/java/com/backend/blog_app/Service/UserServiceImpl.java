package com.backend.blog_app.Service;

import com.backend.blog_app.Config.AppConstants;
import com.backend.blog_app.Entity.Role;
import com.backend.blog_app.Entity.User;
import com.backend.blog_app.Payloads.UserDto;
import com.backend.blog_app.Repo.RoleRepo;
import com.backend.blog_app.Repo.UserRepo;
import com.backend.blog_app.Exceptions.ResourceNotFoundException;
import org.apache.catalina.UserDatabase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RoleRepo roleRepo;


    @Override
    public UserDto createUser(UserDto userDto) {


        User user=this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);

    }


    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user=this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

       User updatedUser= this.userRepo.save(user);
       UserDto userDto1=this.userToDto(updatedUser);

        return userDto1;
    }


    @Override
    public UserDto getUserById(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        return this.userToDto(user);
    }


    @Override
    public List<UserDto> getAllUser() {
        List<User>users=this.userRepo.findAll();

       List<UserDto> userDtos= users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

       return userDtos;
    }


    @Override
    public void deleteUser(Integer userId) {
       User user= this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }


    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User newuser = this.userRepo.save(user);
        return this.modelMapper.map(newuser,UserDto.class);
    }
}
