package ru.romster.fs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.romster.fs.files.FileManager;
import ru.romster.fs.url.RandomUrlGenerator;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by r0m5t3r on 11/7/15.
 */
@Controller
public class MainController {

    @Autowired
    FileManager fileManager;
    @Autowired
    RandomUrlGenerator urlGenerator;

    @RequestMapping("/start")
    public String loadStartPage(@RequestParam(value = "id", required = false) String id,
                           Model model, HttpSession session) throws IOException {
        if (id == null || !urlGenerator.urlExists(id)) {
            return "redirect:/start?id=" + urlGenerator.creteNewUrl();
        }
        return "start";
    }

    @RequestMapping("share")
    public String loadSharePage(@RequestParam(value = "id", required = true) String id,
                                Model model) throws IOException{
        model.addAttribute("id", id);
        model.addAttribute("sharedFiles", fileManager.getSharedFiles(id));
        return "share";
    }

    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("files") List<MultipartFile> files,
                                   @RequestParam("session_id") String sessionId) throws IOException {
        Path dir = fileManager.getSessionDirectory(sessionId);
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    fileManager.saveFile(sessionId, file.getOriginalFilename(), bytes);
                } catch (Exception e) {
                }
            } else {
            }
        }
        return "redirect:/share?id=" + sessionId;
    }

    @RequestMapping(value = "/download/{folder_id}/{file_name:.*}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("folder_id") String folder,
                                      @PathVariable("file_name") String fileName) throws IOException {
        String fileLocalPath = fileManager.getSessionDirectory(folder)
                .resolve(fileName).toAbsolutePath().toString();
        return new FileSystemResource(fileLocalPath);
    }


    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }
}
