package com.demouser.expensetracker.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank(message = ERROR_MESSAGE_USERNAME)
    @Size(min = 1, max = 50, message = ERROR_MESSAGE_USERNAME)
    String username,
    @NotBlank(message = ERROR_MESSAGE_EMAIL) @Email String email,
    @NotBlank(message = ERROR_MESSAGE_PASSWORD) String password
) {
    private static final String ERROR_MESSAGE_USERNAME =
        "Username must have 1 to 50 characters";
    private static final String ERROR_MESSAGE_EMAIL = "Email must be valid";
    private static final String ERROR_MESSAGE_PASSWORD =
        "Password cannot be empty";
}
