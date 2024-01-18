package filemanagement.filemanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class File {
    @Id @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    private String fileName;

    private Long fileSize;

    private String path;

    private String description;

    public File(String fileName, Long fileSize, String path, String description) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.path = path;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "file",cascade = CascadeType.ALL)
    private List<SharedFileUser> sharedFileUserList = new ArrayList<>();

    @OneToMany(mappedBy = "file",cascade = CascadeType.ALL)
    private List<SavedFile> savedFiles = new ArrayList<>();

    @OneToMany(mappedBy = "file",cascade = CascadeType.ALL)
    private List<SharedFileInGroup> sharedFileInGroupList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FileStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void setOwner(User user) {
        this.user = user;
        user.getFiles().put(this.getId(),this);
    }
}
