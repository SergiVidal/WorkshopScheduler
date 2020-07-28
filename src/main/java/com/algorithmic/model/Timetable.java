package com.algorithmic.model;

import java.util.Objects;

public class Timetable {
    private Integer day;
    private Integer hour;

    public Timetable() {
    }

    public Timetable(Integer day, Integer hour) {
        this.day = day;
        this.hour = hour;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getHour() {
        return hour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timetable)) return false;
        Timetable timetable = (Timetable) o;
        return Objects.equals(day, timetable.day) &&
                Objects.equals(hour, timetable.hour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, hour);
    }
}
