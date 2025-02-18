package com.example.bootstrap.conrollers;

import com.example.bootstrap.exception.NoSuchEmployeeException;
import com.example.bootstrap.models.User;
import com.example.bootstrap.models.UserDto;
import com.example.bootstrap.service.UserService;
import com.example.bootstrap.util.util.PersonErrorResponse;
import com.example.bootstrap.util.util.PersonNotCreatedExeption;
import com.example.bootstrap.util.util.PersonNotfoundExeption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
    private final UserService userService;
    private final View error;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminRestController(UserService userService, View error, ModelMapper modelMapper) {
        this.userService = userService;
        this.error = error;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<UserDto> getPeople() {
        return userService.getAllUsers().stream().map(p -> modelMapper.map(p, UserDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto getPeopleById(@PathVariable("id") long id) {
        return convertopersonDto(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createPeople(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                stringBuilder.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
            }
            throw new PersonNotCreatedExeption(stringBuilder.toString());
        }
        userService.addNewUser(convertoperson(userDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.edit(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NoSuchEmployeeException("There is no such employee with id " + id);
        }
        userService.deleteUser(id);
        return "Deleted user with id  " + id + " successfully deleted.";
    }

    private User convertoperson(@Valid UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);

    }


    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleexeption(PersonNotfoundExeption personNotfoundExeption) {
        PersonErrorResponse personErrorResponse
                = new PersonErrorResponse("Person with such id wasnt found ", System.currentTimeMillis());
        return new ResponseEntity<>(personErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleexeption(PersonNotCreatedExeption personNotCreatedExeption) {
        PersonErrorResponse personErrorResponse =
                new PersonErrorResponse(personNotCreatedExeption.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(personErrorResponse, HttpStatus.BAD_REQUEST);
    }

    public UserDto convertopersonDto(User person) {
        return modelMapper.map(person, UserDto.class);
    }
}
