package com.example.filmproject.services;

import com.example.filmproject.models.FilmEntity;

import java.util.List;

public interface FilmServicesImpl {
    List<FilmEntity> getAll();

    List<FilmEntity> getAllByCategory(int id);

    List<FilmEntity> getAllByYear(int id);

    List<FilmEntity> getAllByName(String name);
}
