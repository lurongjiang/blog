package org.lrj.blog.config;

import org.lrj.blog.handler.AuthenticationAccessDeniedHandler;
import org.lrj.blog.handler.LoginFailHandler;
import org.lrj.blog.handler.LoginSuccessHandler;
import org.lrj.blog.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomUserDetailService customUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new AuthenticationAccessDeniedHandler();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html","/static/**","/css/**","/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/**").hasRole("superAdmin")
                .antMatchers("/frontend/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/index.html").usernameParameter("username").passwordParameter("password").successHandler(new LoginSuccessHandler()).failureHandler(new LoginFailHandler()).permitAll()
                .loginProcessingUrl("/login").and().logout().permitAll().and().csrf().disable().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

}
