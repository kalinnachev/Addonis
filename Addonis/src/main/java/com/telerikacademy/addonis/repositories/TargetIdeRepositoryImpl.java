package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.TargetIde;
import com.telerikacademy.addonis.repositories.contracts.TargetIdeRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TargetIdeRepositoryImpl extends ReadOnlySQLRepository<TargetIde> implements TargetIdeRepository {

    @Autowired
    protected TargetIdeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, TargetIde.class);
    }

    @Override
    public TargetIde getByName(String name) {
        return executeQueryFindOne("from TargetIde where name = :name", Map.of("name", name))
                .orElseThrow(() -> new EntityNotFoundException("TargetIde", "name", name));
    }

}
