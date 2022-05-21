package lgs.bbs.comm.controller;

import lgs.bbs.comm.HttpMessage;
import lgs.bbs.comm.entity.FileRepository;
import lgs.bbs.comm.entity.FileInfo;

import lgs.bbs.comm.entity.FileSpecification;
import lgs.bbs.user.entity.User;
import lgs.bbs.user.entity.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FileController {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @Autowired
    private FileRepository fileRepository;

    // 파일업로드
    @PostMapping(value="upload")
    public ResponseEntity upload(MultipartFile file) throws IllegalStateException, IOException {
        HttpMessage message = new HttpMessage();

        if( !file.isEmpty() ) {
            long fileSize = file.getSize();
            String originFileName = file.getOriginalFilename();
            int dot = originFileName.lastIndexOf(".");
            String fileExtension = originFileName.substring(dot, originFileName.length());
            originFileName = originFileName.substring(0, dot);
            String fileName = UUID.randomUUID().toString();

            FileInfo fileEntity = FileInfo.builder()
                                    .originFileName(originFileName)
                                    .fileName(fileName)
                                    .filePath(uploadPath)
                                    .fileExtension(fileExtension)
                                    .fileSize(fileSize)
                                    .build();

            fileRepository.save(fileEntity);

            /* 저장된 파일 일련번호 반환 */
            message.put("idx", fileEntity.getIdx());

            file.transferTo(new File(fileName));
        }

        return ResponseEntity.ok()
                .body(message.getMessage());
    }

    // 파일다운로드
    @GetMapping(value="download/{idx}")
    public ResponseEntity<Resource> download(@PathVariable Long idx
                                            ,HttpServletRequest request){

        ResponseEntity result = null;

        try {
            /* 파일 정보 DB 조회 */
            Specification<FileInfo> spec = (root, query, criteriaBuilder) -> null;

            spec = spec.and(FileSpecification.equalIdx(idx));

            FileInfo fileData = fileRepository.findAll(spec).get(0);

            if(fileData == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            String originFileName = fileData.getOriginFileName() + fileData.getFileExtension();
            String fileName = fileData.getFileName();

            originFileName = URLDecoder.decode(originFileName, "UTF-8");

            Resource file = new FileSystemResource(uploadPath + File.separator +  fileName);

            if(!file.exists())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            //브라우저별 한글파일 명 처리
            String onlyFileName = originFileName.substring(originFileName.lastIndexOf("_") + 1);

            String agent = request.getHeader("User-Agent");

            if(agent.contains("Trident"))//Internet Explore
                onlyFileName = URLEncoder.encode(onlyFileName, "UTF-8").replaceAll("\\+", " ");

            else if(agent.contains("Edge")) //Micro Edge
                onlyFileName = URLEncoder.encode(onlyFileName, "UTF-8");

            else //Chrome
                onlyFileName = new String(onlyFileName.getBytes("UTF-8"), "ISO-8859-1");

            HttpHeaders header = new HttpHeaders();
            header.add("Content-Disposition", "attachment; filename=" + onlyFileName);

            result = new ResponseEntity<>(file, header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
