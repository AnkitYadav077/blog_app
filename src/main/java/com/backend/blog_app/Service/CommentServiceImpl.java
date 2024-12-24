package com.backend.blog_app.Service;

import com.backend.blog_app.Entity.Comment;
import com.backend.blog_app.Entity.Post;
import com.backend.blog_app.Entity.User;
import com.backend.blog_app.Exceptions.ResourceNotFoundException;
import com.backend.blog_app.Payloads.CommentDto;
import com.backend.blog_app.Repo.CommentRepo;
import com.backend.blog_app.Repo.PostRepo;
import com.backend.blog_app.Repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId,Integer id) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","user id",id));
        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);

    }


    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","comment id",commentId));
        this.commentRepo.delete(comment);

    }
}
