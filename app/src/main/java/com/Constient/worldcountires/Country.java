package com.Constient.worldcountires;

import com.google.gson.JsonObject;

import java.util.List;

public class Country {
    private JsonObject name;
    private List<String> capital;
    private List<String> continents;
    private JsonObject flags;
    private int population;
    private String region;


    public Country(JsonObject name, List<String> capital, int population, String region) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.region = region;
    }

    public JsonObject getName() {
        return name;
    }

    public void setName(JsonObject name) {
        this.name = name;
    }

    public List<String> getCapital() {
        return capital;
    }
    public List<String> getContinents() {
        return continents;
    }

    public JsonObject getFlags() {
        return flags;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }




}

