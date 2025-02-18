package com.example.bootstrap.conrollers;

import com.example.bootstrap.models.User;
import com.example.bootstrap.models.UserDto;
import com.example.bootstrap.service.RoleService;
import com.example.bootstrap.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public UserDto getPeopleById(@PathVariable("id") long id) {
        return convertopersonDto(userService.getUser(id));
    }

    public UserDto convertopersonDto(User person) {
        return modelMapper.map(person, UserDto.class);
    }

}
