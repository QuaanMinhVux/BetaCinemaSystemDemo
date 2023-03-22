package com.betacinema.demo.service;

import com.betacinema.demo.entity.Film;

import java.util.List;

public interface IFilmService {
    public List<Film> getAll();

    public List<Film> getAllSearch(FilmSearch film);

    public Film getByID(int id);
}
