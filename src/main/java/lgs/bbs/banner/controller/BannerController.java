package lgs.bbs.banner.controller;

import lgs.bbs.banner.entity.Banner;
import lgs.bbs.banner.entity.BannerRepository;
import lgs.bbs.comm.HttpHeaderJsonType;
import lgs.bbs.comm.HttpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping(value = "/banner")
public class BannerController {

    @Autowired
    private BannerRepository bannerRepository;

    private final String CLASS_TYPE = "Banner";

    @GetMapping
    public ResponseEntity searchList(@RequestBody Banner banner){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Count", bannerRepository.searchListCount(banner));
        message.put(CLASS_TYPE + "List", bannerRepository.searchList(banner));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{idx}")
    public ResponseEntity search(@PathVariable Long idx){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE, bannerRepository.findAllById(Collections.singleton(idx)));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(@RequestBody Banner banner){

        banner = Banner.builder()
                .bannerName(banner.getBannerName())
                .bannerType(banner.getBannerType())
                .fileNo(banner.getFileNo())
                .html(banner.getHtml())
                .useYn(banner.getUseYn())
                .deleteYn(banner.getUseYn()).build();

        bannerRepository.save(banner);
    }

    @DeleteMapping
    public void delete(@RequestBody Banner banner){
        bannerRepository.delete(banner);
    }
}
