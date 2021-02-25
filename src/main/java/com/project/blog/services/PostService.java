package com.project.blog.services;

import com.project.blog.entities.Post;
import com.project.blog.entities.User;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired private PostRepository postRepo;

    @Autowired private UserService userService;

    public List<Post> getPosts() {
        return postRepo.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        if(!postRepo.existsById(id)) {
            throw new ResourceNotFoundException("Post with id "+id+" does not exist.");
        }
        return postRepo.findById(id);
    }

    public Post createPost(Long usrId, Post post) {
        List<Post> posts = new ArrayList<Post>();
        User user = new User();
        Optional<User> userFound = userService.getUserById(usrId);

        if(!userFound.isPresent()) {
            throw new ResourceNotFoundException("User with id "+usrId+" does not exist.");
        }
        User tmpUser = userFound.get();
        post.setUser(tmpUser);

        Post tmpPost = postRepo.save(post);
        posts.add(tmpPost);
        user.setPosts(posts);

        return tmpPost;
    }
}
