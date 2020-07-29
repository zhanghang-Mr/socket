package com.zh.application.controller;


import com.zh.application.common.Result;
import com.zh.application.pojo.Group;
import com.zh.application.pojo.User;
import com.zh.application.pojo.UserGroup;
import com.zh.application.pojo.vo.*;
import com.zh.application.service.BaseUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户相关操作
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController|用户相关操作", tags = "user")
public class UserController {

    @Autowired
    private BaseUserService baseUserService;

    /**
     * 添加好友
     * @return
     */
    @PostMapping("/friend")
    @ApiOperation(value = "添加好友", httpMethod = "POST")
    public Result<Boolean> addFriend(@Valid  @RequestBody FriendVo bean){
        Result<Boolean> result = new Result<Boolean>();
        result.setResult(baseUserService.addFriend(bean));
        return result;
    }

    /**
     *根据用户id 获取好友
     * @param uid
     * @return
     */
    @GetMapping("/friend/{uid}")
    @ApiOperation(value = "根据用户id 获取好友", httpMethod = "GET")
    public Result<List<UserFriendVo>> getFriendBuUid(@PathVariable Long uid){
        Result<List<UserFriendVo>> result = new Result<List<UserFriendVo>>();
        result.setResult(baseUserService.getFriendByUid(uid));
        return result;
    }

    /**
     * 根据账号名查询用户
     * @param username
     * @return
     */
    @GetMapping("/{username}")
    @ApiOperation(value = "根据账号名查询用户", httpMethod = "GET")
    public Result<User> getUserByUsername(@PathVariable String username){
        Result<User> result = new Result<>();
        result.setResult(baseUserService.getUserByUsername(username));
        return result;
    }

    /**
     *根据用户id 获取被邀请信息
     * @param uid
     * @return
     */
    @GetMapping("/invite/{uid}")
    @ApiOperation(value = "根据用户id 获取被邀请信息", httpMethod = "GET")
    public Result<List<InviteMessageVo>> getInviteMessage(@PathVariable Long uid){
        Result<List<InviteMessageVo>> result = new Result<List<InviteMessageVo>>();
        result.setResult(baseUserService.getInviteMessage(uid));
        return result;
    }

    /**
     * 处理消息
     * @param bean
     * @return
     */
    @PostMapping("/invite")
    @ApiOperation(value = "处理消息", httpMethod = "POST")
    public Result<Boolean> dispose(@Valid  @RequestBody DisposeVo bean){
        Result<Boolean> result = new Result<Boolean>();
        result.setResult(baseUserService.dispose(bean));
        return result;
    }

    /**
     * 修改昵称
     * @return
     */
    @PostMapping("/nickname")
    @ApiOperation(value = "修改昵称", httpMethod = "POST")
    public Result<Boolean> updateNickname(@Valid  @RequestBody UserNicknameVo bean){
        Result<Boolean> result = new Result<>();
        result.setResult(baseUserService.updateNickname(bean));
        return result;
    }

    /**
     * 获取聊天记录
     * @return
     */
    @PostMapping("/friend/{toUid}/{goUid}")
    @ApiOperation(value = "获取聊天记录", httpMethod = "POST")
    public Result<List<MessageVo>> getFriendMessage(@PathVariable Long toUid, @PathVariable Long goUid){
        Result<List<MessageVo>> result = new Result<>();
        result.setResult(baseUserService.getFriendMessage(toUid, goUid));
        return result;
    }

    /**
     * 新建群
     * @return
     */
    @PostMapping("/group")
    public Result<Boolean> addGroup(@RequestBody Group bean){
        Result<Boolean> result = new Result<Boolean>();
        result.setResult(baseUserService.addGroup(bean));
        return result;
    }

    /**
     * 邀请入群
     * @return
     */
    @PostMapping("/group/invite")
    public Result<Boolean> addInviteGroup(@RequestBody UserGroup bean){
        Result<Boolean> result = new Result<Boolean>();
        result.setResult(baseUserService.addInviteGroup(bean));
        return result;
    }
}
