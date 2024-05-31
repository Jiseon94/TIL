package com.example.demo.service;

import com.example.demo.dto.SaveDTO;
import com.example.demo.entity.ContentEntity;
import com.example.demo.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

//    객체 받을 준비
    private ContentRepository contentRepository;

//    생성자로 받을거임
//    Autowired 덕분에 springboot 에서 알아서 주입을 시켜줄거임
    @Autowired
    public ContentService(ContentRepository contentRepository) {

        this.contentRepository = contentRepository;
    }

//    service 단에서 쓰는 메소드
    public void saveContent(SaveDTO saveDTO) {

        String title = saveDTO.getTitle();
        String content = saveDTO.getContent();
        
//        System.out.println("ContentService - getter 확인중");
//        System.out.println(title);
//        System.out.println(content);

//        Entity 바구니 만들기
        ContentEntity content1 = new ContentEntity();

//        원래는 Entity 에 set 메서드 사용을 지양함.
//        실무에서는 꼭 생성자로 쓰길 바람.
        content1.setTitle(title);
        content1.setContent(content);

//        System.out.println("ContentService - setter 확인중");
//        System.out.println(content1);

//        Entity 에 잘 담겨진 데이터 채로 Repository 에게 저장하며 넘김
        contentRepository.save(content1);

//        System.out.println("ContentService - repository 확인중");
//        System.out.println(contentRepository);

        // 저장된 데이터 확인
//        ContentEntity savedEntity = contentRepository.findById(content1.getId()).orElse(null);
//        System.out.println("ContentService - 저장된 데이터 확인중");
//        System.out.println(savedEntity);

        return;
    }

//    응답을 위한 메서드
    public List<ContentEntity> selectContent() {

//        테이블에 저장된 모든 데이터를 다 보기
        return contentRepository.findAll();
    }
}
