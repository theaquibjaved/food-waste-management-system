package com.example.foodmanagement.controller;

import com.example.foodmanagement.dto.UserResponseDTO;


import com.example.foodmanagement.entity.Users;
import com.example.foodmanagement.service.UserService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class UserController {

    @Autowired
    private UserService userService;

    
    
    
    
    // GET ALL USERS (return DTOs recommended)
    @GetMapping("/showusers")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET BY ID
    @GetMapping("/showusers/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted";
    }
}