package com.company.model.entities;


import com.company.model.entities.interfaces.Tracked;
import com.company.model.entities.interfaces.Tracker;

import java.util.*;

public class TrackerUser extends User implements Tracker{
    private Map<Tracked, Integer> trackedItems;

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
        trackedItems = new HashMap<>();
    }

    public void addTracked(Tracked tracked) {
        trackedItems.put(tracked, 0);
    }

    public void removeTracked(Tracked tracked) {
        trackedItems.remove(tracked);
    }

    public Tracked getTrackedById(int id) {
        Optional<Tracked> trackedOptional = trackedItems.keySet().stream().
                filter(item -> item.getId() == id).findFirst();
        if (!trackedOptional.isPresent()) {
            throw new RuntimeException();
        }
        return trackedOptional.get();
    }

    public Set<Tracked> getAllTracked() {
        return trackedItems.keySet();
    }

    public void setSpentTimeOnTracked(Tracked tracked, Integer spentTime) {
        trackedItems.put(tracked, spentTime);
    }

    public Integer getSpentTimeOnTracked(Tracked tracked) {
        return trackedItems.get(tracked);
    }
}
