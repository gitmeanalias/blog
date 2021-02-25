package com.project.blog.services;

import com.project.blog.entities.User;
import com.project.blog.exceptions.ResourceNotFoundException;
import com.project.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository userRepo;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        if(!userRepo.existsById(id)) {
            throw new ResourceNotFoundException("User with id "+id+" does not exist.");
        }
        return userRepo.findById(id);
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

}
