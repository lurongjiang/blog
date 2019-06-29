package org.lrj.blog.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Select(value = "SELECT * FROM user WHERE username=#{username}")
    Map<String, String> login(String username);

    @Select(value = "SELECT * FROM user")
    List<Map<String,String>> list();
}
