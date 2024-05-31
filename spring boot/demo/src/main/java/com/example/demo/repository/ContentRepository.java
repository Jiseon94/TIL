package com.example.demo.repository;

import com.example.demo.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//interface 라고 등록해두면 spring boot에서 자동으로 관리하기때문에
// @Repository 어노테이션을 굳이 생성하지 않아도 됨 (생성해도 노상관)

// JPA 라는 ORM 을 사용하기 위해 상속받아야함.
// 인자를 두개 전달해야함.
// 그 중 하나는 Entity, 나머지는 id 값의 type 인데 reference 타입으로 기입해야함.
// int => Integer
public interface ContentRepository extends JpaRepository<ContentEntity, Integer> {

//    ContentService 에서 세부항목페이지를 위해 만든 메서드
    ContentEntity findById(int id);


}
