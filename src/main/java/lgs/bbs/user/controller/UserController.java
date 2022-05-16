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

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final String CLASS_TYPE = "user";

    @GetMapping
    public ResponseEntity searchList(@RequestBody User user){
        HttpMessage message = new HttpMessage();
        Specification<User> spec = (root, query, criteriaBuilder) -> null;

        /* 조건 조회 */
        if(user.getId() != null) {
            spec = spec.and(UserSpecification.equalId(user.getId()));
        }
        if(user.getName() != null) {
            spec = spec.and(UserSpecification.likeName(user.getName()));
        }

        message.put(CLASS_TYPE + "Count", userRepository.count(spec));
        message.put(CLASS_TYPE + "List", userRepository.findAll(spec));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/userChk")
    public ResponseEntity userChk(@RequestBody User user){
        HttpMessage message = new HttpMessage();

        String id = user.getId();
        String password = "";

        Specification<User> spec = (root, query, criteriaBuilder) -> null;

        spec = spec.and(UserSpecification.equalId(id));

        if(user.getPassword() != null){ /* 로그인 */
            password = UserSha256.encrypt(user.getPassword());
            spec = spec.and(UserSpecification.equalPassword(password));
        }

        message.put(CLASS_TYPE + "Result", userRepository.count(spec));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @GetMapping("/{idx}")
    public ResponseEntity search(@PathVariable Long idx){
        HttpMessage message = new HttpMessage();

        message.put(CLASS_TYPE, userRepository.findAllById(Collections.singleton(idx)));

        return ResponseEntity.ok()
                .headers(HttpHeaderJsonType.getHeader())
                .body(message.getMessage());
    }

    @PostMapping
    public void save(@RequestBody User user){

        user = User.builder()
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
                .build();

        userRepository.save(user);
    }

    @DeleteMapping
    public void delete(@RequestBody User user){
        userRepository.delete(user);
    }
}
