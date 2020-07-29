package com.zh.application.service.impl;

import com.zh.application.mapper.UserMapper;
import com.zh.application.pojo.User;
import com.zh.application.pojo.UserExample;
import com.zh.application.pojo.vo.UserRegisterVo;
import com.zh.application.service.IndexUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class IndexUserServiceImpl implements IndexUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean userRegister(UserRegisterVo bean) {
        //先判断该账号在数据库中有没有、
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andUsernameEqualTo(bean.getUsername());
        if(!userMapper.selectByExample(userExample).isEmpty()){
            //表示该账号已存在
            return false;
        }
        User user = new User();
        user.setUsername(bean.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(bean.getPassword()));
        userMapper.insertSelective(user);
        return true;
    }
}
