package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.Role;

public interface RoleRepository extends ReadOnlyRepository<Role>{

    Role getAdminRole();

    Role getUserRole();

}
