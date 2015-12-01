package ru.romster.fs.message;

/**
 * Created by r0m5t3r on 11/12/15.
 */
public class BaseMessage {

    private String type;

    public BaseMessage() {
    }

    public BaseMessage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected enum MessageType {
        BEGIN,
        NEW_FILE,
        REMOVE_FILE,
        USER_NEW,
        USER_LEFT
    }
}
