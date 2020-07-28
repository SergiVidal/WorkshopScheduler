package com.algorithmic.algorithm;

import com.algorithmic.model.Schedule;
import com.algorithmic.model.Timetable;

import java.util.Arrays;

public class AllConfigurations extends BaseAlgorithm {

    public AllConfigurations(Schedule schedule) {
        super(schedule);
    }

    @Override
    public void tratarSolucion(int[] x) {
        totalConfigurations++;
        bestConfig = Arrays.copyOf(x, x.length);
    }

    @Override
    public void marcar(int[] x, int k) {
        if(x[k] == 1) {
            for (int i = 0; i < this.workshops.get(k).getTimetable().size(); i++) {
                Timetable timetable = this.workshops.get(k).getTimetable().get(i);

                marcaje.getAssist()[timetable.getDay()][timetable.getHour()]++;
            }
        }
    }

    @Override
    public void desmarcar(int[] x, int k) {
        if(x[k] == 1) {
            for (int i = 0; i < this.workshops.get(k).getTimetable().size(); i++) {
                Timetable timetable = this.workshops.get(k).getTimetable().get(i);

                marcaje.getAssist()[timetable.getDay()][timetable.getHour()]--;
            }
        }
    }

    @Override
    public void tratarSolucionMarcaje(int[] x) {
        totalConfigurations++;
        bestConfig = Arrays.copyOf(x, x.length);
    }

}
