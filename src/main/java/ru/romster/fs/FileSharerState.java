package ru.romster.fs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.romster.fs.common.*;
import ru.romster.fs.url.IdGenerator;
import ru.romster.fs.url.ReadableIdGenerator;
import ru.romster.fs.url.UuidIdGenerator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * Created by r0m5t3r on 11/29/15.
 */
@Component
public class FileSharerState {

    Logger logger = Logger.getLogger(FileSharerState.class.getName());


    @Autowired
    ReadableIdGenerator readableUrlGenerator;
    @Autowired
    UuidIdGenerator uuidUrlGenerator;


    private ConcurrentHashMap<String, FSSessionMeta> startRoomState = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, FSSessionMeta> shareRoomState = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, String> userWebSocketSessions = new ConcurrentHashMap<>();

    /**
     * @param topicDestination
     * @param userSessionId
     * @return users in session count
     */
    public int registerNewUser(TopicDestination topicDestination, String userSessionId) {
        logger.info("Register user with session id " + userSessionId);
        userWebSocketSessions.put(userSessionId, topicDestination.toStompTopic());
        return processAction(topicDestination, FSSessionMeta::addUser);
    }

    /**
     * @param topicDestination
     * @param userSessionId
     * @return users in session count
     */
    public int unregisterUser(TopicDestination topicDestination, String userSessionId) {
        logger.info("Unregister user with session id " + userSessionId);
        userWebSocketSessions.remove(userSessionId);
        return processAction(topicDestination, FSSessionMeta::removeUser);
    }

    public boolean isSessionExist(FSRoom type, String sessionId) {
        return getRoomState(type).containsKey(sessionId);
    }


    public String getUserStompTopic(String userSessionId) {
        return userWebSocketSessions.get(userSessionId);
    }

    /**
     * @param type
     * @return id of new sharing session
     */
    public String createNewSession(FSRoom type) {
        String sId = null;
        FSSessionMeta meta = null;
        IdGenerator generator = type == FSRoom.START ? readableUrlGenerator : uuidUrlGenerator;
        ConcurrentHashMap<String, FSSessionMeta> roomState = getRoomState(type);
        do {
            sId = generator.getId();
            if (!roomState.containsKey(sId)) {
                meta = new FSSessionMeta(sId);
            }
        } while (meta == null || roomState.putIfAbsent(sId, meta) != null);
        return sId;
    }

    /**
     * @param type
     * @param sessionId
     * @return is session still alive
     */
    public boolean makeOldSession(FSRoom type, String sessionId) {
        FSSessionMeta meta = getRoomState(type).get(sessionId);
        if (meta == null) {
            throw new IllegalStateException("Session with id " + sessionId + "does not exist");
        }
        return meta.makeOld() != FileSharerSessionState.DEAD;

    }


    private ConcurrentHashMap<String, FSSessionMeta> getRoomState(FSRoom type) {
        switch (type) {
            case SHARE:
                return shareRoomState;
            case START:
                return startRoomState;
            default:
                throw new IllegalArgumentException("Unknown type:" + type);
        }
    }


    /**
     * @param topicDestination
     * @param action
     * @return
     */
    private int processAction(TopicDestination topicDestination,
                              Function<FSSessionMeta, Integer> action) {
        if (topicDestination.getPlace().equals(StringConstants.TOPIC_START)) {
            FSSessionMeta newMeta = new FSSessionMeta(topicDestination.getId());
            FSSessionMeta oldMeta = startRoomState.putIfAbsent(topicDestination.getId(), newMeta);
            if (oldMeta != null) {
                return action.apply(oldMeta);
            } else {
                return action.apply(newMeta);
            }
        }
        return -1;
    }
}
