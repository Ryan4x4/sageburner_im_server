package com.sageburner.im.server.service;

import com.sageburner.im.server.dao.UserDao;
import com.sageburner.im.server.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ryan on 10/16/2014.
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
        logger.info("User added successfully, User Details = " + user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
        logger.info("User updated successfully, User Details = " + user);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        List<User> userList = userDao.listUsers();
        for (User user: userList) {
            logger.info("User List:: " + user);
        }
        return null;
    }

    @Override
    @Transactional
    public User getUserByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        return user;
    }

    @Override
    @Transactional
    public User getUser(String id) {
        User user = userDao.getUser(id);
        if (user != null) {
            logger.info("User retrieved successfully, User details = " + user);
            return user;
        } else {
            logger.info("User not found, User details = " + user);
            return null;
        }
    }

    @Override
    @Transactional
    public void removeUser(String username) {
        userDao.removeUser(username);
        logger.info("Attempted to remove user. Username = " + username + ".  I need to add validation here!");
    }
}
