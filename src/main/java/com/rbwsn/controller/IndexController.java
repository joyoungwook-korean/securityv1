package com.rbwsn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "Hello admin";
    }
    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "Hello manager";
    }
    @GetMapping("/user")
    public @ResponseBody String user(){
        return "Hello user";
    }

    @GetMapping("/login")
    public String login(){
        return "loginform";
    }

    @GetMapping("/loginform")
    public String loginForm(){
        return "loginform";
    }

}
