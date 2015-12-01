package ru.romster.fs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.romster.fs.common.FSRoom;
import ru.romster.fs.FileSharerState;
import ru.romster.fs.message.BaseMessage;
import ru.romster.fs.message.StartPageResponseMessage;
import ru.romster.fs.url.UuidIdGenerator;

import java.util.UUID;

/**
 * Created by r0m5t3r on 11/12/15.
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private FileSharerState fileSharerState;

    @MessageMapping("/start/{sId}")
    public void beginSharing(@DestinationVariable("sId") String sId, BaseMessage message) {
        String shareUrl = FSRoom.SHARE.toString() + "?id=" + fileSharerState.createNewSession(FSRoom.SHARE);
        simpMessagingTemplate.convertAndSend("/" + FSRoom.START.toString() + "/"
                + sId, new StartPageResponseMessage(shareUrl));
    }

}
