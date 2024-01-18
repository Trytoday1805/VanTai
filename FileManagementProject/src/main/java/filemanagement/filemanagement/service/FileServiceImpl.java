package filemanagement.filemanagement.service;


import filemanagement.filemanagement.domain.File;
import filemanagement.filemanagement.domain.SharedFileUser;
import filemanagement.filemanagement.domain.User;
import filemanagement.filemanagement.repository.FileRepository;
import filemanagement.filemanagement.utils.FileUtil;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Path;

@Service
@Transactional
public class FileServiceImpl implements FileService{

    private final FileRepository fileRepository;
    private final UserService userService;
    @Autowired
    public FileServiceImpl(FileRepository fileRepository, UserService userService) {
        this.fileRepository = fileRepository;
        this.userService = userService;
    }


    @Override
    /*
    + lay user
    + trong thu muc co ten la gia tri cua userId, upload file vao do.
    + luu duong dan file vao csdl va thiet lap moi quan he giua user va file
     */
    public void uploadFile(MultipartFile fileToUpload, Long userId, String filename, String description) throws IOException {
        User user = userService.getUserById(userId);
        Path filePath = FileUtil.uploadFile(fileToUpload,userId,filename);
//        fileRepository.saveFile(fileToUpload,userId);
        String extension = FilenameUtils.getExtension(fileToUpload.getOriginalFilename());
        File file = new File(filename + "." + extension, fileToUpload.getSize(),filePath.toString(), description);
//        System.out.println(file);
//        user.setFirstName("11111111111111111111111");
//        user.addFileToUser(file);
        fileRepository.saveFile(file, user);

    }

    @Override
    public File getFileById(Long id) {
        File file = fileRepository.getById(id);
        return file;
    }

    @Override
    public void delete(File file) {
        fileRepository.remove(file);
        FileUtil.deleteFile(file.getPath());
    }

    @Override
    public void shareFileWithUser(File file, User user) {
        SharedFileUser share = new SharedFileUser();
        share.setFile(file);
        share.setUser(user);

        fileRepository.shareFileWithUser(share);
    }
}
