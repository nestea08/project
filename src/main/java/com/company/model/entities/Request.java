package com.company.model.entities;

import com.company.model.entities.interfaces.TrackedItem;
import com.company.model.entities.interfaces.Tracker;

public class Request {
    private int id;
    private Tracker tracker;
    private Activity activity;
    private RequestType type;

    public enum RequestType{
        ADD, REMOVE
    }

    public Request(Tracker tracker, Activity activity, RequestType type) {
        this.tracker = tracker;
        this.activity = activity;
        this.type = type;
    }

    public Request(int id, Tracker tracker, Activity activity, RequestType type) {
        this(tracker, activity, type);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Tracker getTracker() {
        return tracker;
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
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return tracker.equals(request.getTracker()) &&
                activity.equals(request.activity) &&
                type == request.type;
    }

    @Override
    public int hashCode() {
        return ((tracker.hashCode() * 31) + activity.hashCode() * 31) + type.hashCode();
    }
}
