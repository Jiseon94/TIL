package com.example.demo.dto;


import lombok.Data;

//lombok 을 통해서 setter 메소드를 편리하게 어노테이션으로 생성가능
//Data 어노테이션의 경우, 데이터를 넣고 가져올수있는 메소드를 갖고있기에 편리함.
@Data
public class SaveDTO {

    private String title;

    private String content;
}
