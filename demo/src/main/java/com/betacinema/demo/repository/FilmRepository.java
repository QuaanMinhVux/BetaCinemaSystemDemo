package com.betacinema.demo.repository;

import com.betacinema.demo.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Integer> {

    @Query(value = "  select x.FilmID,x.FilmName,x.Descripton,x.Manufacturer,x.Director,x.RealeaseYear,x.VIP,x.img_url,x.mov_url,x.trailer_url from Film x,FilmCategory fc,Category c where x.FilmName like ?1 and YEAR(x.RealeaseYear) = ?2 and x.FilmID = fc.FilmID and fc.CategoryID = c.CategoryID and c.CategoryName like ?3",nativeQuery = true)
    List<Film> getFilmSearch(String name,int year,String category);

    @Query(value = "  select * from Film x where x.FilmID = ?1 ",nativeQuery = true)
    Film find(int id);
}
