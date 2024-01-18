package filemanagement.filemanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class SharedFileInGroup {
    @Id @GeneratedValue
    @Column(name = "shared_file_in_group_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
