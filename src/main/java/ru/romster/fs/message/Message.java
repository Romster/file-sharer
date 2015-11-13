package ru.romster.fs.message;

/**
 * Created by r0m5t3r on 11/12/15.
 */
public class Message {
    private String value;
    private ActionType action;


    public Message() {
    }

    public Message(ActionType action) {
        this.action = action;
    }

    public Message(ActionType action, String value) {
        this.value = value;
        this.action = action;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public enum ActionType {
        REDIRECT,
        FILE_UPLOAD,
        NEW_USER,
        REMOVE_USER;
    }
}
