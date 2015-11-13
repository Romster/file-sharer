package ru.romster.fs.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.romster.fs.message.BaseMessage;
import ru.romster.fs.message.Message;

import java.util.UUID;

/**
 * Created by r0m5t3r on 11/12/15.
 */
@Controller
public class WebSocketController {


    @MessageMapping("/start/{sId}")
    @SendTo("/topic/{sId}")
    public Message beginSharing(@DestinationVariable("sId") String sId, BaseMessage message) {
        return new Message(Message.ActionType.REDIRECT);
    }
//    @MessageMapping("/start")
//    public OutputMessage beginSharing() {
//        return new OutputMessage(OutputMessage.ActionType.REDIRECT, registerNewSharingSession());
//    }

    private String registerNewSharingSession() {
        return UUID.randomUUID().toString();
    }

}
