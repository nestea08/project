package com.company.model.entities;

import com.company.model.entities.interfaces.TrackedItem;

/**
 * Created by Asus on 21.08.2018.
 */
public class TimeTrackedItem implements TrackedItem{
    private Activity activity;
    private int spentTime;

    public TimeTrackedItem(Activity activity, int spentTime) {
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
}
