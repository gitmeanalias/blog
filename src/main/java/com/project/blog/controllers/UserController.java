package com.project.blog.controllers;

import com.project.blog.entities.User;
import com.project.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class UserController {
    @Autowired private UserService usrService;

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(usrService.getUsers());
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<User> listUser(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(usrService.getUserById(id).get());
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(usrService.createUser(user));
    }
}
