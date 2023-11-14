package com.taskmanager.tasks.controllers;

import com.taskmanager.tasks.domain.user.User;
import com.taskmanager.tasks.domain.user.UserDTO;
import com.taskmanager.tasks.dto.ResponseDTO;
import com.taskmanager.tasks.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "User API", description = "USER API for CRUD operation")
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "Create a user")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO){
        User newUser = userService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get list with all users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete user by ID")
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        ResponseDTO responseDTO = new ResponseDTO("User with ID: " + id + " deleted successfully.");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update user info by ID param + requestBody with new info")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) throws Exception {
        User updatedUser = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
