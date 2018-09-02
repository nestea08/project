package com.company.model.entities;

import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.exceptions.InvalidSpentTimeException;

import java.util.Objects;

/**
 * Created by Asus on 21.08.2018.
 */
public class TimeTrackedItem implements TrackedItem{
    private Activity activity;
    private int spentTime;

    public TimeTrackedItem(Activity activity, int spentTime) {
        if (spentTime < 0 || spentTime > 10000) {
            throw new InvalidSpentTimeException(spentTime);
        }
        this.activity= activity;
        this.spentTime = spentTime;
    }

    @Override
    public TrackedItem plusSpentTime(int time) {
        return new TimeTrackedItem(activity, spentTime + time);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeTrackedItem)) return false;
        TimeTrackedItem that = (TimeTrackedItem) o;
        return spentTime == that.spentTime &&
                activity.equals(that.activity);
    }

    @Override
    public int hashCode() {
        return (activity.hashCode() * 31) + spentTime;
    }
}
