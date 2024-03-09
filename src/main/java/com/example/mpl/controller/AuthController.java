package com.example.mpl.controller;

import com.example.mpl.dto.JwtRequest;
import com.example.mpl.dto.JwtResponse;
import com.example.mpl.entity.Users;
import com.example.mpl.exception.CustomBadRequestException;
import com.example.mpl.security.JwtHelper;
import com.example.mpl.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper helper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<Users> signUp(@RequestBody @Valid Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        if(request.getUsername().isEmpty() || request.getPassword().isEmpty()){
            throw new CustomBadRequestException("Username and Password field cannot be empty","");
        }else{
            doAuthenticate(request.getUsername(), request.getPassword());

            UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            String token = this.helper.generateToken(userDetails);
            JwtResponse response = JwtResponse.builder()
                    .jwtToken(token)
                    .username(userDetails.getUsername()).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (Exception e) {
            throw new CustomBadRequestException(" Invalid Username or Password!!","");
        }

    }

}
