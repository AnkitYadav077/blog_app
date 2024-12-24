package com.backend.blog_app.Payloads;

import com.backend.blog_app.Entity.Category;
import com.backend.blog_app.Entity.Comment;
import com.backend.blog_app.Entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;


    private Set<CommentDto> comments=new HashSet<>();



}
