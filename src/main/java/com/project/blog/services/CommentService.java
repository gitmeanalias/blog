package com.project.blog.services;

import com.project.blog.entities.Comment;
import com.project.blog.entities.Post;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired private CommentRepository commentRepo;
    @Autowired private PostService postService;

    public List<Comment> getComments() {
        return commentRepo.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        if(!commentRepo.existsById(id)) {
            throw new ResourceNotFoundException("Comment with id "+id+" does not exist.");
        }
        return commentRepo.findById(id);
    }

    public Comment createComment(Long postId, Comment comment) {
        List<Comment> comments = new ArrayList<Comment>();
        Post post = new Post();
        Optional<Post> postFound = postService.getPostById(postId);

        if(!postFound.isPresent()) {
            throw new ResourceNotFoundException("Post with id "+postId+" does not exist.");
        }
        Post tmpPost = postFound.get();
        comment.setPost(tmpPost);

        Comment tmpComment = commentRepo.save(comment);
        comments.add(tmpComment);
        post.setComments(comments);

        return tmpComment;
    }
}
