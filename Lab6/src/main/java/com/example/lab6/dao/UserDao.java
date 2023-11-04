package com.example.lab6.dao;

import com.example.lab6.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
