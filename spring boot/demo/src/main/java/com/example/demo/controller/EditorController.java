package com.example.demo.controller;


import com.example.demo.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EditorController {

    private ContentService contentService;

    @Autowired
    public EditorController(ContentService contentService){
        this.contentService = contentService;
    }

    @GetMapping("/editor")
    public String editorP() {

        return "editor";
    }

//    list 뷰에서 게시글 수정을 누르면, 기능하는 컨트롤러
//    결국 editor 페이지로 리턴한다.
    @GetMapping("/editor/{id}")
    public String updateP(@PathVariable("id")String id, Model model) {

//        기존 게시글 내용이 나와야하기에
        model.addAttribute("data", contentService.selectOneContent(id));
        return "editor";
    }
}
