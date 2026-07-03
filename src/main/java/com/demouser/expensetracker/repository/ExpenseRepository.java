package com.demouser.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demouser.expensetracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {}
