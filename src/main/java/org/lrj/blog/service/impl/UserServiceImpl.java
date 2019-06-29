package org.lrj.blog.service.impl;

import org.lrj.blog.dao.UserMapper;
import org.lrj.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public Object list() {
        return userMapper.list();
    }
}
