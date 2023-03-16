package com.example.filmproject.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "FilmCategory", schema = "dbo", catalog = "movie_webdb")
public class FilmCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "CategoryID")
    private int categoryId;
    @Basic
    @Column(name = "FilmID")
    private int filmId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategoryEntity that = (FilmCategoryEntity) o;
        return categoryId == that.categoryId && filmId == that.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, filmId);
    }
}
