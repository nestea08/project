package com.company.model.entities;

import java.util.Objects;

public class UserRequest {
    private int id;
    private TrackerUser user;
    private Activity activity;
    private RequestType type;

    public enum RequestType{
        ADD, REMOVE
    }

    public UserRequest(TrackerUser user, Activity activity, RequestType type) {
        this.user = user;
        this.activity = activity;
        this.type = type;
    }

    public UserRequest(int id, TrackerUser user, Activity activity, RequestType type) {
        this(user, activity, type);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TrackerUser getUser() {
        return user;
    }

    public Activity getActivity() {
        return activity;
    }

    public RequestType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRequest)) return false;
        UserRequest request = (UserRequest) o;
        return user.equals(request.getUser()) &&
                activity.equals(request.activity) &&
                type == request.type;
    }

    @Override
    public int hashCode() {
        return ((user.hashCode() * 31) + activity.hashCode() * 31) + type.hashCode();
    }
}
