package com.company.model.entities;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TrackerUser extends User {
    private Map<Activity, Integer> trackingActivities;

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
        trackingActivities = new HashMap<>();
    }

    public void addActivity(Activity activity) {
        trackingActivities.put(activity, 0);
    }

    public void removeActivity(Activity activity) {
        trackingActivities.remove(activity);
    }

    public Activity getActivity(String title) {
        Optional<Activity> activityOptional = trackingActivities.keySet().stream().
                filter(activity -> activity.getTitle().equals(title)).findFirst();
        if (!activityOptional.isPresent()) {
            throw new RuntimeException();
        }
        return activityOptional.get();
    }

    public void putSpentTimeOnActivity(Activity activity, Integer spentTime) {
        trackingActivities.put(activity, spentTime);
    }

    public Integer getSpentTimeOnActivity(Activity activity) {
        return trackingActivities.get(activity);
    }
}
