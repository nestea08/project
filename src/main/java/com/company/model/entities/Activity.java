package com.company.model.entities;

import com.company.model.entities.interfaces.Tracked;
import com.company.model.entities.interfaces.Tracker;

import java.util.HashSet;
import java.util.Set;

public class Activity implements Tracked {
    private int id;
    private String title;
    private String description;
    private Set<Tracker> trackers;

    public Activity(String title, String description) {
        this.title = title;
        this.description = description;
        trackers = new HashSet<>();
    }

    public Activity(int id, String title, String description) {
        this(title, description);
        this.id = id;
    }

    public void addTracker(Tracker tracker) {
        trackers.add(tracker);
    }

    public void removeTracker(Tracker tracker) {
        trackers.remove(tracker);
    }

    public Set<Tracker> getTrackers() {
        return new HashSet<>(trackers);
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
