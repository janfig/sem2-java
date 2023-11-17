package com.example.lab10.service;

import java.util.ArrayList;

import com.example.lab10.converters.UserMapper;
import com.example.lab10.model.UserDto;
import com.example.lab10.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    public UserDto save(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.mapUserDaoToDto(userRepository.save(userMapper.mapUserDtoToDao(user)));
    }
}