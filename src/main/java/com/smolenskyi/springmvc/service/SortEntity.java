package com.smolenskyi.springmvc.service;

public enum SortEntity {
    SortNameAZ("Name [A - Z]"), SortNameZA("Name [Z - A]"), SortCountToMax("Count [min - max]"), SortCountToMin("Count [max - min]");
    private String name;

    SortEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
