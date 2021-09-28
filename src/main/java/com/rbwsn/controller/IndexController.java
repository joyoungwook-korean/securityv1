package com.rbwsn.controller;

import com.rbwsn.dto.UserFormDto;
import com.rbwsn.entity.User;
import com.rbwsn.repository.UserRepository;
import com.rbwsn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
public class IndexController {



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;



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

    @GetMapping("/createuser")
    public String createUser(){
        return "createuser";
    }

    @GetMapping("/loginform")
    public String loginForm(){
        return "loginform";
    }



    @GetMapping("/joinform")
    public String join(Model model){
        model.addAttribute("userFormDto",new UserFormDto());
        return "/joinform";
    }

    @PostMapping("/joinform")
    public String join(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/joinform";
        }
        try{
            User user = User.createUser(userFormDto, passwordEncoder);
            userService.saveUser(user);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/joinform";
        }

        return "redirect:/";
    }
}
