package ru.romster.fs.controller.event;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.romster.fs.common.FSRoom;
import ru.romster.fs.common.TopicDestination;
import ru.romster.fs.message.StartPageResponseMessage;

/**
 * Created by r0m5t3r on 11/29/15.
 */
@Component
public class StompDisconnectListener extends BaseEventLister implements ApplicationListener<SessionDisconnectEvent> {

    @Override
    public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        String userSessionId = stompHeaderAccessor.getSessionId();
        String topic = fileSharerState.getUserStompTopic(userSessionId);
        TopicDestination td = getDestination(topic);
        FSRoom roomType = FSRoom.fromString(td.getPlace());
        if (roomType == FSRoom.START) {
            Integer userCount = fileSharerState.unregisterUser(td, stompHeaderAccessor.getSessionId());
            simpMessagingTemplate.convertAndSend(topic,
                    new StartPageResponseMessage(userCount));
        }
    }
}
