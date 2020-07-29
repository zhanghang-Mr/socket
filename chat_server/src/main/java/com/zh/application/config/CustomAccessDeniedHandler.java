package com.zh.application.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zh.application.common.CommonConstant;
import com.zh.application.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Autowired
	private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
    	log.error("----------------------------------->权限不足401<-------------------------------------");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
        	Result<Boolean> result = new Result<Boolean>();
        	result.setCode(CommonConstant.SC_USR_LMT);
        	result.setMessage(CommonConstant.SC_USR_LMT_MSG);
            response.getWriter().write(objectMapper.writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
