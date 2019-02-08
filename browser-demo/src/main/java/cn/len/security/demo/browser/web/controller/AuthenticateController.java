package cn.len.security.demo.browser.web.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author len
 * @date 2019年 02月06日
 */
@Controller
public class AuthenticateController {
    @PostMapping("/authenticate/form")
    public String authenticate() {
        return "index";
    }

    public static void main(String[] args) {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String dd = "$2a$10$q3F5qvhAHCrEiD.Ka.y.kut.C/9DKqj57uVMSbYiPWwwPFrlF2tbq";
        String dd2 = "$2a$10$e3pLUgHh7Epw238GCTn36untOMbQX4p6BABvrDYVS7pplI3JbtvG2";
        System.out.println(bCryptPasswordEncoder.matches("123456", dd));
        System.out.println(bCryptPasswordEncoder.matches("123456", dd2));
    }



}
