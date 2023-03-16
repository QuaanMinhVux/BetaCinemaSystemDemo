package com.example.filmproject.repositories;

import com.example.filmproject.models.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FilmRepository  extends JpaRepository<FilmEntity,Long> {

    @Query(value="select f from FilmEntity f,FilmCategoryEntity fm where fm.filmId = f.filmId and f.filmId = ?1")
    List<FilmEntity> getAllByCategory(int id);

    @Query(value="select f from FilmEntity f where year(f.realeaseYear) = ?1")
    List<FilmEntity> getAllByYear(int year);

    @Query(value="select f from FilmEntity f where f.filmName like ?1")
    List<FilmEntity> getAllByName(String name);
}
