package com.backend.blog_app.Controller;

import com.backend.blog_app.Exceptions.ApiException;
import com.backend.blog_app.Payloads.JwtAuthRequest;
import com.backend.blog_app.Payloads.JwtAuthResponse;
import com.backend.blog_app.Payloads.UserDto;
import com.backend.blog_app.Security.JwtTokenHelper;
import com.backend.blog_app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
            this.authenticate(request.getUsername(),request.getPassword());
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
           String token= this.jwtTokenHelper.generateToken(userDetails);
           JwtAuthResponse response=new JwtAuthResponse();
           response.setToken(token);
           return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
            try {

                this.authenticationManager.authenticate(authenticationToken);
            }catch (BadCredentialsException e){
                System.out.println("Invalid Detaials !!");
                throw new ApiException("Invalid username or password !!");
            }
    }

    //register new user

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
       UserDto registeredUser=this.userService.registerNewUser(userDto);
       return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
    }



}
