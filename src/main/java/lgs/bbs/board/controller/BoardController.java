package lgs.bbs.board.controller;

import lgs.bbs.board.entity.Board;
import lgs.bbs.board.entity.BoardRepository;
import lgs.bbs.comm.HttpHeaderJsonType;
import lgs.bbs.comm.HttpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    private final String CLASS_TYPE = "board";

    @GetMapping
    public ResponseEntity searchList(Board board){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Count", boardRepository.searchListCount(board));
        message.put(CLASS_TYPE + "List", boardRepository.searchList(board));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{idx}")
    public ResponseEntity search(@PathVariable Long idx){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE, boardRepository.findAllById(Collections.singleton(idx)));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(Board board){

        board = Board.builder()
                .boardName(board.getBoardName())
                .boardType(board.getBoardType())
                .useYn(board.getUseYn())
                .deleteYn(board.getUseYn()).build();

        boardRepository.save(board);
    }

    @DeleteMapping
    public void delete(Board board){
        boardRepository.delete(board);
    }
}
