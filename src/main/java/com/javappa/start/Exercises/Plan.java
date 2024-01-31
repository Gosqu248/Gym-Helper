package com.javappa.start.Exercises;

public class Plan {

    private int id_plan;
    private String name;
    private String description;
    private String lvl;
    private int id_user;

    public Plan() {}

    public Plan(int id_plan, String name, String description, String lvl, int id_user) {
        this.id_plan = id_plan;
        this.name = name;
        this.description = description;
        this.lvl = lvl;
        this.id_user = id_user;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLvl() {
        return lvl;
    }

    public void setLvl(String lvl) {
        this.lvl = lvl;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
