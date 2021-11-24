package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.models.Tag;
import com.telerikacademy.addonis.repositories.contracts.TagRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepositoryImpl extends CRUDSQLRepository<Tag> implements TagRepository {
    protected TagRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Tag.class);
    }
}
