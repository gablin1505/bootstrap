package com.example.bootstrap.service;

import com.example.bootstrap.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService  extends UserDetailsService {

    List<User> getAllUsers();

    User getUser(long id);

    void deleteUser(long id);

    public void addNewUser(User user);

    public void edit(User user);
}
