package ru.romster.fs.common;

/**
 * Created by r0m5t3r on 11/29/15.
 */
public enum FSRoom {
    START,
    SHARE;

    public static final FSRoom fromString(String type) {
        return FSRoom.valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return super.name().toLowerCase();
    }
}
