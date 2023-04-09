package com.FanHA.service.impl;

import com.FanHA.mapper.UserMapper;
import com.FanHA.pojo.User;
import com.FanHA.service.UserService;
import com.FanHA.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author HeTao
 * @data 2023/4/8
 **/
public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public User select(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user1 = mapper.select(user.getUsername(), user.getPassword());

        sqlSession.close();
        return user1;
    }

    @Override
    public void add(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);


        mapper.add(user);

        sqlSession.commit();
        sqlSession.close();
    }
}
