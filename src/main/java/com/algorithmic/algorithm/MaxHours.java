package com.algorithmic.algorithm;

import com.algorithmic.model.Schedule;
import com.algorithmic.model.Timetable;

import java.util.Arrays;

public class MaxHours extends BaseAlgorithm {

    private int numWorkshops;
    private int actualNumWorkshops;
    private int bestMax = 0;
    private int[] bestConfig;

    public MaxHours(Schedule schedule) {
        super(schedule);
    }

    @Override
    public void tratarSolucion(int[] x) {
        int actualMax = 0;
        actualNumWorkshops = 0;

        for (int i = 0; i < x.length; i++) {
            if (x[i] == 1) {
                actualMax += this.workshops.get(i).getTimetable().size();
                actualNumWorkshops++;

            }
        }

        if (actualMax == bestMax) {
            totalConfigurations++;
        } else if (actualMax > bestMax) {
            bestMax = actualMax;
            bestConfig = Arrays.copyOf(x, x.length);
            numWorkshops = actualNumWorkshops;

            totalConfigurations = 1;
        }
    }

    @Override
    public void marcar(int[] x, int k) {
        if (x[k] == 1) {
            for (int i = 0; i < this.workshops.get(k).getTimetable().size(); i++) {
                Timetable timetable = this.workshops.get(k).getTimetable().get(i);

                marcaje.getAssist()[timetable.getDay()][timetable.getHour()]++;
            }
            marcaje.setActualMaxHours(marcaje.getActualMaxHours() + this.workshops.get(k).getTimetable().size());
            marcaje.setNumWorkshops(marcaje.getNumWorkshops() + 1);
        }
    }

    @Override
    public void desmarcar(int[] x, int k) {
        if (x[k] == 1) {
            for (int i = 0; i < this.workshops.get(k).getTimetable().size(); i++) {
                Timetable timetable = this.workshops.get(k).getTimetable().get(i);

                marcaje.getAssist()[timetable.getDay()][timetable.getHour()]--;
            }
            marcaje.setActualMaxHours(marcaje.getActualMaxHours() - this.workshops.get(k).getTimetable().size());
            marcaje.setNumWorkshops(marcaje.getNumWorkshops() - 1);
        }
    }

    public void tratarSolucionMarcaje(int[] x) {
        if (marcaje.getActualMaxHours() == marcaje.getBestMaxHours()) {
            totalConfigurations++;
        } else if (marcaje.getActualMaxHours() > marcaje.getBestMaxHours()) {
            marcaje.setBestConfig(Arrays.copyOf(x, x.length));
            marcaje.setBestMaxHours(marcaje.getActualMaxHours());
            numWorkshops = marcaje.getNumWorkshops();
            bestMax = marcaje.getActualMaxHours();
            bestConfig = marcaje.getBestConfig();

            totalConfigurations = 1;
        }
    }

    public int getBestMax() {
        return bestMax;
    }

    public void setBestMax(int bestMax) {
        this.bestMax = bestMax;
    }

    public int[] getBestConfig() {
        return bestConfig;
    }

    public void setBestConfig(int[] bestConfig) {
        this.bestConfig = bestConfig;
    }

    public int getNumWorkshops() {
        return numWorkshops;
    }

    public void setNumWorkshops(int numWorkshops) {
        this.numWorkshops = numWorkshops;
    }
}
