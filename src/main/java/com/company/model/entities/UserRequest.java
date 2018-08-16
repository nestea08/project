package com.company.model.entities;

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
}
