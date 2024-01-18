package filemanagement.filemanagement.service;

import filemanagement.filemanagement.domain.File;
import filemanagement.filemanagement.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    /*
    tra ve duong dan file.
     */
    void uploadFile(MultipartFile fileToUpload, Long userId, String filename, String description) throws IOException;
    File getFileById(Long id);

    void delete(File file);

    void shareFileWithUser(File file, User user);
}
