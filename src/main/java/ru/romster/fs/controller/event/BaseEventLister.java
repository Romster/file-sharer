package ru.romster.fs.controller.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import ru.romster.fs.FileSharerState;
import ru.romster.fs.common.TopicDestination;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by r0m5t3r on 11/29/15.
 */
public abstract class BaseEventLister {

    protected Pattern pattern = Pattern.compile("\\/(.+)\\/(.+)");

    @Autowired
    protected SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    protected FileSharerState fileSharerState;


    protected TopicDestination getDestination(String destinationUrl) {
        Matcher m = pattern.matcher(destinationUrl);
        if (m.matches()) {
            return new TopicDestination(m.group(1), m.group(2));
        } else {
            throw new IllegalArgumentException("Illegal destinationUrl:" + destinationUrl);
        }
    }


}
