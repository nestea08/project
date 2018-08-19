package com.company.model.entities.interfaces;

import java.util.Set;

public interface Tracker {
    int getId();

    void addTracked(Tracked item);

    void removeTracked(Tracked item);

    Set<Tracked> getAllTracked();

    Tracked getTrackedById(int id);

    Integer getSpentTimeOnTracked(Tracked tracked);

    void setSpentTimeOnTracked(Tracked tracked, Integer time);
}
