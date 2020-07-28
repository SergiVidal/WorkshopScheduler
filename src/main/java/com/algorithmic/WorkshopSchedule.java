package com.algorithmic;

import com.algorithmic.algorithm.*;
import com.algorithmic.model.Schedule;
import com.algorithmic.model.Workshop;
import com.algorithmic.service.CLI;
import com.algorithmic.service.DataService;
import com.algorithmic.view.ScheduleView;

import java.awt.*;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class WorkshopSchedule {

    public static void paintSchedule(BaseAlgorithm object, List<Workshop> workshops, ScheduleView scheduleView) {
        for (int i = 0; i < object.getBestConfig().length; i++) {
            if (object.getBestConfig()[i] == 1) {
                for (int j = 0; j < workshops.get(i).getTimetable().size(); j++) {
                    scheduleView.setCellContent(workshops.get(i).getAcronym(),
                            workshops.get(i).getCategory(),
                            workshops.get(i).getPrice(),
                            new Color(workshops.get(i).getRgbColor().get(0), workshops.get(i).getRgbColor().get(1), workshops.get(i).getRgbColor().get(2)),
                            workshops.get(i).getTimetable().get(j).getHour(),
                            workshops.get(i).getTimetable().get(j).getDay());
                }
            }
        }
    }

    public static void main(String[] args) {
        Schedule schedule;
        ScheduleView scheduleView;
        List<Workshop> workshops;
        DataService dataService = new DataService();

        int menuOption;
        CLI.showWelcome();
        try {
            schedule = dataService.readJSON(CLI.askForPathWithMessage());
            if (schedule != null) {
                workshops = schedule.getWorkshops();

                Duration duration;
                LocalDateTime now;
                LocalDateTime after;
                do {
                    scheduleView = new ScheduleView();
                    menuOption = CLI.askForMenuOption();
                    switch (menuOption) {
                        case 1:
                            AllConfigurations allConfigurations = new AllConfigurations(schedule);
                            if (CLI.askForMarkOption()) {
                                now = LocalDateTime.now();
                                allConfigurations.backTrackingMarcaje(new int[workshops.size()], 0);
                                after = LocalDateTime.now();
                            } else {
                                now = LocalDateTime.now();
                                allConfigurations.backTracking(new int[workshops.size()], 0);
                                after = LocalDateTime.now();
                            }
                            paintSchedule(allConfigurations, workshops, scheduleView);

                            scheduleView.setSolutionsContent(allConfigurations.getTotalConfigurations());
                            scheduleView.setStartDateContent(now);
                            scheduleView.setFinishDateContent(after);
                            duration = Duration.between(now, after);
                            scheduleView.setDurationContent(duration);
                            scheduleView.setVisible(true);
                            break;
                        case 2:
                            MaxHours maxHours = new MaxHours(schedule);
                            if (CLI.askForMarkOption()) {
                                now = LocalDateTime.now();
                                maxHours.backTrackingMarcaje(new int[workshops.size()], 0);
                                after = LocalDateTime.now();
                            } else {
                                now = LocalDateTime.now();
                                maxHours.backTracking(new int[workshops.size()], 0);
                                after = LocalDateTime.now();
                            }
                            paintSchedule(maxHours, workshops, scheduleView);

                            scheduleView.setSolutionsContent(maxHours.getTotalConfigurations());
                            scheduleView.setStartDateContent(now);
                            scheduleView.setFinishDateContent(after);
                            duration = Duration.between(now, after);
                            scheduleView.setDurationContent(duration);
                            scheduleView.setTotalWorkshopsContent(maxHours.getNumWorkshops());
                            scheduleView.setTotalHoursContent(maxHours.getBestMax());
                            scheduleView.setVisible(true);
                            break;
                        case 3:
                            Budget budget = new Budget(schedule, CLI.askForBudget());
//                            Budget budget = new Budget(schedule, new BigDecimal("40"));

                            if (CLI.askForMarkOption()) {
                                now = LocalDateTime.now();
                                budget.backTrackingMarcaje(new int[workshops.size()], 0);
                                after = LocalDateTime.now();
                            } else {
                                now = LocalDateTime.now();
                                budget.backTracking(new int[workshops.size()], 0);
                                after = LocalDateTime.now();
                            }
                            paintSchedule(budget, workshops, scheduleView);

                            scheduleView.setSolutionsContent(budget.getTotalConfigurations());
                            scheduleView.setStartDateContent(now);
                            scheduleView.setFinishDateContent(after);
                            duration = Duration.between(now, after);
                            scheduleView.setDurationContent(duration);
                            scheduleView.setLimitCostContent(budget.getBudget().floatValue());
                            scheduleView.setBaseCostContent(budget.getBestBasePrice().floatValue());
                            scheduleView.setDiscountContent(budget.getDiscound());
                            scheduleView.setFinalCostContent(budget.getBestPrice().floatValue());
                            scheduleView.setCategoryContent(1, budget.getCategories()[0]);
                            scheduleView.setCategoryContent(2, budget.getCategories()[1]);
                            scheduleView.setCategoryContent(3, budget.getCategories()[2]);
                            scheduleView.setCategoryContent(4, budget.getCategories()[3]);
                            scheduleView.setCategoryContent(5, budget.getCategories()[4]);
                            scheduleView.setVisible(true);
                            break;
                        case 4:
                            System.out.println("Saliendo del programa...");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Opcion incorrecta, introduce un número entre 1 y 4!");
                            break;
                    }
                } while (true);
            } else {
                System.out.println("No existe el fichero indicado");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
//TODO: Objetivo 3 150w sin marcaje me sale 1 solucion de mas pero el mismo resultado, con el resto de ficheros funciona bien
