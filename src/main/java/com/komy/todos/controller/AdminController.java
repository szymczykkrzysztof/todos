package com.komy.todos.controller;

import com.komy.todos.response.UserResponse;
import com.komy.todos.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin REST API Endpoints", description = "Operations related admin users")
@RequestMapping("/api/admin")
@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users in the system")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return adminService.getAllUsers();
    }

    @Operation(summary = "Promote user to admin", description = "Promote a user to admin role")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}/role")
    public UserResponse promoteToAdmin(@PathVariable("userId") @Min(1) long id) {
        return adminService.promoteToAdmin(id);
    }


    @Operation(summary = "Delete user", description = "Delete a user from the system")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") @Min(1)long id)
    {
        adminService.deleteNonAdminUser(id);
    }
}
