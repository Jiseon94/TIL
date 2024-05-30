package com.example.demo.controller;


import com.example.demo.dto.SaveDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveController {

    @PostMapping("/save")
    public String saveLogic(SaveDTO saveDTO) {

        System.out.println(saveDTO);

        return "redirect:/";

    }
}
