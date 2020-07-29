package com.zh.application.mapper;

import com.zh.application.pojo.User;
import com.zh.application.pojo.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapperExt {
    User queryUserByUsername(String username);
}