package com.demouser.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demouser.expensetracker.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
