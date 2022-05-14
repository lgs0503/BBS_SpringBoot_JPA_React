package lgs.bbs.main.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    @GetMapping("/api/login")
    public String loginProcessing(@RequestBody String data){

        return "test";
    }

    @PostMapping("/register")
    public void registerProcessing(){

    }

}
