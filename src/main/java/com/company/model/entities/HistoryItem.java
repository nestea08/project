package com.company.model.entities;

import com.company.model.entities.interfaces.Tracker;
import com.company.model.exceptions.InvalidSpentTimeException;

import java.time.LocalDate;

/**
 * Represents a history item to ended activity
 */
public class HistoryItem{
    private int id;
    private Tracker tracker;
    private String title;
    /**
     * Spent time cannot accept zero value.
     * History item is considered to be created from
     * activity on which was spent time.
     */
    private int spentTime;
    private LocalDate endDate;

    /**
     *  Constructs a history item with default id
     */
    public HistoryItem(String title, Tracker tracker, int spentTime, LocalDate date) {
        if (spentTime <= 0 && spentTime > 10000) {
            throw new InvalidSpentTimeException(spentTime);
        }
        this.title = title;
        this.tracker = tracker;
        this.spentTime = spentTime;
        this.endDate = date;
    }

    /**
     * Constructs a history item with specified id
     * @param id history item id from database
     */
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
