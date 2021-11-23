package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.models.TargetIde;
import com.telerikacademy.addonis.repositories.contracts.TargetIdeRepository;
import com.telerikacademy.addonis.services.contracts.TargetIdeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetIdeServiceImpl implements TargetIdeService {

    private final TargetIdeRepository targetIdeRepository;

    @Autowired
    public TargetIdeServiceImpl(TargetIdeRepository targetIdeRepository) {
        this.targetIdeRepository = targetIdeRepository;
    }

    @Override
    public TargetIde getByName(String name) {
        return targetIdeRepository.getByName(name);
    }

    @Override
    public TargetIde getById(int id) {
        return targetIdeRepository.getById(id);
    }

    @Override
    public List<TargetIde> getAll() {
        return targetIdeRepository.getAll();
    }
}
