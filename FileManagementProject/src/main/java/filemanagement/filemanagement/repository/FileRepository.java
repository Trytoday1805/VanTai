package filemanagement.filemanagement.repository;


import filemanagement.filemanagement.domain.File;
import filemanagement.filemanagement.domain.SharedFileUser;
import filemanagement.filemanagement.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;



@Repository
public class FileRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveFile(File fileToUpload, User user){
//        entityManager.persist();
        entityManager.persist(fileToUpload);
        fileToUpload.setOwner(user);
    }

    public File getById(Long fileId){
        File file = entityManager.find(File.class, fileId);

        return file;
    }

    public void remove(File file) {
        entityManager.remove(file);
    }

    public void shareFileWithUser(SharedFileUser share) {
        entityManager.persist(share);
    }
}
