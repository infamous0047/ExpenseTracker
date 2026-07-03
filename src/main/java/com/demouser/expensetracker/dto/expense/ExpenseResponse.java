package com.demouser.expensetracker.dto.expense;

import java.math.BigDecimal;
import java.time.LocalDate;



public record ExpenseResponse(
    Long expenseId,
    String title,
    String description,
    LocalDate expenseDate,
    BigDecimal amount,
    String categoryType
) {}
