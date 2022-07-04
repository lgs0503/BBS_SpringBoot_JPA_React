package lgs.bbs.user.controller;

import lgs.bbs.comm.HttpHeaderJsonType;
import lgs.bbs.comm.HttpMessage;
import lgs.bbs.comm.UserSha256;
import lgs.bbs.user.entity.User;
import lgs.bbs.user.entity.UserRepository;
import lgs.bbs.user.entity.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final String CLASS_TYPE = "user";

    @GetMapping
    public ResponseEntity searchList(User user){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Count", userRepository.searchListCount(user));
        message.put(CLASS_TYPE + "List", userRepository.searchList(user));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/userChk")
    public ResponseEntity userChk(User user){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE + "Result", userRepository.searchListCount(user));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{id}")
    public ResponseEntity search(@PathVariable String id){
        HttpMessage message = new HttpMessage();

        User user = User.builder().id(id).build();

        message.put(CLASS_TYPE, userRepository.searchDetail(user));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(@RequestBody User user){

        user = User.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .password(user.getPassword() != null ? UserSha256.encrypt(user.getPassword()) : "")
                .name(user.getName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .gender(user.getGender())
                .phoneNum(user.getPhoneNum())
                .address(user.getAddress())
                .addressDtl(user.getAddressDtl())
                .rule(user.getRule())
                .deleteYn(user.getDeleteYn())
                .imageFileNo(user.getImageFileNo())
                .build();

        userRepository.save(user);
    }

    @DeleteMapping
    public void delete(@RequestBody String [] user){

        return;
        //userRepository.delete(user);
    }
}
