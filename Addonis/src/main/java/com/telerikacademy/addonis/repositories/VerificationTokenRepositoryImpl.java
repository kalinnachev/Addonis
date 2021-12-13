package com.telerikacademy.addonis.repositories;


import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.VerificationToken;
import com.telerikacademy.addonis.repositories.contracts.VerificationTokenRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class VerificationTokenRepositoryImpl extends CRUDSQLRepository<VerificationToken>
        implements VerificationTokenRepository {

    @Autowired
    protected VerificationTokenRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, VerificationToken.class);
    }

    @Override
    public VerificationToken findByTokenValue(String value) {
        return executeQueryFindOne("from VerificationToken where name = :name", Map.of("name", value))
                .orElseThrow(() -> new EntityNotFoundException("token", "value", value));
    }
}
