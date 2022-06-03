package lgs.bbs.popup.controller;

import lgs.bbs.comm.HttpHeaderJsonType;
import lgs.bbs.comm.HttpMessage;
import lgs.bbs.popup.entity.Popup;
import lgs.bbs.popup.entity.PopupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/popup")
public class PopupController {

    @Autowired
    private PopupRepository popupRepository;

    private final String CLASS_TYPE = "popup";

    @GetMapping
    public ResponseEntity searchList(Popup popup){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Count", popupRepository.searchListCount(popup));
        message.put(CLASS_TYPE + "List", popupRepository.searchList(popup));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{idx}")
    public ResponseEntity search(@PathVariable Long idx){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE, popupRepository.findAllById(Collections.singleton(idx)));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(@RequestBody Popup popup){

        popup = Popup.builder()
                .fileNo(popup.getFileNo())
                .xPos(popup.getXPos())
                .yPos(popup.getYPos())
                .width(popup.getWidth())
                .height(popup.getHeight())
                .html(popup.getHtml())
                .popupName(popup.getPopupName())
                .popupType(popup.getPopupType())
                .useYn(popup.getUseYn())
                .deleteYn(popup.getUseYn()).build();

        popupRepository.save(popup);
    }

    @DeleteMapping
    public void delete(@RequestBody Popup popup){
        popupRepository.delete(popup);
    }
}
