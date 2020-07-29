package com.zh.application.service;

import com.zh.application.pojo.vo.UserRegisterVo;

public interface IndexUserService {

    /**
     * 用户注册
     * @return
     */
    Boolean userRegister(UserRegisterVo bean);
}
