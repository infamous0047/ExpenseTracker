package com.demouser.expensetracker.controller;

import com.demouser.expensetracker.dto.expense.ExpenseRequest;
import com.demouser.expensetracker.dto.expense.ExpenseResponse;
import com.demouser.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public ExpenseResponse create(@Valid @RequestBody ExpenseRequest dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ExpenseResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ExpenseResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ExpenseResponse updateById(
        @PathVariable Long id,
        @Valid @RequestBody ExpenseRequest dto
    ) {
        return service.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
