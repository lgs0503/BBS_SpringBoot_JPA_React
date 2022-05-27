package lgs.bbs.code.controller;

import lgs.bbs.board.entity.Board;
import lgs.bbs.board.entity.BoardRepository;
import lgs.bbs.code.entity.Code;
import lgs.bbs.code.entity.CodeRepository;
import lgs.bbs.comm.HttpHeaderJsonType;
import lgs.bbs.comm.HttpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/code")
public class CodeController {

    @Autowired
    private CodeRepository codeRepository;

    private final String CLASS_TYPE = "code";

    @GetMapping
    public ResponseEntity searchList(Code code){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Count", codeRepository.searchListCount(code));
        message.put(CLASS_TYPE + "List", codeRepository.searchList(code));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{idx}")
    public ResponseEntity search(@PathVariable Long idx){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE, codeRepository.findAllById(Collections.singleton(idx)));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(Code code){

        code = Code.builder()
                .codeId(code.getCodeId())
                .codeName(code.getCodeName())
                .upperCodeIdx(code.getUpperCodeIdx())
                .codeValue(code.getCodeValue())
                .useYn(code.getUseYn())
                .ref1(code.getRef1())
                .ref2(code.getRef2())
                .ref3(code.getRef3())
                .build();

        codeRepository.save(code);
    }

    @DeleteMapping
    public void delete(Code code){
        codeRepository.delete(code);
    }
}
