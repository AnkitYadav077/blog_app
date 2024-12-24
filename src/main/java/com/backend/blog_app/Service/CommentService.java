package com.backend.blog_app.Service;

import com.backend.blog_app.Entity.Comment;
import com.backend.blog_app.Payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId,Integer id);

    void deleteComment(Integer commentId);
}
