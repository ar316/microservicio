package org.usuarioservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usuarioservice.entities.User;
import org.usuarioservice.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User>  users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable  Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

//endpoint para crear usuario
    @PostMapping("/create")
    public ResponseEntity<User> getUserById(@RequestBody  User user) {
        return ResponseEntity.ok(userService.create(user)) ;
    }

    @PutMapping("/edit")
    public ResponseEntity<User> updateUser(@RequestBody  User user) {

        return ResponseEntity.ok( userService.update(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable  Long id) {
        return ResponseEntity.ok( userService.delete(id));
    }
}
