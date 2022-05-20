package lgs.bbs.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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

@RestController
public class FileController {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    // 파일업로드
    @PostMapping(value="upload")
    public ResponseEntity upload(MultipartFile file) throws IllegalStateException, IOException {
        HttpMessage message = new HttpMessage();

        if( !file.isEmpty() ) {
            //log.debug("file org name = {}", file.getOriginalFilename());
            //log.debug("file content type = {}", file.getContentType());
            file.transferTo(new File(file.getOriginalFilename()));
        }

        return ResponseEntity.ok()
                .body(message.getMessage());
    }

    // 파일다운로드
    @GetMapping(value="download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName
                                            ,HttpServletRequest request){

        ResponseEntity result = null;

        try {
            String originFileName = URLDecoder.decode(fileName, "UTF-8");
            String agent = request.getHeader("User-Agent");

            Resource file = new FileSystemResource(uploadPath + File.separator +  originFileName);

            if(!file.exists()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            //브라우저별 한글파일 명 처리
            String onlyFileName = originFileName.substring(originFileName.lastIndexOf("_") + 1);

            if(agent.contains("Trident"))//Internet Explore
                onlyFileName = URLEncoder.encode(onlyFileName, "UTF-8").replaceAll("\\+", " ");

            else if(agent.contains("Edge")) //Micro Edge
                onlyFileName = URLEncoder.encode(onlyFileName, "UTF-8");

            else //Chrome
                onlyFileName = new String(onlyFileName.getBytes("UTF-8"), "ISO-8859-1");
            //브라우저별 한글파일 명 처리

            HttpHeaders header = new HttpHeaders();
            header.add("Content-Disposition", "attachment; filename=" + onlyFileName);

            result = new ResponseEntity<>(file, header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
