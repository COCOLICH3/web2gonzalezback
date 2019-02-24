package com.example.app.controllers;

import com.example.app.exceptions.UserNotFoundException;
import com.example.app.models.Config;
import com.example.app.models.User;
import com.example.app.models.Wall;
import com.example.app.repositories.ConfigRepository;
import com.example.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfigRepository configRepository;


    @CrossOrigin
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        List<User> users = null;
        users = userRepository.findAll();
        if (users != null && users.size() > 0)
            return users;
        return null;
    }

    @CrossOrigin
    @GetMapping("/users/{id}")
    public User retrieveStudent(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);

        return user.get();
    }

    @CrossOrigin
    @GetMapping("/users/email/{email}")
    public List<User> retrieveStudent(@PathVariable String email) {
        List<User> users = null;
        users = userRepository.findByEmail(email);
        if (users != null && users.size() > 0)
            return users;

        return null;
    }

    @CrossOrigin
    @GetMapping("/users/username/{email}")
    public List<User> retrieveStudentByUsername(@PathVariable String username) {
        List<User> users = null;
        users = userRepository.findByUsername(username);
        if (users != null && users.size() > 0)
            return users;

        return null;
    }


    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }

    @CrossOrigin
    @DeleteMapping("/users/delete")
    public void deleteAllUser() {
        userRepository.deleteAll();
    }

    @CrossOrigin
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();

    }


    @CrossOrigin
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        List<User> byUsernameAndPassword = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (byUsernameAndPassword.isEmpty()) {
            return null;
        }
        return byUsernameAndPassword.get(0);
    }

    @CrossOrigin
    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();

        user.setId(id);

        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @GetMapping("/config")
    public Config retrieveConfig() {
        List<Config> all = this.configRepository.findAll();
        if (!all.isEmpty()) {
            return all.get(0);
        }
        return null;

    }

    @CrossOrigin
    @PostMapping("/config")
    public void createUser(@RequestBody Config config) {
        this.configRepository.save(config);
    }

    @CrossOrigin
    @PostMapping("/users/{id}/share") //mando el id y a quien se lo estoy compartiendo
    public void postUser(@PathVariable long id,@RequestBody Wall wall2) {
        Optional<User> one = this.userRepository.findById(id); //busco el usuario al que le voy a compartir en la bD
        if(one.isPresent()){ //devuelve un opcional que puede no existir el usuario, si exste da true
            User user = one.get(); //trae el usuario dentro del optional

            Wall wall = new Wall(); //crea un wall
            wall.setUser(user);
            wall.setPost(wall2.getPost());
            user.getPosts().add(wall);
            this.userRepository.save(user);
        }
    }
}
