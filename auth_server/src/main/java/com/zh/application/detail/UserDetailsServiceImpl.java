package com.zh.application.detail;

import com.zh.application.mapper.UserMapperExt;
import com.zh.application.pojo.User;
import com.zh.application.pojo.vo.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

  @Service 
  public class UserDetailsServiceImpl implements UserDetailsService {
  
  
  @Autowired 
  private UserMapperExt userMapperExt;

// 实现UserDetailsService中的loadUserByUsername方法，用于加载用户数据


		  @Override 
		  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
			  System.out.println("===============开始查询数据库============");
			  User user = userMapperExt.queryUserByUsername(username);
			  if (user == null) {
				  throw new UsernameNotFoundException("用户不存在");
				}
			  System.out.println("===============user:============"+JSON.toJSONString(user));
		  //用户权限列表 Collection<? extends GrantedAuthority> 
//			  authorities = userService.queryUserAuthorities(user.getId());
		  
		  return new JwtUser( user.getId(),user.getUsername(), user.getPassword());
		  }
		  
		  }
