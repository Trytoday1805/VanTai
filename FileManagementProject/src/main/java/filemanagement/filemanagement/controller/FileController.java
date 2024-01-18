package filemanagement.filemanagement.controller;

import filemanagement.filemanagement.domain.File;
import filemanagement.filemanagement.domain.User;
import filemanagement.filemanagement.service.FileService;
import filemanagement.filemanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class FileController {

    private final FileService fileService;

    private final UserService userService;
    @Autowired
    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, @RequestParam("userId") Long userId,
                             @RequestParam("filename") String filename, @RequestParam("description") String description) throws IOException {

        // File khong duoc vuot qua 40mb
        // File ten khong duoc dai qua 40 ky tu

        fileService.uploadFile(file, userId, filename,description);

        return "redirect:/home";
    }

    @RequestMapping("/download/{fileId}")

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileId") Long fileId) throws FileNotFoundException {

        File file = fileService.getFileById(fileId);

        Path path = Paths.get(file.getPath());
        if (Files.exists(path)) {

            response.addHeader("Content-Disposition", "attachment; filename=" + file.getFileName());
            try {
                Files.copy(path, response.getOutputStream());
                response.getOutputStream().flush();
                ;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable("fileId") Long fileId){
        File file = fileService.getFileById(fileId);
        fileService.delete(file);
        return "redirect:/home";
    }

    @PostMapping("/share")
    public String shareFileInUser(@RequestParam("sharedFileId") Long fileId, @RequestParam("email") String email){
        File file = fileService.getFileById(fileId);
        User user = userService.getUserByEmail(email);

        fileService.shareFileWithUser(file,user);

        return "redirect:/home";
    }

    @GetMapping("/sharedFiles")
    public String filesShared(Principal principal, Model model){
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("userId", user.getId());
        model.addAttribute("files", user.getSharedFileUsers());
        return "sharedFiles";
    }
    @GetMapping("/save")
    public String save(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);

        // Add necessary attributes to the model
        model.addAttribute("userId", user.getId());
        model.addAttribute("files", user.getFiles().values());

        // Return the Thymeleaf template name (without ".html" extension)
        return "save";
    }
}
