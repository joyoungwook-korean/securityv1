package com.rbwsn.config;

import com.rbwsn.auth.SecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity // Spring Security Config
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityDetailsService securityDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/user/**").authenticated() // 認証が必要な権限
                .antMatchers("/manager/**").access("hasRole('ADMIN') or hasRole('MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest().permitAll() // IF　NOT全部PERMIT
                .and()
                .formLogin().loginPage("/loginform") //loginPageで移動
                .loginProcessingUrl("/login") //springSecurity代わりに　認証します。
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password");

    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
