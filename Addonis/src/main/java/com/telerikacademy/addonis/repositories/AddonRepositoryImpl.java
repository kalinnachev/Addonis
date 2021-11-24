package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static java.lang.String.format;

@Repository
public class AddonRepositoryImpl extends CRUDSQLRepository<Addon> implements AddonRepository {

    @Autowired
    protected AddonRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Addon.class);
    }

    @Override
    public Addon findByOriginUrl(String originUrl) {
        String query = format("from %s where %s = :value", getClazz().getSimpleName(), "originUrl");
        try(Session session = getSessionFactory().openSession()){
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", originUrl)
                    .uniqueResultOptional()
                    .orElseThrow(()-> new EntityNotFoundException("Addon", "originUrl", originUrl));
        }
    }
}
