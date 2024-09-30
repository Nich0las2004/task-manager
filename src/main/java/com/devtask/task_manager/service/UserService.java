package com.devtask.task_manager.service;

import java.util.List;
import com.devtask.task_manager.entity.UserEntity;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity createUser(UserEntity user);
    UserEntity updateUser(Long id, UserEntity user);
    void deleteUser(Long id);
    UserEntity getUserById(Long id);
}
