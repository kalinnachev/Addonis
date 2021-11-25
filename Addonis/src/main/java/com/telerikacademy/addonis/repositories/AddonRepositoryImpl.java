package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.models.Addon;
import com.telerikacademy.addonis.repositories.contracts.AddonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    //TODO ask about tinyint
    @Override
    public List<Addon> getFeatured() {
        String query = format("from %s where %s = :value", getClazz().getSimpleName(), "featured");
        try(Session session = getSessionFactory().openSession()){
            return session
                    .createQuery(query, getClazz())
                    .setParameter("value", 1)
                    .list();
        }
    }

    @Override
    public List<Addon> getNewest() {
        String query = format("from %s order by %s desc limit 5",getClazz().getSimpleName(),"creationDate");
        try(Session session = getSessionFactory().openSession()){
            return session
                    .createQuery(query, getClazz())
                    .list();
        }
    }
}
