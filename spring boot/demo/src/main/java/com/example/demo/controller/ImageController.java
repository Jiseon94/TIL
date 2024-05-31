package com.example.demo.controller;

import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ImageController {

//    image service 부르기
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @PostMapping("/image/upload")
    @ResponseBody
//    image의 경우 Json 의 형태로 올것이기에 Map<String, Object>로 반환 받을예정임.
//    MultipartRequest 인자를 쓰기위해 @ResponseBody 어노테이션 사용하는거!
    public Map<String, Object> imageUpload(MultipartRequest request)throws Exception{

//        CKEditor 는 2가지 응답값을 받음. true / false
//        만약, true 라면 url 데이터에 대한 주소값을 받음.
//        그래서 미리 HashMap 형태의 responseData 라는 변수 생성해둠.
        Map<String, Object> responseData = new HashMap<>();

//        만약 파일저장 중 실패하여 에러 발생을 방지하기 위해
//        try~catch 문을 사용
        try{

//            저장에 대한 응답값 받기
            String s3Url = imageService.imageUpload(request);

//            응답이 성공하면 ,true 와 주소값을 받음
            responseData.put("uploaded", true);
            responseData.put("url", s3Url);

            return responseData;

        } catch (IOException e) {
//            응답이 실패하면, false 를 put
            responseData.put("uploaded", false);

            return responseData;
        }

    }
}
