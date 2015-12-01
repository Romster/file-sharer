package ru.romster.fs.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.romster.fs.FileSharerState;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by r0m5t3r on 11/12/15.
 */
@Service
public class ReadableIdGenerator implements IdGenerator {

    private static final String ADJ_FILE_NAME = "url/adjectives";
    private static final String NOUN_FILE_NAME = "url/nouns";

    @Autowired
    FileSharerState fileSharerState;

    private String[] adj;
    private String[] noun;
    private Random random;


    @PostConstruct
    void init() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        Path aPath = Paths.get(classLoader.getResource(ADJ_FILE_NAME).getFile());
        adj = Files.readAllLines(aPath).toArray(new String[0]);
        Path nPath = Paths.get(classLoader.getResource(NOUN_FILE_NAME).getFile());
        noun = Files.readAllLines(nPath).toArray(new String[0]);
        random = new Random();
    }


    @Override
    public String getId() {
        int a = random.nextInt(adj.length);
        int n = random.nextInt(noun.length);
        return adj[a] + "_" + noun[n];
    }


}
