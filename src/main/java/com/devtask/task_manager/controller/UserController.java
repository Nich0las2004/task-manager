package com.devtask.task_manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            @ApiResponse(responseCode = "200", description = "Found all the Users",
                content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = UserEntity.class)
                )}
            ),
            @ApiResponse(responseCode = "404", description = "Users not found",
            content = @Content)
    })

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();

        if (users.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("response", "Users not found");
            return ResponseEntity.status(404).body(response);
        }

        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Create user", description = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created a User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Error creating a user",
                    content = @Content)
    })

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserEntity user) {
        try{
            UserEntity createdUser = userService.createUser(user);
            return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(createdUser);
        }
        catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "An unexpected error occurred");
            return ResponseEntity.status(500).body(response);
        }
    }

    @Operation(summary = "Update user", description = "Update an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Error updating a user",
                    content = @Content)
    })

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        UserEntity updatedUser = userService.updateUser(id, user);

        if(updatedUser != null){
            Map<String, String> response = new HashMap<>();
            response.put("response", "Updated the User");
            return ResponseEntity.status(201).body(response);
        }else{
            Map<String, String> response = new HashMap<>();
            response.put("response", "Error updating a user");
            return ResponseEntity.status(404).body(response);
        }
    }

    @Operation(summary = "Delete user", description = "Delete an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted a user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "Error deleting a user",
                    content = @Content)
    })

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        UserEntity userToDelete = userService.getUserById(id);

        if (userToDelete != null) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("response", "Error deleting a user");
            return ResponseEntity.status(404).body(response);
        }
    }

    @Operation(summary = "Find user", description = "Find an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the User",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class)
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)
    })

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserEntity foundUser = userService.getUserById(id);

        if (foundUser != null) {
            return ResponseEntity.ok(foundUser);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("response", "User not found");
            return ResponseEntity.status(404).body(response);
        }
    }
}
