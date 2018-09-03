package com.company.model.entities;

import com.company.model.entities.interfaces.Tracker;

/**
 * Represents a simple request to administrator
 */
public class Request {
    private int id;
    private Tracker tracker;
    private Activity activity;
    private RequestType type;

    /**
     * Describes a type of request
     */
    public enum RequestType{
        ADD, REMOVE
    }

    /**
     *  Constructs a request with default id
     */
    public Request(Tracker tracker, Activity activity, RequestType type) {
        this.tracker = tracker;
        this.activity = activity;
        this.type = type;
    }

    /**
     * Constructs a request with specified id
     * @param id request id from database
     */
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

    /**
     * Compares two objects for equality.
     * Comparison is dependent on activity
     * and tracker equality
     * @return <code>true</code> if the objects are the same;
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return tracker.equals(request.getTracker()) &&
                activity.equals(request.activity) &&
                type == request.type;
    }

    /**
     * Computes a hash code to request
     * Computation is dependent on activity
     * and tracker hash codes
     * @return hash code
     */
    @Override
    public int hashCode() {
        return ((tracker.hashCode() * 31) + activity.hashCode() * 31) + type.hashCode();
    }
}
