package com.algorithmic.algorithm;

import java.math.BigDecimal;
import java.util.Arrays;

public class Marcaje {
    private int[][] assist;
    private int bestMaxHours = 0;
    private int actualMaxHours = 0;
    private BigDecimal bestPrice;
    private BigDecimal actualPrice;
    private int[] bestConfig;
    private int[] categories;
    private int numWorkshops;
    private int counter;

    private static final int MAX_CATEGORIES = 5;
    private static final int MAX_DAYS = 5;
    private static final int MAX_HOURS = 12;

    public Marcaje(int size) {
        this.assist = new int[MAX_DAYS][MAX_HOURS];
        this.bestConfig = new int[size];
        this.categories = new int[MAX_CATEGORIES];
        this.bestPrice = new BigDecimal("0");
        this.actualPrice = new BigDecimal("0");
    }

    public int[][] getAssist() {
        return assist;
    }

    public void setAssist(int[][] assist) {
        this.assist = assist;
    }

    public int getBestMaxHours() {
        return bestMaxHours;
    }

    public void setBestMaxHours(int bestMaxHours) {
        this.bestMaxHours = bestMaxHours;
    }

    public int getActualMaxHours() {
        return actualMaxHours;
    }

    public void setActualMaxHours(int actualMaxHours) {
        this.actualMaxHours = actualMaxHours;
    }

    public BigDecimal getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(BigDecimal bestPrice) {
        this.bestPrice = bestPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int[] getBestConfig() {
        return bestConfig;
    }

    public void setBestConfig(int[] bestConfig) {
        this.bestConfig = bestConfig;
    }

    public int[] getCategories() {
        return categories;
    }

    public void setCategories(int[] categories) {
        this.categories = categories;
    }

    public int getNumWorkshops() {
        return numWorkshops;
    }

    public void setNumWorkshops(int numWorkshops) {
        this.numWorkshops = numWorkshops;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }



    @Override
    public String toString() {
        return "Marcaje{" +
                "assist=" + Arrays.toString(assist) +
                ", bestMaxHours=" + bestMaxHours +
                ", actualMaxHours=" + actualMaxHours +
                ", bestPrice=" + bestPrice +
                ", actualPrice=" + actualPrice +
                ", bestConfig=" + Arrays.toString(bestConfig) +
                ", categories=" + Arrays.toString(categories) +
                '}';
    }
}
