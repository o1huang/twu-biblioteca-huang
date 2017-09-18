package com.twu.biblioteca;

import java.util.Objects;

public class MovieInfo implements Item {
    private String name;
    private String year;
    private String director;
    private String rating;
    boolean beenRented=false;

    public MovieInfo(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public void rent(){
        beenRented=true;
    }
    public void returns(){
        beenRented=false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieInfo movieInfo = (MovieInfo) o;
        return beenRented == movieInfo.beenRented &&
                Objects.equals(name, movieInfo.name) &&
                Objects.equals(year, movieInfo.year) &&
                Objects.equals(director, movieInfo.director) &&
                Objects.equals(rating, movieInfo.rating);
    }

    @Override
    public String toString() {
        return name+"\t"+year+"\t"+director+"\t"+rating;
    }
}
