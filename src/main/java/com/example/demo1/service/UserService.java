package com.example.demo1.service;


import com.example.demo1.pojo.User;

public interface UserService {
    /*
    * 注册用户
    * @param user
    * */
    public void registerUser(User user);
    /*
     * 登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值说明登录成功
     * */
    public User login(User user);
    /*
    * 检查用户名是否可用
    * @param username
    * @return 返回true 表示用户已经存在 返回false表示用户名可用
    * */
    public boolean existUsername(String username);
}
