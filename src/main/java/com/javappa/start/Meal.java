package com.javappa.start;

import java.util.List;

public class Meal {
    private String name;
    private List<String> names;

    public Meal(String name, List<String> names) {
        this.name = name;
        this.names = names;
    }

    public String getName() {
        return name;
    }

    public List<String> getNames() {
        return names;
    }
}
