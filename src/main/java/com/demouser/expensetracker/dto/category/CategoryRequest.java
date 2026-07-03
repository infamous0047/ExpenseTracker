package com.demouser.expensetracker.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
    @NotBlank(message = ERROR_MESSAGE_CATTYPE) String categoryType
) {
    private static final String ERROR_MESSAGE_CATTYPE = "Type must be given";
}
