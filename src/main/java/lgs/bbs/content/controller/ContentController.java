package lgs.bbs.content.controller;

import lgs.bbs.comm.HttpHeaderJsonType;
import lgs.bbs.comm.HttpMessage;
import lgs.bbs.content.entity.Content;
import lgs.bbs.content.entity.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/content")
public class ContentController {

    @Autowired
    private ContentRepository contentRepository;

    private final String CLASS_TYPE = "content";

    @GetMapping
    public ResponseEntity searchList(Content content){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Count", contentRepository.searchListCount(content));
        message.put(CLASS_TYPE + "List", contentRepository.searchList(content));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{idx}")
    public ResponseEntity search(@PathVariable Long idx){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE, contentRepository.findAllById(Collections.singleton(idx)));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(@RequestBody Content content){

        content = Content.builder()
                .html(content.getHtml())
                .contentName(content.getContentName())
                .useYn(content.getUseYn())
                .deleteYn(content.getUseYn()).build();

        contentRepository.save(content);
    }

    @DeleteMapping
    public void delete(@RequestBody Content content){
        contentRepository.delete(content);
    }
}
