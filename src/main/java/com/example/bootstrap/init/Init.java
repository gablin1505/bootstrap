package com.example.bootstrap.init;

import com.example.bootstrap.models.Role;
import com.example.bootstrap.models.User;
import com.example.bootstrap.service.RoleService;
import com.example.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    private void postConstruct() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));
        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        roleAdmin.add(roleService.findByRoleName("ROLE_ADMIN"));
        roleUser.add(roleService.findByRoleName("ROLE_USER"));
        User admin = new User();
        admin.setName("admin");
        admin.setLastname("admin");
        admin.setRole(roleAdmin);
        admin.setAge((long) 30);
        admin.setEmail("admin@mail.ru");
        admin.setPassword("admin");
        User user = new User();
        user.setName("user");
        user.setLastname("user");
        user.setRole(roleUser);
        user.setAge((long) 20);
        user.setEmail("user@mail.ru");
        user.setPassword("user");
        userService.addNewUser(user);
        userService.addNewUser(admin);
    }

}
