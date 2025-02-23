package com.example.bootstrap.conrollers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/users")
    public String showAdminPage(Principal principal, Model model) {
        return "users";
    }

}
