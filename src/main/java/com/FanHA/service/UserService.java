package com.FanHA.service;

import com.FanHA.pojo.User;

/**
 * @author HeTao
 * @data 2023/4/8
 **/
public interface UserService {
    /**
     * 登录功能,通过用户名密码登录
     * @param user 封装用户名和密码
     * @return 返回对象如果对象存在
     */
    User select(User user);

    /**
     * 注册功能,通过用户名密码注册
     * @param user 封装用户名和密码
     */
    void add(User user);
}
