package com.example.filmproject.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Comment", schema = "dbo", catalog = "movie_webdb")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CommentID")
    private int commentId;
    @Basic
    @Column(name = "FilmID")
    private int filmId;
    @Basic
    @Column(name = "UserID")
    private int userId;
    @Basic
    @Column(name = "Description")
    private String description;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return commentId == that.commentId && filmId == that.filmId && userId == that.userId && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, filmId, userId, description);
    }
}
