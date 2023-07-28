package com.example.demo1.dao;


import com.example.demo1.pojo.User;

public interface UserDao {
    /**
     * 根据用户查询用户信息
     * @param username 用户名
     * @return 如果返回null，说明没有这个用户，反之亦然
     */
    public User queryUserByUsername(String username);
    /**
     * 根据用户和密码查询用户信息
     * @param username 用户名
     * @return 如果返回null，说明没有这个用户，反之亦然
     */
    public User queryUserByUsernameAndPassword(String username,String password);
    /**
     * 保存用户信息
     * @param user
     * @return 如果返回-1，表示操作失败其他是sql语句影响的行数
     */
    public int saveUser(User user);
}
