package org.usuarioservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usuarioservice.DTO.CarDTO;
import org.usuarioservice.entities.User;
import org.usuarioservice.services.UserService;

import java.util.List;
import java.util.Map;

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

    //crear el endpint para editar
    @PutMapping("/edit")
    public ResponseEntity<User> updateUser(@RequestBody  User user) {

        return ResponseEntity.ok( userService.update(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable  Long id) {
        return ResponseEntity.ok( userService.delete(id));
    }

    @GetMapping("getCarsByUser/{id}")
    public ResponseEntity<Map<String, Object>> getCars(@PathVariable Long id) {
        Map<String, Object> carsByUser = userService.getAllCarsByUser(id);
        if (carsByUser != null && !carsByUser.isEmpty()) {
            return ResponseEntity.ok(carsByUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
