package com.algorithmic.algorithm;

import com.algorithmic.model.Schedule;
import com.algorithmic.model.Timetable;
import com.algorithmic.model.Workshop;

import java.util.List;

public abstract class BaseAlgorithm {
    protected final List<Workshop> workshops;
    protected final Integer[][] compatibilityMatrix;
    protected int[] bestConfig;
    protected Marcaje marcaje;
    protected int totalConfigurations;

    public BaseAlgorithm(Schedule schedule) {
        this.workshops = schedule.getWorkshops();
        this.compatibilityMatrix = schedule.getCompatibilityMatrix();
        this.marcaje = new Marcaje(schedule.getWorkshops().size());
        this.bestConfig = new int[workshops.size()];
    }

    public boolean buena(int[] x, int k) {
        //Si asistes al k-eismo
        if (x[k] == 1) {
            //Recorres todos los anteriores menos el k-esimo
            for (int i = 0; i < k; i++) {
                //Si has asistido al i-esimo
                if (x[i] == 1) {
                    //Comprueba si son compatibles el i-esimo (los anteriores) con el k-esimo
                    if (this.compatibilityMatrix[i][k] == 0) {
                        return false;
                    }
                    for (Timetable timetableK : this.workshops.get(k).getTimetable()) {
                        //Recorres los horarios del i-sesimo (anteriores)
                        for (Timetable timetableI : this.workshops.get(i).getTimetable()) {
                            //Compruebas 1 horario del k-esimo con  1 horario del i-esimo (Dia/Hora)
                            if (timetableK.getDay().equals(timetableI.getDay()) && timetableK.getHour().equals(timetableI.getHour())) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean buenaMarcaje(int[] x, int k) {
        if (x[k] == 1) {
            //Recorres todos los anteriores menos el k-esimo
            for (int i = 0; i < k; i++) {
                //Si has asistido al i-esimo
                if (x[i] == 1) {
                    //Comprueba si son compatibles el i-esimo (los anteriores) con el k-esimo
                    if (this.compatibilityMatrix[i][k] == 0) {
                        return false;
                    }

                }
            }

            //Recorres los horarios del k-esimo
            for (Timetable timetable : this.workshops.get(k).getTimetable()) {
                if (marcaje.getAssist()[timetable.getDay()][timetable.getHour()] > 1) {
                    return false;
                }
            }

        }
        return true;
    }

    public abstract void tratarSolucion(int[] x);

    public abstract void tratarSolucionMarcaje(int[] x);

    public abstract void marcar(int[] x, int k);

    public abstract void desmarcar(int[] x, int k);

    public void preparaRecorridoNivel(int[] x, int k) {
        x[k] = -1;
    }

    public boolean haySucesor(int[] x, int k) {
        return x[k] < 1;
    }

    public void siguienteHermano(int[] x, int k) {
        x[k]++;
    }

    public boolean solucion(int k) {
        return k == this.workshops.size() - 1;
    }

    public void backTracking(int[] x, int k) {
        preparaRecorridoNivel(x, k);

        while (haySucesor(x, k)) {
            siguienteHermano(x, k);
            if (solucion(k)) {
                if (buena(x, k)) {
                    tratarSolucion(x);
                }
            } else {
                if (buena(x, k)) {
                    backTracking(x, k + 1);
                }
            }
        }

    }

    public void backTrackingMarcaje(int[] x, int k) {
        preparaRecorridoNivel(x, k);

        while (haySucesor(x, k)) {
            siguienteHermano(x, k);
            marcar(x, k);
            if (solucion(k)) {
                if (buenaMarcaje(x, k)) {
                    tratarSolucionMarcaje(x);
                }
            } else {
                if (buenaMarcaje(x, k)) {
                    backTrackingMarcaje(x, k + 1);
                }
            }
            desmarcar(x, k);
        }

    }

    public int[] getBestConfig() {
        return bestConfig;
    }

    public void setBestConfig(int[] bestConfig) {
        this.bestConfig = bestConfig;
    }

    public int getTotalConfigurations() {
        return totalConfigurations;
    }

    public void setTotalConfigurations(int totalConfigurations) {
        this.totalConfigurations = totalConfigurations;
    }

    public Marcaje getMarcaje() {
        return marcaje;
    }

    public void setMarcaje(Marcaje marcaje) {
        this.marcaje = marcaje;
    }
}
