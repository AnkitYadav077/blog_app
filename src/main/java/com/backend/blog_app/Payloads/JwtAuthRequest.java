package com.backend.blog_app.Payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;

    private String password;
}
