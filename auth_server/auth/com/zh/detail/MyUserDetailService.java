package com.zh.detail;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        //将来连接数据库根据账号查询用户信息
//        UserDto userDto = userDao.getUserByUsername(username);
//        if(userDto == null){
//            //如果用户查不到，返回null，由provider来抛出异常
//            return null;
//        }
//        //根据用户的id查询用户的权限
//        List<String> permissions = userDao.findPermissionsByUserId(userDto.getId());
//        //将permissions转成数组
//        String[] permissionArray = new String[permissions.size()];
//        permissions.toArray(permissionArray);
//        //将userDto转成json
//        String principal = JSON.toJSONString(userDto);
        UserDetails userDetails = User.withUsername("zhangsan").password("$2a$10$qY/tMDTu7oOmnmFHtG07Gue8zxVgzdajQ3pnu98xDgZfnkbheBmTu").authorities("p1").build();
        return userDetails;
    }

}
