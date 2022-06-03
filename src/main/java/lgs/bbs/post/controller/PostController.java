package lgs.bbs.post.controller;

import lgs.bbs.board.entity.Board;
import lgs.bbs.comm.HttpHeaderJsonType;
import lgs.bbs.comm.HttpMessage;
import lgs.bbs.post.entity.Post;
import lgs.bbs.post.entity.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    private final String CLASS_TYPE = "post";

    @GetMapping
    public ResponseEntity searchList(Post post){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Count", postRepository.searchListCount(post));
        message.put(CLASS_TYPE + "List", postRepository.searchList(post));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{idx}")
    public ResponseEntity search(@PathVariable Long idx){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE, postRepository.findAllById(Collections.singleton(idx)));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(@RequestBody Post post){

        post = Post.builder()
                .postName(post.getPostName())
                .boardIdx(post.getBoardIdx())
                .readCnt(post.getReadCnt())
                .postContent(post.getPostContent())
                .useYn(post.getUseYn())
                .fileNo1(post.getFileNo1())
                .fileNo2(post.getFileNo2())
                .fileNo3(post.getFileNo3())
                .deleteYn(post.getDeleteYn())
                .build();

        postRepository.save(post);
    }

    @DeleteMapping
    public void delete(@RequestBody Post post){
        postRepository.delete(post);
    }
}
