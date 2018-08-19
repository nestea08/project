package com.company.model.entities;

import com.company.model.entities.interfaces.Tracked;

import java.time.LocalDate;

public class HistoryItem implements Tracked {
    private int id;
    private String title;
    private int spentTime;
    private LocalDate endDate;

    public HistoryItem(String title, int spentTime, LocalDate date) {
        this.title = title;
        this.spentTime = spentTime;
        this.endDate = date;
    }

    public HistoryItem(int id, String title, int spentTime, LocalDate date) {
        this(title, spentTime, date);
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
