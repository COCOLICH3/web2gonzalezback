package com.example.app.controllers;

import com.example.app.models.Post;
import com.example.app.models.User;
import com.example.app.models.Wall;
import com.example.app.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;

    @CrossOrigin
    @PostMapping("/posts")
    public ResponseEntity<Object> createUser(@RequestBody Post post) {

        Post savedPost = postRepository.save(post);

        Wall wall = new Wall();
        wall.setUser(savedPost.getUser_creator());
        wall.setPost(savedPost);

        savedPost.getUsers().add(wall);
        postRepository.save(savedPost);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
