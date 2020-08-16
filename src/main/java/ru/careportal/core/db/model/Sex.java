package ru.careportal.core.db.model;

import java.util.ArrayList;
import java.util.List;

public enum Sex {
    MAN("Мужчина"),
    WOMAN("Женщина");

    private final String rusName;

    Sex(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }

    public static List<String> getSexList() {
        List<String> sexList = new ArrayList<>();
        for (Sex sex : Sex.values()) {
            sexList.add(sex.getRusName());
        }
        return sexList;
    }

    public static Sex getSexByRusName(String rusName) {
        for (Sex sex : values()) {
            if (sex.getRusName().equals(rusName)) {
                return sex;
            }
        }

        throw new IllegalArgumentException("No enum found with url: [" + rusName + "]");
    }

    public static List<Sex> getEnumListByRusNameList(List<String> rusNameList) {
        List<Sex> sexList = new ArrayList<>();
        for(String rusName : rusNameList) {
            sexList.add(getSexByRusName(rusName));
        }
        return sexList;
    }
}
