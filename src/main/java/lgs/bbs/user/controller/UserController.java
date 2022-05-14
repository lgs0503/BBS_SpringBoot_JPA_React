package lgs.bbs.user.controller;

import lgs.bbs.user.entity.User;
import lgs.bbs.user.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> searchList(){
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public List<User> searchList(@PathVariable Long userId){
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
