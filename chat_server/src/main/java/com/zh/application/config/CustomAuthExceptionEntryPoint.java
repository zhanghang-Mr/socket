package com.zh.application.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.application.common.CommonConstant;
import com.zh.application.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthExceptionEntryPoint implements AuthenticationEntryPoint{

	@Autowired
	private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException, JsonProcessingException, IOException {
        Throwable cause = authException.getCause();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        Result<Boolean> result = new Result<Boolean>();
            if(cause instanceof InvalidTokenException) {
            	result.setCode(CommonConstant.TOKEN_MISTAKE);
            	result.setMessage(CommonConstant.TOKEN_MISTAKE_MSG);
                response.getWriter().write(objectMapper.writeValueAsString(result));
            }else{
            	result.setCode(CommonConstant.TOKEN_MISTAKE);
            	result.setMessage(CommonConstant.TOKEN_MISTAKE_MSG);
                response.getWriter().write(objectMapper.writeValueAsString(result));
            }
    }
}
