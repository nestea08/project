package com.company.model.entities;

import com.company.model.entities.interfaces.TimeTracking;
import com.company.model.exceptions.InvalidSpentTimeException;

/**
 * Represents a time tracking item for tracker
 * Is a base implementation for
 * <code>TimeTracking</code> interface.
 *
 * Uses composition with activity providing
 * accessors to its fields
 */
public class TimeTrackingItem implements TimeTracking {
    private Activity activity;
    /**
     * Spent time cannot accept negative values
     * or values more than 10000 hours
     */
    private int spentTime;

    /**
     * Constructs a time tracking item with
     * specified activity and spent time
     */
    public TimeTrackingItem(Activity activity, int spentTime) {
        if (spentTime < 0 || spentTime > 10000) {
            throw new InvalidSpentTimeException(spentTime);
        }
        this.activity = activity;
        this.spentTime = spentTime;
    }

    /**
     * Creates a new time tracking item with
     * increased spent time value
     *
     * Added to save time tracking item's non mutability
     */
    @Override
    public TimeTracking plusSpentTime(int time) {
        return new TimeTrackingItem(activity, spentTime + time);
    }

    @Override
    public int getId() {
        return activity.getId();
    }

    @Override
    public String getTitle() {
        return activity.getTitle();
    }

    @Override
    public String getDescription() {
        return activity.getDescription();
    }

    @Override
    public int getSpentTime() {
        return spentTime;
    }

    /**
     * Compares two objects for equality.
     * Comparison is dependent on activity equality
     * @return <code>true</code> if the objects are the same;
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeTrackingItem)) return false;
        TimeTrackingItem that = (TimeTrackingItem) o;
        return spentTime == that.spentTime &&
                activity.equals(that.activity);
    }

    /**
     * Computes a hash code to time tracking item
     * Computation is dependent on activity hash code
     * @return hash code
     */
    @Override
    public int hashCode() {
        return (activity.hashCode() * 31) + spentTime;
    }
}
