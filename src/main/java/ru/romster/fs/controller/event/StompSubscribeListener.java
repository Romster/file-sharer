package ru.romster.fs.controller.event;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import ru.romster.fs.common.FSRoom;
import ru.romster.fs.common.TopicDestination;
import ru.romster.fs.message.StartPageResponseMessage;

/**
 * Created by r0m5t3r on 11/13/15.
 */
@Component
public class StompSubscribeListener extends BaseEventLister implements ApplicationListener<SessionSubscribeEvent> {


    @Override
    public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
        String topic = stompHeaderAccessor.getDestination();
        TopicDestination td = getDestination(topic);
        FSRoom roomType = FSRoom.fromString(td.getPlace());
        if (roomType == FSRoom.START) {
            Integer userCount = fileSharerState.registerNewUser(td, stompHeaderAccessor.getSessionId());
            simpMessagingTemplate.convertAndSend(topic, new StartPageResponseMessage(userCount));
        }
    }
}
