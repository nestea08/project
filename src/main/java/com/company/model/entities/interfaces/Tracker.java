package com.company.model.entities.interfaces;

import java.util.Map;
import java.util.Set;

public interface Tracker {
    int getId();

    void addTracked(Tracked item);

    void removeTracked(Tracked item);

    Map<Tracked, Integer> getTrackedItems();

    Map.Entry<Tracked, Integer> getTrackedById(int id);

    Integer getSpentTime(Tracked tracked);

    void setSpentTime(Tracked tracked, Integer time);
}
