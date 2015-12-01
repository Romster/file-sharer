package ru.romster.fs.common;

/**
 * Created by r0m5t3r on 11/29/15.
 */
public class TopicDestination {
    private String place;
    private String id;


    public String getPlace() {
        return place;
    }

    public String getId() {
        return id;
    }

    public TopicDestination(String place, String id) {
        this.place = place;
        this.id = id;
    }

    public String toStompTopic() {
        return "/" + place + "/" + id;
    }

}
