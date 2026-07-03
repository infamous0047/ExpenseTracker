package com.demouser.expensetracker.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demouser.expensetracker.dto.user.UserRequest;
import com.demouser.expensetracker.dto.user.UserResponse;
import com.demouser.expensetracker.entity.User;
import com.demouser.expensetracker.exceptions.ResourceNotFoundException;
import com.demouser.expensetracker.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo,PasswordEncoder passwordEncoder){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    private UserResponse toResponse(User user){
        return new UserResponse(user.getUuid(),user.getUsername(), user.getEmail());
    }

    public UserResponse create(UserRequest dto){
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPasswordHash(passwordEncoder.encode(dto.password()));

        repo.save(user);
        return toResponse(user);
    }

    public List<UserResponse> getAll(){
        return repo.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    public UserResponse getById(Long id){
        User user = repo.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("User", id));
            return toResponse(user);
    }

    public void deleteById(Long id){
        if(!repo.existsById(id)){
            throw new ResourceNotFoundException("User", id);
        }
        repo.deleteById(id);
    }

    public UserResponse updateById(Long id, UserRequest dto){
        User user = repo.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("User", id));

            user.setUsername(dto.username());
            user.setEmail(dto.email());

            User updated = repo.save(user);
            return toResponse(updated);
            
    }

    private User toEntity(UserRequest dto){
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPasswordHash(dto.password());

        return user;
        
    }
	
}