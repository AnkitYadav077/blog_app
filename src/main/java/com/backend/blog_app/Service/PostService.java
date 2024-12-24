package com.backend.blog_app.Service;

import com.backend.blog_app.Entity.Post;
import com.backend.blog_app.Payloads.PostDto;
import com.backend.blog_app.Payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoeyId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get single post
    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostsByCategory(Integer categoryId);

    //get all post by user
    List<PostDto> getPostsByUser(Integer userId);

    //search posts
    List<PostDto> searchPosts(String keyword);
}
