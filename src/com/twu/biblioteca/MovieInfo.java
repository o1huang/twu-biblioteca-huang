package com.twu.biblioteca;

import java.util.Objects;

public class MovieInfo implements Item {
    public String name;
    public String year;
    public String director;
    public String rating;
    public boolean beenRented=false;

    public MovieInfo(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isBeenRented() {
        return beenRented;
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
        return  Objects.equals(name, movieInfo.name) &&
                Objects.equals(year, movieInfo.year) &&
                Objects.equals(director, movieInfo.director) &&
                Objects.equals(rating, movieInfo.rating);
    }

    @Override
    public String toString() {
        return name+"\t"+year+"\t"+director+"\t"+rating;
    }
}
