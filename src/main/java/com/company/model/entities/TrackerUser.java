package com.company.model.entities;


import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

import java.util.*;

public class TrackerUser extends User implements Tracker{
    private List<TrackedItem> trackedItems;

    public static class Builder extends User.Builder {
        public Builder(String nickname, String login, String password) {
            super(nickname, login, password, Role.USER);
        }

        @Override
        public Builder id(int val) {
            id = val;
            return this;
        }

        @Override
        public TrackerUser build() {
            return new TrackerUser(this);
        }

    }

    private TrackerUser(Builder builder) {
        super(builder);
        trackedItems = new ArrayList<>();
    }

    public void addTrackedItem(TrackedItem trackedItem) {
        trackedItems.add(trackedItem);
    }

    public void removeTrackedItem(TrackedItem trackedItem) {
        trackedItems.remove(trackedItem);
    }

    public TrackedItem getTrackedItemById(int id) {
        Optional<TrackedItem> trackedOptional = trackedItems.stream().
                filter(item -> item.getId() == id).findFirst();
        if (!trackedOptional.isPresent()) {
            throw new RuntimeException();
        }
        return trackedOptional.get();
    }

    public List<TrackedItem> getTrackedItems() {
        return trackedItems;
    }

}
