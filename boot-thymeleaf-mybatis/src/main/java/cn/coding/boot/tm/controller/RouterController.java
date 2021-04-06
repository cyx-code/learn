package cn.coding.boot.tm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouterController {
    @GetMapping("/")
    public String index() {
        return "user/user";
    }
    @GetMapping("/user")
    public String user() {
        return "user/user";
    }
}
