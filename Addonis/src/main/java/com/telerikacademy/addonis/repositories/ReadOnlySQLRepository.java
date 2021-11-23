package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.exceptions.EntityNotFoundException;
import com.telerikacademy.addonis.repositories.contracts.ReadOnlyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReadOnlySQLRepository<E> implements ReadOnlyRepository<E> {

    private final SessionFactory sessionFactory;
    private final Class<E> clazz;

    protected ReadOnlySQLRepository(SessionFactory sessionFactory, Class<E> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected Class<E> getClazz() {
        return clazz;
    }

    @Override
    public E getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            E entity = session.get(clazz, id);
            if (entity == null) {
                throw new EntityNotFoundException(clazz.getSimpleName(), id);
            }
            return entity;
        }
    }

    @Override
    public List<E> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from "+clazz.getSimpleName(), clazz)
                    .list();
        }
    }

    protected List<E> executeQuery(String queryStr, Map<String, Object> parameters) {
        try (Session session = sessionFactory.openSession()) {
            Query<E> query = session.createQuery(queryStr, clazz);
            query.setProperties(parameters);
            return query.getResultList();
        }
    }

    protected Optional<E> executeQueryFindOne(String queryStr, Map<String, Object> parameters) {
        List<E> result = executeQuery(queryStr, parameters);
        if (result.isEmpty())
            return Optional.empty();
        return Optional.of(result.get(0));
    }


}
