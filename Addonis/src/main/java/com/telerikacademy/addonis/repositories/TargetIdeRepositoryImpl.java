package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.models.TargetIde;
import com.telerikacademy.addonis.repositories.contracts.TargetIdeRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TargetIdeRepositoryImpl extends ReadOnlySQLRepository<TargetIde> implements TargetIdeRepository {

    @Autowired
    protected TargetIdeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, TargetIde.class);
    }


}
