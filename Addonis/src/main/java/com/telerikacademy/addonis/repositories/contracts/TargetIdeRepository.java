package com.telerikacademy.addonis.repositories.contracts;

import com.telerikacademy.addonis.models.TargetIde;

public interface TargetIdeRepository extends ReadOnlyRepository<TargetIde> {

    TargetIde getByName(String name);
}
