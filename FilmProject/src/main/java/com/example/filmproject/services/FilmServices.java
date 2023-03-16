package com.example.filmproject.services;

import com.example.filmproject.models.FilmCategoryEntity;
import com.example.filmproject.models.FilmEntity;
import com.example.filmproject.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FilmServices implements FilmServicesImpl {
    @Autowired
    private FilmRepository repository;

    @Override
    public List<FilmEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<FilmEntity> getAllByCategory(int id) {
        return repository.getAllByCategory(id);
    }

    @Override
    public List<FilmEntity> getAllByYear(int year) {
        return repository.getAllByYear(year);
    }

    @Override
    public List<FilmEntity> getAllByName(String name) {
        return repository.getAllByName(name);
    }
}
