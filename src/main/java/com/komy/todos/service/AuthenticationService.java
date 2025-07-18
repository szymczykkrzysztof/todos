package com.komy.todos.service;

import com.komy.todos.request.AuthenticationRequest;
import com.komy.todos.request.RegisterRequest;
import com.komy.todos.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);

}
