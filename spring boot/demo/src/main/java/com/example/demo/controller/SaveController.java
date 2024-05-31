package com.example.demo.controller;


import com.example.demo.dto.SaveDTO;
import com.example.demo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveController {

//    service 를 controller 에게 등록시켜주기
    private ContentService contentService;

    @Autowired
    public SaveController(ContentService contentService) {

        this.contentService = contentService;
    }
    
    @PostMapping("/save")
    public String saveLogic(SaveDTO saveDTO) {

//        확인용
//        System.out.println(saveDTO);

//        service 단의 saveContent 메서드 호출하기
        contentService.saveContent(saveDTO);
       
        

        return "redirect:/";

    }
}
