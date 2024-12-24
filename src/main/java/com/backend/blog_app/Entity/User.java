package com.backend.blog_app.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@Getter
@Setter

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name",nullable = false,length = 100)
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> comments=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns=@JoinColumn(name = "user",referencedColumnName ="id"),
            inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "id")
    )
    private Set<Role> roles= new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<SimpleGrantedAuthority> authories= this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return authories;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}