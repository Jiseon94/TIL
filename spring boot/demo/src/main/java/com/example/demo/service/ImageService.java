package com.example.demo.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demo.config.S3Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

//    config 주입하기
    private S3Config s3Config;

    @Autowired
    public ImageService(S3Config s3Config){
        this.s3Config = s3Config;
    }

//    Value 어노테이션을 통해 application.properties에서 등록해놓은 값들을 bucket 변수명에 저장하여 쓸 수가 있어짐
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//    잠깐 로컬에 저장될 주소
//    path 마지막에 \\ 를 넣음으로써, 계층형으로써 vs 폴더 안에 들어갈수있는거임
    private String localLocation = "D:\\TIL\\vs\\";
    
//    이미지 업로드하는 서비스 메소드
    public String imageUpload(MultipartRequest request)throws IOException {

//        request 인자에서 이미지 파일을 뽑아냄
       MultipartFile file = request.getFile("upload");

//       뽑아낸 이미지 파일에서 이름 및 확장자 추출
       String fileName = file.getOriginalFilename();
       String ext = fileName.substring(fileName.indexOf("."));

//       이미지 파일 이름 유일성을 위해 uuid 생성
       String uuidFileName = UUID.randomUUID() + ext;
       
//       서버환경에 저장할 경로 생성
       String localPath = localLocation + uuidFileName;

//       서버환경에 이미지 파일을 저장
       File localFile = new File(localPath);
//       file 을 저장하는 중에 오류가 발생할 수 있기에 이에 대해 오류처리를 해줘야 함! tthrows IOException 을 통해서
       file.transferTo(localFile);


//       s3에 이미지 올리기
//       Acl 은 보안관련된 부분이고, publicRead를 통해 외부에서 읽을 수 있게 설정하는것이다
        s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, uuidFileName, localFile).withCannedAcl(CannedAccessControlList.PublicRead));

//        s3에 올린 이미지의 주소를 받아야함. bucket, uuid 를 통해 확인된값을통해서
        String s3Url = s3Config.amazonS3Client().getUrl(bucket, uuidFileName).toString();

//        서버에 저장한 이미지를 삭제
//        delete 메서드를 사용하면, 자동으로 삭제가 된다고 함
        localFile.delete();

        return s3Url;
    }

}
