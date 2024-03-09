package com.example.mpl.service.impl;

import com.example.mpl.constants.MPLConstants;
import com.example.mpl.dto.ApiResponseDto;
import com.example.mpl.entity.CustomUserDetails;
import com.example.mpl.entity.Users;
import com.example.mpl.exception.CustomBadRequestException;
import com.example.mpl.exception.CustomInternalServerException;
import com.example.mpl.repository.UsersRepository;
import com.example.mpl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomBadRequestException("No user found with username: " + username,"");
        }
        return new CustomUserDetails(user);
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users saveUser(Users user) {
        try{
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new CustomBadRequestException(user.getUsername() + " username already exists.",e.getMessage());
        }

    }

    @Override
    public ApiResponseDto deleteUser(String username) {
        loadUserByUsername(username);
        int status = userRepository.deleteByUsername(username);
        if(status == 1){
            return ApiResponseDto.builder()
                    .status(MPLConstants.SUCCESS)
                    .message("User has been deleted")
                    .build();
        }else{
            throw new CustomInternalServerException("Technical Error!! User was not deleted","");
        }
    }
}
