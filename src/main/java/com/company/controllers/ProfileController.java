package com.company.controllers;

import com.company.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String profile(){
        return "profile";
    }

    @GetMapping("/tables")
    public String tables(){
        return "tables";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
