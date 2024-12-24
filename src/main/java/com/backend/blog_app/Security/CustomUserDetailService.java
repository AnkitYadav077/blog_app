package com.backend.blog_app.Security;

import com.backend.blog_app.Entity.User;
import com.backend.blog_app.Exceptions.ResourceNotFoundException;
import com.backend.blog_app.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loadingUser from database by username
        User user=this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","email: "+username,0));
        return user;
    }
}
