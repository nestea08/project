package com.company.model.entities;

/**
 * Represents a simple human activity.
 */
public class Activity {
    private int id;
    private String title;
    private String description;

    /**
     * Constructs an activity with specified title and description.
     * Id gets a default value.
     */
    public Activity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Constructs an activity with specified id, title and description.
     * @param id activity id from database
     */
    public Activity(int id, String title, String description) {
        this(title, description);
        this.id = id;
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

    /**
     * Compares two objects for equality.
     * Comparison is made by title and description.
     * @return <code>true</code> if the objects are the same;
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return title.equals(activity.title) &&
                description.equals(activity.description);
    }

    /**
     * Computes a hash code to Activity objects
     * @return object's hash code
     */
    @Override
    public int hashCode() {
        return (title.hashCode() * 31) + description.hashCode();
    }
}
