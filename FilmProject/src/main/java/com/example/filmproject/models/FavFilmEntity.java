package com.example.filmproject.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "FavFilm", schema = "dbo", catalog = "movie_webdb")
public class FavFilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "FilmID")
    private int filmId;
    @Basic
    @Column(name = "UserID")
    private int userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavFilmEntity that = (FavFilmEntity) o;
        return filmId == that.filmId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, userId);
    }
}
