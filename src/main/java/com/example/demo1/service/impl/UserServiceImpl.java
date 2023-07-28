package com.example.demo1.service.impl;


import com.example.demo1.dao.Impl.UserDaoImpl;
import com.example.demo1.dao.UserDao;
import com.example.demo1.pojo.User;
import com.example.demo1.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryUserByUsername(username)==null){
            return false;
        }
        return true;
    }
}
