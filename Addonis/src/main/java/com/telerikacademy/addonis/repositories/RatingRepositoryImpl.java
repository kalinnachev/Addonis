package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.models.User;
import com.telerikacademy.addonis.repositories.contracts.RatingRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import static java.lang.String.format;

@Repository
public class RatingRepositoryImpl extends CRUDSQLRepository<Rating> implements RatingRepository {

    protected RatingRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Rating.class);
    }

    @Override
    public Rating getByUserAndAddon(Addon addon, User user) {
        String query = format("from %s where %s = :addon and %s = :user", getClazz().getSimpleName(), "addon", "user");
        try (Session session = getSessionFactory().openSession()) {
            return session
                    .createQuery(query, getClazz())
                    .setParameter("addon", addon)
                    .setParameter("user",user)
                    .uniqueResultOptional()
                    .orElseThrow(() -> new EntityNotFoundException("You have not yet rated that addon"));
        }
    }
}
