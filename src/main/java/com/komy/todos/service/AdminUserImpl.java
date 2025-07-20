package com.komy.todos.service;

import com.komy.todos.entity.Authority;
import com.komy.todos.entity.User;
import com.komy.todos.repository.UserRepository;
import com.komy.todos.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class AdminUserImpl implements AdminService {
    private final UserRepository userRepository;

    public AdminUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(this::convertUserToUserResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse promoteToAdmin(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || user.get().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found or already and admin");
        }
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_ADMIN"));
        authorities.add(new Authority("ROLE_EMPLOYEE"));
        user.get().setAuthorities(authorities);
        User savedUser = userRepository.save(user.get());
        return convertUserToUserResponse(savedUser);
    }

    @Override
    @Transactional
    public void deleteNonAdminUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || user.get().getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found or already an admin");
        }
        userRepository.delete(user.get());
    }

    private UserResponse convertUserToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getFirstName() + " " + user.getLastName(), user.getEmail(), user.getAuthorities().stream().map(auth -> (Authority) auth).toList());
    }
}
