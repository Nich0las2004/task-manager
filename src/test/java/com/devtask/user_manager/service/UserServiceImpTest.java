package com.devtask.user_manager.service;

import com.devtask.user_manager.entity.UserEntity;
import com.devtask.user_manager.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsers() {
        UserEntity user1 = new UserEntity(1L, "John123", "john123@gmail.com");
        UserEntity user2 = new UserEntity(2L, "Jane123", "jane@gmail.com");
        List<UserEntity> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John123", result.get(0).getUsername());
        assertEquals("Jane123", result.get(1).getUsername());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void createUser() {
        UserEntity user = new UserEntity(1L, "John123", "john123@gmail.com");

        when(userRepository.save(user)).thenReturn(user);

        UserEntity createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user.getId(), createdUser.getId());
        assertEquals(user.getUsername(), createdUser.getUsername());
        assertEquals(user.getEmail(), createdUser.getEmail());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser() {
        Long userId = 1L;
        UserEntity existingUser = new UserEntity(userId, "John123", "john123@gmail.com");

        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        UserEntity updatedUser = userService.updateUser(userId, existingUser);

        assertNotNull(updatedUser);
        assertEquals(userId, updatedUser.getId());
        assertEquals(existingUser.getUsername(), updatedUser.getUsername());
        assertEquals(existingUser.getEmail(), updatedUser.getEmail());

        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void deleteUser() {
        Long userId = 1L;

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void getUserById() {
        Long userId = 1L;
        UserEntity user = new UserEntity(userId, "John123", "john123@gmail.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserEntity result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("John123", result.getUsername());
        assertEquals("john123@gmail.com", result.getEmail());

        verify(userRepository, times(1)).findById(userId);
    }
}