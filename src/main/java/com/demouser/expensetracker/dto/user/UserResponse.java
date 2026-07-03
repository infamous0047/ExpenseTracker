package com.demouser.expensetracker.dto.user;

import java.util.UUID;

public record UserResponse(UUID uuid, String username, String email) {}
