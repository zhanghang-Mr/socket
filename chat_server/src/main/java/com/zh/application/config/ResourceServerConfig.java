package com.zh.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.servlet.http.HttpServletResponse;

@Configuration

//开启oauth2,reousrce server模式
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

/*    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomAuthExceptionEntryPoint customAuthExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
//        registry.antMatchers("/info/user/copmany/**").hasAuthority("USER");
        //registry.antMatchers("/user/info").permitAll();

        registry.antMatchers("chat/*").permitAll();

        //任何连接都放行
//        registry.anyRequest().authenticated();
//        registry.antMatchers("test/**").authenticated();

        registry.anyRequest().permitAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        //resources.tokenExtractor(customTokenExtractor);
        resources.authenticationEntryPoint(customAuthExceptionEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler);
    }*/



    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                //设置我这个resource的id, 这个在auth中配置, 这里必须照抄
                .resourceId("resource1")
                .tokenStore(tokenStore)

                //这个貌似是配置要不要把token信息记录在session中
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/test/*","/chat/**").authenticated().and() //不需要授权认证                    .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .httpBasic();


//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
//                .authorizeRequests();
//        registry.antMatchers("user/*").permitAll();
        //registry.antMatchers("/user/info").permitAll();

        //任何连接都放行
//        registry.anyRequest().authenticated();

//
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("ws/**","chat/**").permitAll();
//                .anyRequest().authenticated();
//
//                本项目所需要的授权范围,这个scope是写在auth服务的配置里的
//                .antMatchers("/**").access("#oauth2.hasScope('scope1')");
//
//                .and()
//
//
//                //这个貌似是配置要不要把token信息记录在session中
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
