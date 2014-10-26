package com.brumhack.guessthestats;

/**
 * Created by Bertie on 26/10/2014.
 */
public class Question {
    private String country;
    private String description;
    private int value;

    public Question(String country, String description, int value) {
        this.country = country;
        this.description = description;
        this.value = value;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public String getCountryPretty() {
        return country.replaceAll("_", " ").toUpperCase();
    }
}
