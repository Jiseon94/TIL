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

//    게시글 등록 메서드
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

//    List 페이지에서 세부 Content 페이지로 이동하여 보이는 데이터
//    모든 데이터가 다 보여야하기에 반환값은 ContentEntity 로 씀
    public ContentEntity selectOneContent(String id) {
//        id 값이 string 형태이나, Entity에서 int 로 저장되어있기에 형변환 필요
        int to = Integer.parseInt(id);

//        id 로 repository 에 저장된 내용 다 찾아오기
        return contentRepository.findById(to);

    }

//    게시글 삭제 메서드
    public void deleteOneContent(String id){

        int to = Integer.parseInt(id);

        contentRepository.deleteById(to);
    }
    
//    게시글 수정 메서드 (덮어쓰는 개념)
    public void updateOneContent(SaveDTO saveDTO, String id){

        int to = Integer.parseInt(id);

//        Entity 바구니 만들기
        ContentEntity content1 = new ContentEntity();

//        Entity 에 수정할 내용 담기
        content1.setId(to);
        content1.setTitle(saveDTO.getTitle());
        content1.setContent(saveDTO.getContent());

//        Entity 에 잘 담겨진 데이터 채로 Repository 에게 저장하며 넘김
        contentRepository.save(content1);
//        스프링 Data JPA에서는 save() 메소드를 통해 업데이트와 생성이 가능함.
//        save() 메소드는 테이블 PK값에 대해 이미 존재하는 PK값 데이터가 들어올 경우 덮어쓰는 update 구문을 실행함.

    }
}
