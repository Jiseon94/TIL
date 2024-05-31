package com.example.demo.controller;


import com.example.demo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ContentController {

    private ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {

        this.contentService = contentService;
    }
//    id 값에 따라 url 변경되어서 GetMapping 됨
    @GetMapping("/content/{id}")
    public String contentP(@PathVariable("id")String id, Model model) {

//        System.out.println(id);

//      id 값을 Service 단으로 넘겨서 메서드의 반환값을 model 에 저장
        model.addAttribute("Content", contentService.selectOneContent(id));


        return "content";

    }
}
