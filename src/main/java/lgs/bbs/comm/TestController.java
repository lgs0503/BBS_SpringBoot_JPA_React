package lgs.bbs.comm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @RequestMapping("/")
    public String TestView(){
        return "index";
    }

}
