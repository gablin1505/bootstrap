package com.example.bootstrap.models;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

public class UserDto {
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "age")
    private Long age;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private Set<Role> role = new HashSet<>();

    public UserDto() {
    }

    public UserDto(String firstname, String email, String lastName, Long age, Set<Role> role) {
        this.firstname = firstname;
        this.email = email;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
    }

    public UserDto(String firstname, String email, Long age, String lastName) {
        this.firstname = firstname;
        this.email = email;
        this.age = age;
        this.lastName = lastName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
