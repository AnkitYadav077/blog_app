package com.backend.blog_app.Controller;

import com.backend.blog_app.Payloads.ApiResponse;
import com.backend.blog_app.Payloads.UserDto;
import com.backend.blog_app.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto createUserDto=this.userService.createUser( userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid ){
       UserDto updatedUser= this.userService.updateUser(userDto,uid);
       return ResponseEntity.ok(updatedUser);
    }

    //ADMIN
    //Delete User
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")Integer uid){
          this.userService.deleteUser(uid);
          return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted SuccessFully",true),HttpStatus.OK);
    }


    //User get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }


    //Single user get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>getSingleUsers(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
