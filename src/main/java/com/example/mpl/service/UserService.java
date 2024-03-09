package com.example.mpl.service;

import com.example.mpl.dto.ApiResponseDto;
import com.example.mpl.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public Users findByUsername(String username);
    public Users saveUser(Users user);
    public ApiResponseDto deleteUser(String username);
}
