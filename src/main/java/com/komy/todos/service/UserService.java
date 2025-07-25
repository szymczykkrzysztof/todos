package com.komy.todos.service;

import com.komy.todos.request.PasswordUpdateRequest;
import com.komy.todos.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo();

    void deleteUser();

    void updatePassword(PasswordUpdateRequest request);

}
