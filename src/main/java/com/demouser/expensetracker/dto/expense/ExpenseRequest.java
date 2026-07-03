package com.demouser.expensetracker.dto.expense;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseRequest(
    @NotNull(message = "user id is required")
    Long userId,
    @NotBlank(message = ERROR_MESSAGE_TITLE)
    @Size(min = 1, max = 255, message = ERROR_MESSAGE_TITLE)
    String title,
    String description,
    @NotNull(message = ERROR_MESSAGE_DATE) LocalDate expenseDate,
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    BigDecimal amount,
    @NotBlank(message = ERROR_MESSAGE_CATEGORY) String category
) {
    private static final String ERROR_MESSAGE_TITLE =
        "Title must be between 1 to 255 characters";
    private static final String ERROR_MESSAGE_DATE =
        "Expense must have a Date ";
    private static final String ERROR_MESSAGE_CATEGORY =
        "Must have a Category type";
}
