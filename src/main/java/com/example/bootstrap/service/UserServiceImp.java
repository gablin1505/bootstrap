package com.example.bootstrap.service;

import com.example.bootstrap.models.Role;
import com.example.bootstrap.models.User;
import com.example.bootstrap.repositories.RoleRepository;
import com.example.bootstrap.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImp(UserRepository peopleService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = peopleService;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(long id) {
        return userRepository.getById(id);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User addNewUser(User user) {
        Set<Role> roles = new HashSet<>();
        for (Role role : user.getRole()) {
            roles.add(roleRepository.findById(role.getId()).get());
        }
        user.setRole(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User edit(User user) {
        if (user == null) {
            throw new EntityNotFoundException("User not found");
        }
        //Это нужно, чтоб пароль не хэшировался каждый раз, когда редактируем юзера
        //Проверяет, если пароль совпадает с текущим - сохраняет, если нет - шифрует
        var currentPassword = userRepository.findById(user.getId()).get().getPassword();
        if (!user.getPassword().equals(currentPassword)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> person = userRepository.findByEmail(email);
        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return person.get();
    }
}
