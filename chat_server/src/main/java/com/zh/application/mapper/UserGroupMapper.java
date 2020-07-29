package com.zh.application.mapper;

import com.zh.application.pojo.UserGroup;
import com.zh.application.pojo.UserGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserGroupMapper {
    int countByExample(UserGroupExample example);

    int deleteByExample(UserGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    List<UserGroup> selectByExample(UserGroupExample example);

    UserGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserGroup record, @Param("example") UserGroupExample example);

    int updateByExample(@Param("record") UserGroup record, @Param("example") UserGroupExample example);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
}