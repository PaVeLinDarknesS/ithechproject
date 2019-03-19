package com.itech.library.repository.impl;

import com.itech.library.entity.User;
import com.itech.library.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<User> getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User as u where u.login= :login", User.class);
        query.setParameter("login", login);
        return query.uniqueResultOptional();
    }

    @Override
    public User addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user;
    }

    @Override
    public Optional<User> findOne(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User as u " +
                "where u.login = :login " +
                "and u.password = :pass", User.class);
        query.setParameter("login", user.getLogin());
        query.setParameter("pass", user.getPassword());
        List<User> foundUsers = query.list();
        Optional<User> findUserOptional = Optional.empty();
        if (foundUsers.size() == 1) {
            findUserOptional = Optional.of(foundUsers.get(0));
        }
        return findUserOptional;
    }
}
