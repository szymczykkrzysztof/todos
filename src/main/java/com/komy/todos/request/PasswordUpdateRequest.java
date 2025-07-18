package com.komy.todos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PasswordUpdateRequest {
    @NotEmpty(message = "Old password is mandatory")
    @Size(min = 5, max = 30, message = "Old password must be at least 5 characters long")
    private String oldPassword;
    @NotEmpty(message = "New password is mandatory")
    @Size(min = 5, max = 30, message = "New password must be at least 5 characters long")
    private String newPassword;
    @NotEmpty(message = "Confirmed password is mandatory")
    @Size(min = 5, max = 30, message = "Confirmed password must be at least 5 characters long")
    private String newPassword2;

    public PasswordUpdateRequest(String oldPassword, String newPassword, String newPassword2) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPassword2 = newPassword2;
    }

    public @NotEmpty(message = "Old password is mandatory") @Size(min = 5, max = 30, message = "Old password must be at least 5 characters long") String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(@NotEmpty(message = "Old password is mandatory") @Size(min = 5, max = 30, message = "Old password must be at least 5 characters long") String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public @NotEmpty(message = "New password is mandatory") @Size(min = 5, max = 30, message = "New password must be at least 5 characters long") String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(@NotEmpty(message = "New password is mandatory") @Size(min = 5, max = 30, message = "New password must be at least 5 characters long") String newPassword) {
        this.newPassword = newPassword;
    }

    public @NotEmpty(message = "Confirmed password is mandatory") @Size(min = 5, max = 30, message = "Confirmed password must be at least 5 characters long") String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(@NotEmpty(message = "Confirmed password is mandatory") @Size(min = 5, max = 30, message = "Confirmed password must be at least 5 characters long") String newPassword2) {
        this.newPassword2 = newPassword2;
    }
}
