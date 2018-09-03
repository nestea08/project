package com.company.model.entities.interfaces;

import java.util.List;

/**
 * Describes somebody who can manage trackings
 */
public interface Tracker {
    int getId();

    String getNickname();

    void addTracking(TimeTracking item);

    void removeTracking(TimeTracking item);

    List<TimeTracking> getTimeTrackings();

    TimeTracking getTrackingById(int id);

}
