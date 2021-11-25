package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    @Override
    public User findByTelephone(String phoneNumber) {
        String query = format("from %s where %s = :value", getClazz().getSimpleName(), "phoneNumber");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", phoneNumber)
                    .uniqueResultOptional()
                    .orElseThrow(() -> new EntityNotFoundException("User", "phoneNumber", phoneNumber));
        }
    }
    @Override
    public List<User> search(Optional<String> username, Optional<String> email, Optional<String> phoneNumber) {
        try (Session session = getSessionFactory().openSession()) {
            var queryString = new StringBuilder(" from User ");
            var search = new ArrayList<String>();
            var params = new HashMap<String, Object>();

            username.ifPresent(value -> {
                search.add("username like :username");
                params.put("username", "%" + username.get() + "%");
            });

            email.ifPresent(value -> {
                search.add("email like :email");
                params.put("email", "%" + email.get() + "%");
            });

            phoneNumber.ifPresent(value -> {
                search.add("phoneNumber like :phoneNumber");
                params.put("phoneNumber", "%" + phoneNumber.get() + "%");
            });

            if(!search.isEmpty()) {
                queryString.append(" where ").append(String.join(" or ", search));
            }
            System.out.println(queryString);
            Query<User> query = session.createQuery(queryString.toString(), User.class);
            query.setProperties(params);
            return query.list();
        }
    }
}
