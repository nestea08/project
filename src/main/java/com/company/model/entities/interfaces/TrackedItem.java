package com.company.model.entities.interfaces;

import com.company.model.exceptions.InvalidSpentTimeException;

public interface TrackedItem {
    int getId();

    String getTitle();

    String getDescription();

    int getSpentTime();

    TrackedItem plusSpentTime(int time) throws InvalidSpentTimeException;
}
