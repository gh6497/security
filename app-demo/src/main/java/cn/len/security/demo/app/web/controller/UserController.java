package cn.len.security.demo.app.web.controller;

import cn.len.security.demo.app.extension.StringExtension;
import cn.len.security.demo.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author len
 * @date 2019年 02月06日
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public void me(HttpServletResponse response, HttpServletRequest request) {

    }
}
