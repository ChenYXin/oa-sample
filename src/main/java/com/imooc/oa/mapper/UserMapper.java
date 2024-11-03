package com.imooc.oa.mapper;

import com.imooc.oa.entity.User;
import com.imooc.oa.utils.MyBatisUtils;

public class UserMapper {
    public User selectByUsername(String username) {
        User user = (User) MyBatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("usermapper.selectByUsername", username));
        return user;
    }
}
