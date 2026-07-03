package com.demouser.expensetracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.demouser.expensetracker.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryType(String categoryType);
}
