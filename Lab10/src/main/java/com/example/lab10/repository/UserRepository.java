package com.example.lab10.repository;

import com.example.lab10.model.UserDao;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository<UserDao, Integer>
{
    UserDao findByUsername(String username);
}