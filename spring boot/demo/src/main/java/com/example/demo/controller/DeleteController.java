package com.example.demo.controller;

import com.example.demo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteController {

    private ContentService contentService;

    @Autowired
    public DeleteController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/delete/{id}")
    public String deleteC(@PathVariable("id")String id){

//        Service 단에 일시키기 
        contentService.deleteOneContent(id);

//        삭제완료되면 list 페이지로 리다이렉트
        return "redirect:/list";
    }
}
