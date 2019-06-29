package org.lrj.blog.controller;

import org.lrj.blog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Resource
    private UserService userService;
    @GetMapping("/loginPage")
    public Object login() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "403");
        map.put("message", "no login");
        return map;
    }

    @GetMapping("getCurrentUser")
    public Object getCurrentUser() {
        return userService.list();
    }
}
