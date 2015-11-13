package ru.romster.fs.message;

/**
 * Created by r0m5t3r on 11/12/15.
 */
public class BaseMessage {

    private MessageType type;

    public BaseMessage() {
    }

    public BaseMessage(MessageType type) {
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
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
