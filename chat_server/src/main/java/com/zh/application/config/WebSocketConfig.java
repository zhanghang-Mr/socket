package com.zh.application.config;


import com.zh.application.mapper.UserFriendMapperExt;
import com.zh.application.mapper.UserMapper;
import com.zh.application.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * 开启websocket支持
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 因为在WebSocketServer 类中 无法自动注入userMapper，
     * 所以在当前配置类 注入
     * @param userMapper
     */
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketServer.userMapper = userMapper;
    }

    @Autowired
    public void setUserFriendMapperExt(UserFriendMapperExt userFriendMapperExt){
        WebSocketServer.userFriendMapperExt = userFriendMapperExt;
    }

    @Autowired
    public void setBaseUserService(BaseUserService baseUserService){
        WebSocketServer.baseUserService = baseUserService;
    }
}
