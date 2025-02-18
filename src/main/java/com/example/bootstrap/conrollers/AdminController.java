//package com.example.bootstrap.conrollers;
//
//
//import com.example.bootstrap.models.Role;
//import com.example.bootstrap.models.User;
//import com.example.bootstrap.service.RoleService;
//import com.example.bootstrap.service.UserService;
//import com.example.bootstrap.util.PersonValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    private final PersonValidator personValidator;
//    private final RoleService roleService;
//    private final UserService userService;
//
//    @Autowired
//    public AdminController(PersonValidator personValidator, RoleService roleService, UserService userService) {
//        this.personValidator = personValidator;
//        this.roleService = roleService;
//        this.userService = userService;
//    }
//
//
//    @GetMapping("/users")
//    public String showAllUsers(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("user", user);
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("person", new User());
//        List<Role> roles = roleService.findAll();
//        model.addAttribute("allRoles", roles);
//        return "users";
//    }
//
//    @PostMapping("/addNewUser")
//    public String addNewUser(@ModelAttribute("person") @Valid User user, BindingResult bindingResult) {
//        personValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "users";
//        }
//        userService.addNewUser(user);
//        return "redirect:/admin/users";
//    }
//
//    @PostMapping("/edit")
//    public String editUser(@ModelAttribute("showUser") @Valid User user) {
//        userService.edit(user);
//        return "redirect:/admin/users";
//    }
//
//
//    @PostMapping("/delete")
//    public String deleteUserId(@ModelAttribute("showUser") User user) {
//        userService.deleteUser(user.getId());
//        return "redirect:/admin/users";
//    }
//
//}
