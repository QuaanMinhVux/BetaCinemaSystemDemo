package com.betacinema.demo.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int FilmID;

    private String FilmName;

    private String Descripton;

    private String Manufacturer;

    private String Director;

    private Date RealeaseYear;

    private Boolean VIP;

    private String img_url;

    private String mov_url;

    private String trailer_url;

    public int getFilmID() {
        return FilmID;
    }

    public void setFilmID(int filmID) {
        FilmID = filmID;
    }

    public String getFilmName() {
        return FilmName;
    }

    public void setFilmName(String filmName) {
        FilmName = filmName;
    }

    public String getDescripton() {
        return Descripton;
    }

    public void setDescripton(String descripton) {
        Descripton = descripton;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public Date getRealeaseYear() {
        return RealeaseYear;
    }

    public void setRealeaseYear(Date realeaseYear) {
        RealeaseYear = realeaseYear;
    }

    public Boolean getVIP() {
        return VIP;
    }

    public void setVIP(Boolean VIP) {
        this.VIP = VIP;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getMov_url() {
        return mov_url;
    }

    public void setMov_url(String mov_url) {
        this.mov_url = mov_url;
    }

    public String getTrailer_url() {
        return trailer_url;
    }

    public void setTrailer_url(String trailer_url) {
        this.trailer_url = trailer_url;
    }
}
