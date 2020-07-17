package ru.itis.javalab.rmrteam.theworkers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.rmrteam.theworkers.entities.Tag;
import ru.itis.javalab.rmrteam.theworkers.repositories.TagsRepository;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    TagsRepository tagsRepository;

    @Override
    public List<Tag> getTags() {
        return tagsRepository.findAll();
    }
}
