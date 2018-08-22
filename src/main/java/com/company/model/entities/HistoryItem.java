package com.company.model.entities;

import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

import java.time.LocalDate;

public class HistoryItem{
    private int id;
    private Tracker tracker;
    private String title;
    private int spentTime;
    private LocalDate endDate;

    public HistoryItem(String title, Tracker tracker, int spentTime, LocalDate date) {
        this.title = title;
        this.tracker = tracker;
        this.spentTime = spentTime;
        this.endDate = date;
    }

    public HistoryItem(int id, Tracker tracker, String title, int spentTime, LocalDate date) {
        this(title, tracker, spentTime, date);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSpentTime() {
        return spentTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Tracker getTracker() {
        return tracker;
    }
}
