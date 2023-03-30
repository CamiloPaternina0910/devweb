package com.devweb.taskmanager.controllers;

import com.devweb.taskmanager.dto.GeneralResponse;
import com.devweb.taskmanager.entities.User;
import com.devweb.taskmanager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ResponseEntity<GeneralResponse> addUser(@Valid @RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(GeneralResponse.builder()
                .status(200)
                .data("User created")
                .error(false)
                .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> deleteUser(@PathVariable Long id){
        User user = userService.findById(id);
        userService.remove(user);
        return new ResponseEntity<>(GeneralResponse.builder()
                .status(HttpStatus.OK.value())
                .data("User delete")
                .error(false)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getAll(){
        return new ResponseEntity<>(GeneralResponse.builder()
                .status(200)
                .data(userService.findAll())
                .error(false)
                .build(),
                HttpStatus.OK);
    }
}
