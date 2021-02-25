package com.project.blog.controllers;

import com.project.blog.entities.Comment;
import com.project.blog.services.CommentService;
import com.project.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired private CommentService commentService;

    @GetMapping(path = "/users/{id}/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> list() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @GetMapping(path = "/users/{id}/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable(value = "commentId") Long commentId) {
        return ResponseEntity.ok(commentService.getCommentById(commentId).get());
    }

    @PostMapping(path = "/users/{id}/posts/{postId}/comments")
    public ResponseEntity<Comment> saveComment(@PathVariable(value = "postId") Long postId, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(postId, comment));
    }
}
