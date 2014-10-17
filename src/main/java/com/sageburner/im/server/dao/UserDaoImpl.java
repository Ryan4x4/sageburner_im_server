package com.sageburner.im.server.dao;

import com.sageburner.im.server.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Ryan on 10/16/2014.
 */
@SuppressWarnings("ALL")
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User added successfully, User Details = " + user);

        return null;
    }

    @Override
    public Void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User updated successfully, User Details = " + user);

        return null;
    }

    @Override
    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("from USER").list();
        for(User user : userList){
            logger.info("User List:: " + user);
        }
        return userList;
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, username);
        logger.info("User retrieved successfully, User details = " + user);
        return user;
    }

    @Override
    public Void removeUser(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, username);
        if(user != null){
            session.delete(user);
            logger.info("User removed successfully, Username = " + username);
        }
        logger.info("User could not be removed because it was not found. Username = " + username);

        return null;
    }
}
