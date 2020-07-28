package com.algorithmic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Schedule {
    private List<Workshop> workshops = new ArrayList<>();
    private Integer[][] compatibilityMatrix;

    public Schedule() {
    }

    public Schedule(List<Workshop> workshops, Integer[][] compatibilityMatrix) {
        this.workshops = workshops;
        this.compatibilityMatrix = compatibilityMatrix;;
    }

    public List<Workshop> getWorkshops() {
        return workshops;
    }

    public Integer[][] getCompatibilityMatrix() {
        return compatibilityMatrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(workshops, schedule.workshops) &&
                Objects.equals(compatibilityMatrix, schedule.compatibilityMatrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workshops, compatibilityMatrix);
    }
}
