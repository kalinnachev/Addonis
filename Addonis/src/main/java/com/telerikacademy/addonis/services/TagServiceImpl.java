package com.telerikacademy.addonis.services;

import com.telerikacademy.addonis.models.Tag;
import com.telerikacademy.addonis.repositories.contracts.TagRepository;
import com.telerikacademy.addonis.services.contracts.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.getAll();
    }
}
