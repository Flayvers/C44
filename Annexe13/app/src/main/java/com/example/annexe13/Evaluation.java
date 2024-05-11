package com.example.annexe13;

public class Evaluation {
    private String beerName;
    private String brewery;
    private int rating;

    public Evaluation(String beerName, String brewery, int rating) {
        this.beerName = beerName;
        this.brewery = brewery;
        this.rating = rating;
    }

    public String getBeerName() {
        return beerName;
    }

    public String getBrewery() {
        return brewery;
    }

    public int getRating() {
        return rating;
    }
}
