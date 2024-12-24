package com.backend.blog_app.Payloads;

import com.backend.blog_app.Entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 4,message = "Username must be min of 4 characters")
    private String name;

    @Email(message="Email address is not valid!!")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message = "Password must be min of 3 chars and max of 10 char !!")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles= new HashSet<>();


}
