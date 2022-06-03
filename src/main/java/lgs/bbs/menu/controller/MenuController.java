package lgs.bbs.menu.controller;

import lgs.bbs.menu.entity.Menu;
import lgs.bbs.menu.entity.MenuRepository;
import lgs.bbs.comm.HttpHeaderJsonType;
import lgs.bbs.comm.HttpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    private final String CLASS_TYPE = "menu";

    @GetMapping
    public ResponseEntity searchList(@RequestBody Menu menu){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Count", menuRepository.searchListCount(menu));
        message.put(CLASS_TYPE + "List", menuRepository.searchList(menu));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{idx}")
    public ResponseEntity search(@PathVariable Long idx){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE, menuRepository.findAllById(Collections.singleton(idx)));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(@RequestBody Menu menu){

        menu = Menu.builder()
                .upperMenuId(menu.getUpperMenuId())
                .boardId(menu.getBoardId())
                .url(menu.getUrl())
                .contentId(menu.getContentId())
                .menuViewPath(menu.getMenuViewPath())
                .menuName(menu.getMenuName())
                .menuType(menu.getMenuType())
                .useYn(menu.getUseYn())
                .deleteYn(menu.getUseYn()).build();

        menuRepository.save(menu);
    }

    @DeleteMapping
    public void delete(@RequestBody Menu menu){
        menuRepository.delete(menu);
    }
}
