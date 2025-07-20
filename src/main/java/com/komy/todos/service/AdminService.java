package com.komy.todos.service;

import com.komy.todos.response.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    UserResponse promoteToAdmin(long id);
    void deleteNonAdminUser(long id);
}
