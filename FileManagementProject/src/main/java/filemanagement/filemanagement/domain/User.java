package filemanagement.filemanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<SharedFileUser> sharedFileUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @MapKey(name = "id")
    private Map<Long,File> files = new HashMap<>();

    @OneToMany(mappedBy = "user")
    private List<SavedFile> savedFies = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<GroupUser> groupUserList;

    public void addFileToUser(File file){
        files.put(file.getId(), file);
        file.setOwner(this);
    }

}
