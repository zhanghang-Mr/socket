//package com.zh.application.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//*
// * @author Administrator
// * @version 1.0
// *
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    //认证管理器
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    //密码编码器
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    //先使用内存中创建用户
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("123456")).roles("USER");
//    }
//
//    //密码模式才需要配置,认证管理器
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//
//
//    //安全拦截机制（最重要）
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
////                .antMatchers("/r/r1").hasAuthority("p2")
////                .antMatchers("/r/r2").hasAuthority("p2")
//                .antMatchers("/r/**").authenticated()//所有/r/**的请求必须认证通过
//                .anyRequest().permitAll()//除了/r/**，其它的请求可以访问
//                .and()
//                .formLogin()//允许表单登录
//                .loginPage("/login-view")//登录页面
//                .loginProcessingUrl("/login")
//                .successForwardUrl("/login-success")//自定义登录成功的页面地址
//        .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login-view?logout");
//
//
//    }
//
//    //安全拦截机制（最重要）
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests()
////                .antMatchers("/r/r1").hasAnyAuthority("p1")
////                .antMatchers("/login*").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////        ;
////
////    }
//}
