package com.devtask.task_manager.controller;

import java.util.List;

import com.devtask.task_manager.entity.UserEntity;
import com.devtask.task_manager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get users", description = "Get a list of Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = UserEntity.class)
                )}
            ),
            @ApiResponse(responseCode = "404", description = "Users not found",
            content = @Content)
    })

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Create user", description = "Create a new user")

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity createdUser = userService.createUser(user);
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(createdUser);
    }

    @Operation(summary = "Update user", description = "Update an existing user")

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        UserEntity updatedUser = userService.updateUser(id, user);

        if(updatedUser != null){
            return ResponseEntity.ok("User updated");
        }else{
            return ResponseEntity.status(404).body("User Not Found");
        }
    }

    @Operation(summary = "Delete user", description = "Delete an existing user")

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find user", description = "Find an existing user")

    @GetMapping("/find/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        UserEntity foundUser = userService.getUserById(id);

        if (foundUser != null) {
            return ResponseEntity.ok(foundUser);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
