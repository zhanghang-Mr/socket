package com.zh.application.controller;


import com.zh.application.common.Result;
import com.zh.application.mapper.UserMapper;
import com.zh.application.pojo.User;
import com.zh.application.pojo.UserExample;
import com.zh.application.pojo.vo.UserRegisterVo;
import com.zh.application.service.IndexUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

/**
 * 用户相关操作
 */
@RestController
@Api(value = "UserController|用户相关操作", tags = "user")
public class UserController {

    @Autowired
    private IndexUserService indexUserService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册用户
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册用户", httpMethod = "POST")
    public Result<Boolean> addUser(@Valid @RequestBody  UserRegisterVo bean){
        Result<Boolean> result = new Result<Boolean>();
//        result.setResult(indexUserService.userRegister(bean));
        //先判断该账号在数据库中有没有、
        UserExample userExample = new UserExample();
        UserExample.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andUsernameEqualTo(bean.getUsername());
        if(!userMapper.selectByExample(userExample).isEmpty()){
            //表示该账号已存在
            result.setResult(false);
        }else{
            User user = new User();
            user.setUsername(bean.getUsername());
            user.setNickname(bean.getNickname());
            user.setPassword(new BCryptPasswordEncoder().encode(bean.getPassword()));
            userMapper.insertSelective(user);
            result.setResult(true);
        }
        return result;
    }

    @GetMapping("/user/info")
    public Principal user(Principal user){
        return user;
    }
}
