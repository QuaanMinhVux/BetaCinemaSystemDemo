package com.betacinema.demo.service;

import com.betacinema.demo.entity.Film;
import com.betacinema.demo.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService implements IFilmService{
    @Autowired
    FilmRepository filmRepository;
    @Override
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @Override
    public List<Film> getAllSearch(FilmSearch film) {
        return filmRepository.getFilmSearch(film.getName(),film.getYear(),film.getCategory());
    }

    @Override
    public Film getByID(int id) {
        return filmRepository.find(id);
    }
}
