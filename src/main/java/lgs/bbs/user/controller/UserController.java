package lgs.bbs.user.controller;

import lgs.bbs.user.entity.User;
import lgs.bbs.user.entity.UserRepository;
import lgs.bbs.user.entity.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> searchList(@RequestBody User user){
        Specification<User> spec = (root, query, criteriaBuilder) -> null;

        /* 조건 조회 */
        if(user.getId() != null) {
            spec = spec.and(UserSpecification.equalId(user.getId()));
        }
        if(user.getName() != null) {
            spec = spec.and(UserSpecification.likeName(user.getName()));
        }

        return userRepository.findAll(spec);
    }

    @GetMapping("/{userId}")
    public List<User> search(@PathVariable Long userId){
        return userRepository.findAllById(Collections.singleton(userId));
    }

    @PostMapping
    public void save(@RequestBody User user){
        userRepository.save(user);
    }

    @DeleteMapping
    public void delete(@RequestBody User user){
        userRepository.delete(user);
    }
}
