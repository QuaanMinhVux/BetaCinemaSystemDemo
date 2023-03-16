package com.example.filmproject.models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Film", schema = "dbo", catalog = "movie_webdb")
public class FilmEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "FilmID")
    private int filmId;
    @Basic
    @Column(name = "FilmName")
    private String filmName;
    @Basic
    @Column(name = "Descripton")
    private String descripton;
    @Basic
    @Column(name = "Manufacturer")
    private String manufacturer;
    @Basic
    @Column(name = "Director")
    private String director;
    @Basic
    @Column(name = "RealeaseYear")
    private Date realeaseYear;
    @Basic
    @Column(name = "VIP")
    private boolean vip;
    @Basic
    @Column(name = "img_url")
    private String imgUrl;
    @Basic
    @Column(name = "mov_url")
    private String movUrl;
    @Basic
    @Column(name = "trailer_url")
    private String trailerUrl;

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getRealeaseYear() {
        return realeaseYear;
    }

    public void setRealeaseYear(Date realeaseYear) {
        this.realeaseYear = realeaseYear;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMovUrl() {
        return movUrl;
    }

    public void setMovUrl(String movUrl) {
        this.movUrl = movUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return filmId == that.filmId && vip == that.vip && Objects.equals(filmName, that.filmName) && Objects.equals(descripton, that.descripton) && Objects.equals(manufacturer, that.manufacturer) && Objects.equals(director, that.director) && Objects.equals(realeaseYear, that.realeaseYear) && Objects.equals(imgUrl, that.imgUrl) && Objects.equals(movUrl, that.movUrl) && Objects.equals(trailerUrl, that.trailerUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, filmName, descripton, manufacturer, director, realeaseYear, vip, imgUrl, movUrl, trailerUrl);
    }
}
