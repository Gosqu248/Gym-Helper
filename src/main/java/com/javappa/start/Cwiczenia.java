package com.javappa.start;

public class Cwiczenia {

    private int id_plan;
    private int id_jednostki;
    private String name;
    private int series;
    private int reps;
    private String description;

    public Cwiczenia() {
    }

    public Cwiczenia(int id_plan, int id_jednostki, String name, int series, int reps, String description) {
        this.id_plan = id_plan;
        this.id_jednostki = id_jednostki;
        this.name = name;
        this.series = series;
        this.reps = reps;
        this.description = description;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public int getId_jednostki() {
        return id_jednostki;
    }

    public void setId_jednostki(int id_jednostki) {
        this.id_jednostki = id_jednostki;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
