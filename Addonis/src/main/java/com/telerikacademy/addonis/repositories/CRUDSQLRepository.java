package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.repositories.contracts.CRUDRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CRUDSQLRepository<E> extends ReadOnlySQLRepository<E> implements CRUDRepository<E> {

    protected CRUDSQLRepository(SessionFactory sessionFactory, Class<E> clazz) {
        super(sessionFactory, clazz);
    }

    @Override
    public void create(E entity) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(E entity) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int id) {
        E toDelete = getById(id);
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(toDelete);
            session.getTransaction().commit();
        }
    }
}
