package com.telerikacademy.addonis.repositories;

import com.telerikacademy.addonis.models.Role;
import com.telerikacademy.addonis.repositories.contracts.RoleRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends ReadOnlySQLRepository<Role> implements RoleRepository {

    private static final int ADMIN_ROLE_ID = 1;
    private static final int USER_ROLE_ID = 2;

    @Autowired
    protected RoleRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Role.class);
    }

    @Override
    public Role getAdminRole() {
        return getById(ADMIN_ROLE_ID);
    }

    @Override
    public Role getUserRole() {
        return getById(USER_ROLE_ID);
    }
}
