package com.company.model.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Activity {
    private int id;
    private String title;
    private String description;
    private Set<TrackerUser> trackerUsers;

    public Activity(String title, String description) {
        this(0, title, description);
    }

    public Activity(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        trackerUsers = new HashSet<>();
    }

    public void addTrackerUser(TrackerUser trackerUser) {
        trackerUsers.add(trackerUser);
    }

    public void removeTrackerUser(TrackerUser trackerUser) {
        trackerUsers.remove(trackerUser);
    }

    public Set<TrackerUser> getTrackerUsers() {
        return new HashSet<>(trackerUsers);
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
