package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddonRepositoryImpl extends CRUDSQLRepository<Addon> implements AddonRepository {

    @Autowired
    protected AddonRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Addon.class);
    }
}
