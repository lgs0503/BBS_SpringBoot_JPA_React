package lgs.bbs.comm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value ="/linuxCommander/{commande}")
    public String linuxCommander(){
        return LinuxCommander.exec("pwd");
    }

}
