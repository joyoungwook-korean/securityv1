package com.rbwsn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @GetMapping("/")
    public @ResponseBody String aaa(){
        return "Hello Main";
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

    @GetMapping("/create")
    public @ResponseBody String create(){
        return "Hello create";
    }

    @GetMapping("/createProc")
    public @ResponseBody String createProc(){
        return "create success";
    }

}
