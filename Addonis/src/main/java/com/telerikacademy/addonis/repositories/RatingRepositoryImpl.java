package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.models.Rating;
import com.telerikacademy.addonis.repositories.contracts.RatingRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RatingRepositoryImpl extends CRUDSQLRepository<Rating> implements RatingRepository {

    protected RatingRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Rating.class);
    }
}
