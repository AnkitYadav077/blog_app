package com.backend.blog_app.Controller;

import com.backend.blog_app.Entity.Comment;
import com.backend.blog_app.Payloads.ApiResponse;
import com.backend.blog_app.Payloads.CategoryDto;
import com.backend.blog_app.Payloads.CommentDto;
import com.backend.blog_app.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/user/{userid}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId,@PathVariable Integer userid){
        CommentDto createComment= this.commentService.createComment(comment,postId,userid);
        return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully!! ",true),HttpStatus.OK);
    }
}
