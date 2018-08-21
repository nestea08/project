package com.company.model.entities.interfaces;

public interface TrackedItem {
    int getId();

    String getTitle();

    String getDescription();

    int getSpentTime();

    TrackedItem plusSpentTime(int time);
}
