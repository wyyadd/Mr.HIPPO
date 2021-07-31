package com.hippomanager.backend.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String Gologin(){
        return "/index/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        HttpServletRequest request,
                        Model model){
        HttpSession session = request.getSession();
        if(username.equals("admin") && password.equals("2021Hippo")){
            session.setAttribute("admin", "authorized");
            return "redirect:/";
        }
        else {
            model.addAttribute("msg", "账号或密码错误");
            return "/index/login.html";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index/login.html";
    }

}
