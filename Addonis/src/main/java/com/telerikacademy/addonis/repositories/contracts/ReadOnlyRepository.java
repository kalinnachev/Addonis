package com.telerikacademy.addonis.repositories.contracts;

import java.util.List;

public interface ReadOnlyRepository<E> {

    E getById(int id);

    List<E> getAll();
}
