package com.example.bootstrap.service;

import com.example.bootstrap.models.Role;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public interface RoleService {
    public List<Role> findAll();

    public Optional<Role> findById(long id);

    void save(Role role);

    Role findByRoleName(String roleAdmin);
}
