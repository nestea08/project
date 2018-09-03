package com.company.model.entities.interfaces;

/**
 * Describes a time tracking that
 * is held my tracker
 */
public interface TimeTracking {
    int getId();

    String getTitle();

    String getDescription();

    int getSpentTime();

    TimeTracking plusSpentTime(int time);
}
