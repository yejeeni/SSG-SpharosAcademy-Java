package com.sinse.electroshop.controller.store;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    //application.properties 에 명시한 개발자가 작성한 변수값을 가져오자
    @Value("${app.upload.dir}")
    private String uploadDir;

    private Path path;

    @PostConstruct
    public void init(){
        log.debug("업로드될 파일의 경로는 {}", uploadDir);
        path=Path.of(uploadDir);
    }

    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(MultipartFile file) throws IOException {

        log.debug("파일명은 "+file.getOriginalFilename());

        String oriName=file.getOriginalFilename();

        //dsk.ere.dfds.jpg
        String ext=file.getOriginalFilename().substring(oriName.lastIndexOf(".")+1, oriName.length());

        String filename=UUID.randomUUID().toString()+"."+ext;
        log.debug(filename);

        Path target=path.resolve(filename); //기존 Path 객체에 파일명까지 적용..
        file.transferTo(target);

        return ResponseEntity.ok("업로드 완료");
    }

    @PostMapping("/files/upload")
    public ResponseEntity<String> uploadFiles(MultipartFile[] files) throws IOException {

        for(MultipartFile file : files){
            String oriName=file.getOriginalFilename();
            String ext = oriName.substring(oriName.lastIndexOf(".")+1, oriName.length());
            String filename=UUID.randomUUID().toString()+"."+ext;

            file.transferTo(path.resolve(filename));
        }
        return ResponseEntity.ok("업로드 완료");
    }
}
