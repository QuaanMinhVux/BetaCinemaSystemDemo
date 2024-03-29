package com.betacinema.demo.service;

public class FilmSearch {
    private String name;
    private String category;
    private int year;

    public FilmSearch(String name, String category, int year) {
        this.name = name;
        this.category = category;
        this.year = year;
    }

    public FilmSearch() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
