package com.example.bootstrap.service;

import com.example.bootstrap.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    List<User> getAllUsers();

    User getUser(long id);

    void deleteUser(long id);

    public User addNewUser(User user);

    public User edit(User user);
}
