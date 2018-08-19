package com.company.model.entities;

import com.company.model.entities.interfaces.Tracked;
import com.company.model.entities.interfaces.Tracker;

public class Request {
    private int id;
    private Tracker tracker;
    private Tracked tracked;
    private RequestType type;

    public enum RequestType{
        ADD, REMOVE
    }

    public Request(Tracker tracker, Tracked tracked, RequestType type) {
        this.tracker = tracker;
        this.tracked = tracked;
        this.type = type;
    }

    public Request(int id, Tracker tracker, Tracked tracked, RequestType type) {
        this(tracker, tracked, type);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public Tracked getTracked() {
        return tracked;
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
                tracked.equals(request.tracked) &&
                type == request.type;
    }

    @Override
    public int hashCode() {
        return ((tracker.hashCode() * 31) + tracked.hashCode() * 31) + type.hashCode();
    }
}
