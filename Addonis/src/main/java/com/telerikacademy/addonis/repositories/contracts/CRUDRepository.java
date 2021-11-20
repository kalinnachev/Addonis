package com.telerikacademy.addonis.repositories.contracts;

public interface CRUDRepository<E> extends ReadOnlyRepository<E> {

    void create(E entity);

    void update(E entity);

    void delete(int id);

}
