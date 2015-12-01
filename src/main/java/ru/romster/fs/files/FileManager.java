package ru.romster.fs.files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.romster.fs.common.SFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by r0m5t3r on 11/8/15.
 */
@Service
public class FileManager {

    @Value("${fs.files.root}")
    String rootDirectory;

    @PostConstruct
    void init() {
        System.out.println("Init!");
    }

    public Path getSessionDirectory(String sessionId) throws IOException {
        Path p = directoryPath(sessionId);
        if (!Files.exists(p)) {
            return Files.createDirectories(p);
        } else {
            if (Files.isDirectory(p))
                return p;
            else {
                throw new IllegalStateException(p.toAbsolutePath().toUri().toString() + "is not directory");
            }
        }
    }

    public void saveFile(String sessionId, String fileName, byte[] content) throws IOException {
        Path root = getSessionDirectory(sessionId);
        Path child = root.resolve(fileName);
        if (Files.exists(child)) {
            Files.delete(child);
        }
        Files.createFile(child);
        Files.write(child, content);
    }

    public List<SFile> getSharedFiles(String sessionId) throws IOException {
        Path p = getSessionDirectory(sessionId);
        List<SFile> files = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(p)) {
            for (Path entry : stream) {
                String fileName = entry.getFileName().toString();
                String url = "/download/" + sessionId + "/" + fileName;
                SFile sf = new SFile(fileName, url);
                files.add(sf);
            }
        }
        return files;
    }

    private Path directoryPath(String sessionId) {
        String fullPath = rootDirectory + "/" + sessionId;
        return Paths.get(fullPath);
    }



}
