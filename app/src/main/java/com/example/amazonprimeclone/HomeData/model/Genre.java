package com.example.amazonprimeclone.HomeData.model;

import java.util.Arrays;
import java.util.List;



public class Genre {
    private int id;
    private String name;
    public static List<Genre> GenreList = Arrays.asList(
            new Genre(28, "Action"),
            new Genre(27, "Horror"),
            new Genre(16, "Animation"),
            new Genre(10749, "Romance"),
            new Genre(35, "Comedy"),
            new Genre(80, "Crime"),
            new Genre(12, "Adventure"),
            new Genre(16, "Animation"),
            new Genre(99, "Documentary"),
            new Genre(18, "Drama"),
            new Genre(10751, "Family"),
            new Genre(14, "Fantasy"),
            new Genre(36, "History"),
            new Genre(10402, "Music"),
            new Genre(9648, "Mystery"),
            new Genre(878, "Science Fiction"),

            new Genre(53, "Thriller"),
            new Genre(10752, "War")


    );


    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
