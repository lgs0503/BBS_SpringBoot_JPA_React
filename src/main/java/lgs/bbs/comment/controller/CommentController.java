package lgs.bbs.comment.controller;

import lgs.bbs.comm.HttpHeaderJsonType;
import lgs.bbs.comm.HttpMessage;
import lgs.bbs.comment.entity.Comment;
import lgs.bbs.comment.entity.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    private final String CLASS_TYPE = "Comment";

    @GetMapping
    public ResponseEntity searchList(@RequestBody Comment comment){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Count", commentRepository.searchListCount(comment));
        message.put(CLASS_TYPE + "List", commentRepository.searchList(comment));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{idx}")
    public ResponseEntity search(@PathVariable Long idx){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE, commentRepository.findAllById(Collections.singleton(idx)));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(@RequestBody Comment comment){

        comment = Comment.builder()
                .commentName(comment.getCommentName())
                .postIdx(comment.getPostIdx())
                .likeCnt(comment.getLikeCnt())
                .commentContent(comment.getCommentContent())
                .useYn(comment.getUseYn())
                .createdUser(comment.getCreatedUser())
                .upperCommentIdx(comment.getUpperCommentIdx())
                .deleteYn(comment.getDeleteYn())
                .build();

        commentRepository.save(comment);
    }

    @DeleteMapping
    public void delete(@RequestBody Comment comment){
        commentRepository.delete(comment);
    }
}
