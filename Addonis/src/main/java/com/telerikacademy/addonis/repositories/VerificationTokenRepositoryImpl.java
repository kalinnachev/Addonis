package com.telerikacademy.addonis.repositories;


import com.telerikacademy.addonis.models.VerificationToken;
import com.telerikacademy.addonis.repositories.contracts.VerificationTokenRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class VerificationTokenRepositoryImpl extends CRUDSQLRepository<VerificationToken>
        implements VerificationTokenRepository {

    @Autowired
    protected VerificationTokenRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, VerificationToken.class);
    }
}
