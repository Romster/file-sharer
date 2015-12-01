package ru.romster.fs.common;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by r0m5t3r on 11/29/15.
 */
public class FSSessionMeta {
    private String sessionId;
    private LocalDateTime createTime;
    private AtomicInteger usersOnline;
    private FileSharerSessionState sessionState;

    public FSSessionMeta(String id) {
        this.createTime = LocalDateTime.now();
        this.usersOnline = new AtomicInteger(0);
        this.sessionId = id;
        this.sessionState = FileSharerSessionState.YOUNG;
    }

    public int getUsersOnline() {
        return usersOnline.get();
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public int addUser() {
        return usersOnline.incrementAndGet();
    }

    public int removeUser() {
        return usersOnline.decrementAndGet();
    }

    public FileSharerSessionState getSessionState() {
        return sessionState;
    }

    /**
     *
     * @return new state
     */
    public FileSharerSessionState makeOld() {
        sessionState = sessionState == FileSharerSessionState.YOUNG
                ? FileSharerSessionState.OLD
                : FileSharerSessionState.DEAD;
        return sessionState;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FSSessionMeta that = (FSSessionMeta) o;

        return sessionId.equals(that.sessionId);

    }

    @Override
    public int hashCode() {
        return sessionId.hashCode();
    }

    @Override
    public String toString() {
        return "ShareSessionMeta{" +
                "sessionId='" + sessionId + '\'' +
                ", createTime=" + createTime +
                ", usersOnline=" + usersOnline +
                ", sessionState=" + sessionState +
                '}';
    }
}
