package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.models.RepoInfo;
import com.telerikacademy.addonis.repositories.contracts.RepoInfoRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepoInfoRepositoryImpl extends CRUDSQLRepository<RepoInfo>
        implements RepoInfoRepository {

    @Autowired
    public RepoInfoRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, RepoInfo.class);
    }

}
