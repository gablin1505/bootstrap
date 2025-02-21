package com.example.bootstrap.util;


import com.example.bootstrap.models.User;
import com.example.bootstrap.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PersonValidator implements Validator {

    private final UserServiceImp userDetailsServiceImp;

    @Autowired
    public PersonValidator(UserServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz
        );
    }

    @Override
    public void validate(Object target, Errors errors) {
        User person = (User) target;

        try {
            userDetailsServiceImp.loadUserByUsername(person.getUsername());

        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("email", "Invalid email address");


    }
}
