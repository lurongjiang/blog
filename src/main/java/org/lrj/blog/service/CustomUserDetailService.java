package org.lrj.blog.service;

import org.lrj.blog.dao.UserMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, String> map = userMapper.login(username);
        if(map!=null){
            String name = map.get("username");
            String password=map.get("password");
            return new User(name,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        }
        return null;
    }
}
