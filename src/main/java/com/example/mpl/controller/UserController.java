package com.example.mpl.controller;

import com.example.mpl.dto.ApiResponseDto;
import com.example.mpl.exception.CustomBadRequestException;
import com.example.mpl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @DeleteMapping("{username}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponseDto> deleteUser(@PathVariable("username") String username){
        if(username.isEmpty()){
            throw new CustomBadRequestException("Username should not be null","");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(username));
        }
    }
}
