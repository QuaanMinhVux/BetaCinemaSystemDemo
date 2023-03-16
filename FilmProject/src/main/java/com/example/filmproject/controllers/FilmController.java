package com.example.filmproject.controllers;

import com.example.filmproject.models.FilmEntity;
import com.example.filmproject.services.FilmServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {
    @Autowired
    private FilmServices services;

    @GetMapping("list")
    public List<FilmEntity> index() {
        return services.getAll();


    }
    @GetMapping("/listByCategory/{id}")
    public List<FilmEntity> filterByCategory(@PathVariable int id) {
        return services.getAllByCategory(id);


    }
    @GetMapping("/listByYear/{year}")
    public List<FilmEntity> filterByYear(@PathVariable int year) {
        return services.getAllByYear(year);


    }
    @GetMapping("/listByName/{name}")
    public List<FilmEntity> filterByYear(@PathVariable String name) {
        return services.getAllByName(name);


    }
}
