package com.algorithmic.model;

import java.util.ArrayList;
import java.util.List;

public class Workshop {
    private String acronym;
    private float price;
    private int category;
    private List<Timetable> timetable = new ArrayList<>();
    private List<Integer> rgbColor = new ArrayList<>();

    public Workshop() {
    }

    public Workshop(String acronym,
                    float price,
                    int category,
                    List<Timetable> timetable,
                    List<Integer> rgbColor) {
        this.acronym = acronym;
        this.price = price;
        this.category = category;
        this.timetable = timetable;
        this.rgbColor = rgbColor;
    }


    public String getAcronym() {
        return acronym;
    }

    public float getPrice() {
        return price;
    }

    public int getCategory() {
        return category;
    }

    public List<Timetable> getTimetable() {
        return timetable;
    }

    public List<Integer> getRgbColor() {
        return rgbColor;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Workshop)) return false;
//        Workshop workshop = (Workshop) o;
//        return Objects.equals(acronym, workshop.acronym) &&
//                Objects.equals(price, workshop.price) &&
//                Objects.equals(category, workshop.category) &&
//                Objects.equals(timetable, workshop.timetable) &&
//                Objects.equals(rgbColor, workshop.rgbColor);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(acronym, price, category, timetable, rgbColor);
//    }


    @Override
    public String toString() {
        return "Workshop{" +
                "acronym='" + acronym + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", timetable=" + timetable +
                ", rgbColor=" + rgbColor +
                '}';
    }
}
