package com.smolenskyi.springmvc.service;

public enum FilterEntity {
    AllParts("All parts"), NeedParts("Need parts"), OptionalParts("Optional parts");
    private String name;

    FilterEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
