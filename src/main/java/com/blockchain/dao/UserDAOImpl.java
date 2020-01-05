package com.blockchain.dao;

import com.blockchain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        this.entityManager.persist(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return this.entityManager.createNativeQuery("select * from users").getResultList();
    }

    @Override
    public void deleteUser(Integer UserId) {
        User employee = entityManager.getReference(User.class, UserId);
        if (null != employee) {
            this.entityManager.remove(employee);
        }
    }
}