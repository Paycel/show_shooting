package com.company.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile/database")
public class DatabaseController {
    @GetMapping("/actors")
    public String actors(){
        return "tables/actors";
    }

    @GetMapping("/scenario")
    public String scenario(){
        return "tables/scenario";
    }

    @GetMapping("/techtask")
    public String techtask(){
        return "tables/techtask";
    }

    @GetMapping("/raw_material")
    public String raw_material(){
        return "tables/raw_material";
    }

    @GetMapping("/edited_material")
    public String edited_material(){
        return "tables/edited_material";
    }
}
