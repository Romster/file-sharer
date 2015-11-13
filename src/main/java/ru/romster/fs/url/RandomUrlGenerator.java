package ru.romster.fs.url;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by r0m5t3r on 11/12/15.
 */
@Service
public class RandomUrlGenerator {

    private static final String ADJ_FILE_NAME = "url/adjectives";
    private static final String NOUN_FILE_NAME = "url/nouns";

    private String[] adj;
    private String[] noun;
    private Random random;

    private ConcurrentHashMap<String, LocalDateTime> existedUrls = new ConcurrentHashMap<>();

    @PostConstruct
    void init() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        Path aPath = Paths.get(classLoader.getResource(ADJ_FILE_NAME).getFile());
        adj = Files.readAllLines(aPath).toArray(new String[0]);
        Path nPath = Paths.get(classLoader.getResource(NOUN_FILE_NAME).getFile());
        noun = Files.readAllLines(nPath).toArray(new String[0]);
        random = new Random();
    }

    public String creteNewUrl() {
        String url = null;
        do {
            url = randomUrl();
        } while (existedUrls.put(url, LocalDateTime.now()) != null);
        return url;
    }

    public boolean urlExists(String url) {
        return existedUrls.containsKey(url);
    }


    private String randomUrl() {
        int a = random.nextInt(adj.length);
        int n = random.nextInt(noun.length);
        return adj[a] + "_" + noun[n];
    }


}
