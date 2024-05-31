package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


//entity 의 경우 getter 또는 constructor 필요함.
// setter 는 금지시키는 편. 내부 값이 변경되는걸 꺼려함
// 그래서 보통 생성자로 데이터 집어넣음
@Entity
@Data
public class ContentEntity {

//    DB가 알아서 ID라는걸 알 수 있도록 어노테이션 붙여주고
//    ID값이 자동으로 생성할 수 있도록 GenerationType.IDENTITY 선언
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    게시글 제목
    private String title;

//    게시글 내용
    private String content;

}
