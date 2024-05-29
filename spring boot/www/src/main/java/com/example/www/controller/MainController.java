package com.example.www.controller;

import com.example.www.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/")
    public String mainP(Model model) {

        String name = mainService.test();
        model.addAttribute("username", name);

        return "main";
    }
}
