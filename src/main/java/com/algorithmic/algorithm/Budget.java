package com.algorithmic.algorithm;

import com.algorithmic.model.Schedule;
import com.algorithmic.model.Timetable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Budget extends BaseAlgorithm {
    private BigDecimal budget;
    private BigDecimal actualPrice;
    private BigDecimal bestPrice;
    private BigDecimal actualBasePrice;
    private BigDecimal bestBasePrice;
    private BigDecimal actualPriceDiscound;
    private int actualDiscound;
    private int discound;
    private int counter;
    private int[] categories;
    private int[] actualCategories;
    private static final int MAX_CATEGORIES = 5;

    public Budget(Schedule schedule, float budget) {
        super(schedule);
        this.budget = new BigDecimal(Float.toString(budget));
        categories = new int[MAX_CATEGORIES];
        actualCategories = new int[MAX_CATEGORIES];
        this.bestPrice = new BigDecimal("0");
    }

    public void tratarSolucion(int[] x) {
        actualPrice = new BigDecimal("0");
        for (int i = 0; i < x.length; i++) {
            if (x[i] == 1) {
                actualPrice = actualPrice.add(BigDecimal.valueOf(workshops.get(i).getPrice()));
            }
        }
        getDiscount(x);

        if (actualPriceDiscound.compareTo(budget) <= 0 && actualPriceDiscound.compareTo(bestPrice) == 0) {
            totalConfigurations++;
        } else if (actualPriceDiscound.compareTo(budget) <= 0 && actualPriceDiscound.compareTo(bestPrice) > 0) {
            bestBasePrice = actualBasePrice;

            bestPrice = actualPriceDiscound;
            bestConfig = Arrays.copyOf(x, x.length);

            discound = actualDiscound;
            categories = Arrays.copyOf(actualCategories, actualCategories.length);

            totalConfigurations = 1;
        }

    }

    private void getDiscount(int[] x) {
        actualPriceDiscound = new BigDecimal("0");
        actualDiscound = 0;
        counter = 0;
        actualCategories = new int[MAX_CATEGORIES];

        for (int i = 0; i < x.length; i++) {
            if (x[i] == 1)
                actualCategories[workshops.get(i).getCategory() - 1]++;
        }

        for (int i = 0; i < MAX_CATEGORIES; i++) {
            if (actualCategories[i] > 0) {
                counter++;
            }
        }
        actualPrice = actualPrice.setScale(2, RoundingMode.HALF_UP);

        actualBasePrice = actualPrice;

        if (counter == 2) {
            actualDiscound = 5;
            actualPriceDiscound = actualPrice.multiply(new BigDecimal("0.95"));
        } else if (counter > 2) {
            actualDiscound = 15;
            actualPriceDiscound = actualPrice.multiply(new BigDecimal("0.85"));
        }else {
            actualPriceDiscound = actualPrice;
        }
        actualPriceDiscound = actualPriceDiscound.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public void tratarSolucionMarcaje(int[] x) {
        getDiscountMarcaje();
        if (actualPrice.compareTo(budget) <= 0 && actualPrice.compareTo(marcaje.getBestPrice()) == 0) {
            totalConfigurations++;
        } else if (actualPrice.compareTo(budget) <= 0 && actualPrice.compareTo(marcaje.getBestPrice()) > 0) {
            bestBasePrice = actualBasePrice;

            marcaje.setBestPrice(actualPrice);
            marcaje.setBestPrice(marcaje.getBestPrice().setScale(2, RoundingMode.HALF_UP));

            marcaje.setBestConfig(Arrays.copyOf(x, x.length));

            bestPrice = marcaje.getBestPrice();
            bestConfig = Arrays.copyOf(x, x.length);

            discound = actualDiscound;
            categories = Arrays.copyOf(marcaje.getCategories(), marcaje.getCategories().length);

            totalConfigurations = 1;
        }
    }

    @Override
    public void marcar(int[] x, int k) {
        if (x[k] == 1) {
            for (int i = 0; i < workshops.get(k).getTimetable().size(); i++) {
                Timetable timetable = workshops.get(k).getTimetable().get(i);

                marcaje.getAssist()[timetable.getDay()][timetable.getHour()]++;
            }

            marcaje.setActualPrice(marcaje.getActualPrice().add(BigDecimal.valueOf(workshops.get(k).getPrice())));
            marcaje.setActualPrice(marcaje.getActualPrice().setScale(2, RoundingMode.HALF_UP));

            int category = workshops.get(k).getCategory() - 1;
            marcaje.getCategories()[category]++;

            if (marcaje.getCategories()[category] == 1) {
                marcaje.setCounter(marcaje.getCounter() + 1);
            }
        }
    }

    @Override
    public void desmarcar(int[] x, int k) {
        if (x[k] == 1) {
            for (int i = 0; i < workshops.get(k).getTimetable().size(); i++) {
                Timetable timetable = workshops.get(k).getTimetable().get(i);

                marcaje.getAssist()[timetable.getDay()][timetable.getHour()]--;
            }

            marcaje.setActualPrice(marcaje.getActualPrice().subtract(BigDecimal.valueOf(workshops.get(k).getPrice())));
            marcaje.setActualPrice(marcaje.getActualPrice().setScale(2, RoundingMode.HALF_UP));

            int category = workshops.get(k).getCategory() - 1;
            marcaje.getCategories()[category]--;

            if (marcaje.getCategories()[category] == 0) {
                marcaje.setCounter(marcaje.getCounter() - 1);
            }
        }
    }


    public void getDiscountMarcaje() {
        actualDiscound = 0;
        actualBasePrice = marcaje.getActualPrice();
        actualPrice = marcaje.getActualPrice();

        actualPrice = actualPrice.setScale(2, RoundingMode.HALF_UP);

        if (marcaje.getCounter() == 2) {
            actualDiscound = 5;
            actualPrice = actualPrice.multiply(new BigDecimal("0.95"));
        } else if (marcaje.getCounter() > 2) {
            actualDiscound = 15;
            actualPrice = actualPrice.multiply(new BigDecimal("0.85"));
        }
        actualPrice = actualPrice.setScale(2, RoundingMode.HALF_UP);

    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(BigDecimal bestPrice) {
        this.bestPrice = bestPrice;
    }

    public BigDecimal getActualBasePrice() {
        return actualBasePrice;
    }

    public void setActualBasePrice(BigDecimal actualBasePrice) {
        this.actualBasePrice = actualBasePrice;
    }

    public BigDecimal getBestBasePrice() {
        return bestBasePrice;
    }

    public void setBestBasePrice(BigDecimal bestBasePrice) {
        this.bestBasePrice = bestBasePrice;
    }

    public BigDecimal getActualPriceDiscound() {
        return actualPriceDiscound;
    }

    public void setActualPriceDiscound(BigDecimal actualPriceDiscound) {
        this.actualPriceDiscound = actualPriceDiscound;
    }

    public int getActualDiscound() {
        return actualDiscound;
    }

    public void setActualDiscound(int actualDiscound) {
        this.actualDiscound = actualDiscound;
    }

    public int getDiscound() {
        return discound;
    }

    public void setDiscound(int discound) {
        this.discound = discound;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int[] getCategories() {
        return categories;
    }

    public void setCategories(int[] categories) {
        this.categories = categories;
    }

    public int[] getActualCategories() {
        return actualCategories;
    }

    public void setActualCategories(int[] actualCategories) {
        this.actualCategories = actualCategories;
    }

    public static int getMaxCategories() {
        return MAX_CATEGORIES;
    }
}
