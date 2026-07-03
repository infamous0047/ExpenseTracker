package com.demouser.expensetracker.service;

import com.demouser.expensetracker.dto.expense.ExpenseRequest;
import com.demouser.expensetracker.dto.expense.ExpenseResponse;
import com.demouser.expensetracker.entity.Category;
import com.demouser.expensetracker.entity.Expense;
import com.demouser.expensetracker.entity.User;
import com.demouser.expensetracker.exceptions.ResourceNotFoundException;
import com.demouser.expensetracker.repository.CategoryRepository;
import com.demouser.expensetracker.repository.ExpenseRepository;
import com.demouser.expensetracker.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final UserRepository userRepo;
    private final ExpenseRepository repo;
    private final CategoryRepository catRepo;

    public ExpenseService(
        ExpenseRepository repo,
        CategoryRepository catRepo,
        UserRepository userRepo
    ) {
        this.repo = repo;
        this.catRepo = catRepo;
        this.userRepo = userRepo;
    }

    public ExpenseResponse create(ExpenseRequest dto) {
        User user = userRepo
            .findById(dto.userId())
            .orElseThrow(() -> new ResourceNotFoundException("User", dto.userId()));
        Expense expense = new Expense();
        expense.setTitle(dto.title());
        expense.setDescription(dto.description());
        expense.setExpenseDate(dto.expenseDate());
        expense.setAmount(dto.amount());
        expense.setUser(user);
        Category category = catRepo
            .findByCategoryType(dto.category())
            .orElseThrow(() ->
                new RuntimeException("CategoryNotFoundException")
            );
        expense.setCategory(category);
        repo.save(expense);
        return toResponse(expense);
    }

    private ExpenseResponse toResponse(Expense expenses) {
        return new ExpenseResponse(
            expenses.getExpenseId(),
            expenses.getTitle(),
            expenses.getDescription(),
            expenses.getExpenseDate(),
            expenses.getAmount(),
            expenses.getCategory().getCategoryType()
        );
    }

    public List<ExpenseResponse> getAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public ExpenseResponse getById(Long id) {
        Expense expenses = repo
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Expense", id));
        return toResponse(expenses);
    }

    public ExpenseResponse updateById(Long id, ExpenseRequest dto) {
        Expense expenses = repo
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Expense", id));
        expenses.setTitle(dto.title());
        expenses.setDescription(dto.description());
        expenses.setAmount(dto.amount());
        expenses.setExpenseDate(dto.expenseDate());
        Category category = catRepo
            .findByCategoryType(dto.category())
            .orElseThrow(() -> new RuntimeException("Category not found"));
        expenses.setCategory(category);
        Expense update = repo.save(expenses);

        return toResponse(update);
    }

    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Expense", id);
        }
        repo.deleteById(id);
    }

    private Expense toEntity(ExpenseRequest dto) {
        Expense expense = new Expense();
        expense.setTitle(dto.title());
        expense.setDescription(dto.description());
        expense.setExpenseDate(dto.expenseDate());
        expense.setAmount(dto.amount());
        Category category = catRepo
            .findByCategoryType(dto.category())
            .orElseThrow(() ->
                new RuntimeException("CategoryNotFoundException")
            );
        expense.setCategory(category);

        return expense;
    }
}
