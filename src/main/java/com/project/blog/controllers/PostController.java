package com.project.blog.controllers;

import com.project.blog.entities.Post;
import com.project.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/api/v1/")
public class PostController {
    @Autowired private PostService postService;

    @GetMapping(path = "/users/{id}/posts")
    public ResponseEntity<List<Post>> list() {
        return ResponseEntity.ok(postService.getPosts());
    }

    @GetMapping(path = "/users/{id}/posts/{postId}")
    public ResponseEntity<Post> listPost(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId).get());
    }

    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Post> savePost(@PathVariable(value = "id") Long usrId, @RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(usrId, post));
    }
}
