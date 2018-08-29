package com.company.model.entities.interfaces;

import com.company.model.exceptions.UnknownTrackedItemException;

import java.util.List;

public interface Tracker {
    int getId();

    String getNickname();

    void addTrackedItem(TrackedItem item);

    void removeTrackedItem(TrackedItem item);

    List<TrackedItem> getTrackedItems();

    TrackedItem getTrackedItemById(int id) throws UnknownTrackedItemException;

}
