package com.company.model.entities;

import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

import java.util.HashSet;
import java.util.Set;

public class Activity {
    private int id;
    private String title;
    private String description;

    public Activity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Activity(int id, String title, String description) {
        this(title, description);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return title.equals(activity.title) &&
                description.equals(activity.description);
    }

    @Override
    public int hashCode() {
        return (title.hashCode() * 31) + description.hashCode();
    }
}
