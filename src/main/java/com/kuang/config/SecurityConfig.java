package com.kuang.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/assets/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("userCode")
                .passwordParameter("userPassword")
                .successHandler((request, response, authentication) -> {
                    System.out.println("login success");

                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    com.kuang.pojo.User user = (com.kuang.pojo.User) authentication.getPrincipal();
                    HashMap result = new HashMap<>();
                    result.put("status", "success");
                    result.put("user", user);
                    writer.write(JSONObject.toJSONString(result));
                    writer.flush();
                    writer.close();
                })
                .failureHandler((request, response, exception) -> {
                    System.out.println("login failur");

                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    HashMap result = new HashMap<>();
                    result.put("status", "failur");
                    writer.write(JSONObject.toJSONString(result));
                    writer.flush();
                    writer.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    System.out.println("logout success");

                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    HashMap result = new HashMap<>();
                    result.put("status", "success");
                    writer.write(JSONObject.toJSONString(result));
                    writer.flush();
                    writer.close();
                })
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable();
    }
}
