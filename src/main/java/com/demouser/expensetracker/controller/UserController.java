package com.demouser.expensetracker.controller;

import com.demouser.expensetracker.dto.user.UserRequest;
import com.demouser.expensetracker.dto.user.UserResponse;
import com.demouser.expensetracker.service.UserService;
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
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateById(
        @PathVariable Long id,
        @Valid @RequestBody UserRequest dto
    ) {
        return service.updateById(id, dto);
    }
    
    
    
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
        System.out.println("Deleted");
    }
}
