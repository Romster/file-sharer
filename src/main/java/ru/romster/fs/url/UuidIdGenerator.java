package ru.romster.fs.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.romster.fs.FileSharerState;

import java.util.UUID;

/**
 * Created by r0m5t3r on 12/1/15.
 */
@Service
public class UuidIdGenerator implements IdGenerator {
    @Autowired
    FileSharerState fileSharerState;


    @Override
    public String getId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
