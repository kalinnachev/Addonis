package com.telerikacademy.addonis.services.contracts;

import com.telerikacademy.addonis.models.TargetIde;

import java.util.List;

public interface TargetIdeService {

    TargetIde getByName(String name);

    TargetIde getById(int id);

    List<TargetIde> getAll();

}
