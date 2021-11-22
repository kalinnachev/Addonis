package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static java.lang.String.format;

@Repository
public class UserRepositoryImpl extends CRUDSQLRepository<User> implements UserRepository {

    @Autowired
    protected UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    @Override
    public User findByUsername(String username) {
        String query = format("from %s where %s = :value", getClazz().getSimpleName(), "username");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", username)
                    .uniqueResultOptional()
                    .orElseThrow(() -> new EntityNotFoundException("User", "username", username));
        }
    }

    @Override
    public User findByEmail(String email) {
        String query = format("from %s where %s = :value", getClazz().getSimpleName(), "email");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", email)
                    .uniqueResultOptional()
                    .orElseThrow(() -> new EntityNotFoundException("User", "email", email));
        }
    }
}
