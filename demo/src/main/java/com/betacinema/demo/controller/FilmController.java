package com.betacinema.demo.controller;

import com.betacinema.demo.service.FilmSearch;
import com.betacinema.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FilmController {
    @Autowired
    FilmService service;
    @GetMapping("/list")
    public ModelAndView getList(){
        ModelAndView mv = new ModelAndView("search.html");
        mv.addObject("listFilm",service.getAll());
        mv.addObject("filmSearch",new FilmSearch());

        return mv;
    }

    @PostMapping("/list")
    public ModelAndView getList(@ModelAttribute FilmSearch film){
        ModelAndView mv = new ModelAndView("search.html");
        if(film.getName() != null && !film.getName().equals("")){
            mv.addObject("listFilm",service.getAllSearch(film));
            mv.addObject("filmSearch",new FilmSearch());
            mv.addObject("filmSearch",film);
        }
        return mv;
    }
    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("film-detail.html");

        mv.addObject("film",service.getByID(id));

        return mv;
    }
    @GetMapping("/watch/{id}")
    public ModelAndView watch(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("watch-film.html");

        mv.addObject("film",service.getByID(id));

        return mv;
    }



}
