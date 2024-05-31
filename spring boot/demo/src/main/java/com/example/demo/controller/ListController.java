package com.example.demo.controller;


import com.example.demo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ListController {

    private ContentService contentService;

    @Autowired
    public ListController(ContentService contentService) {

        this.contentService = contentService;
    }


    @GetMapping("/list")
    public String listP(Model model) {

//        model.addAttribute("ContentList", contentService.selectContent());


        List<?> contentList = contentService.selectContent();
        model.addAttribute("ContentList", contentList);

        // 로그 찍기
        if (contentList != null) {
            System.out.println("ContentList size: " + contentList.size());
            System.out.println("ContentList type: " + contentList.getClass().getName());
            if (!contentList.isEmpty()) {
                System.out.println("ContentList first item type: " + contentList.get(0).getClass().getName());
                System.out.println("ContentList first item: " + contentList.get(0));
            }
        } else {
            System.out.println("ContentList is null");
        }

//        view 단에 데이터 전달하기
        return "list";
    }
}
