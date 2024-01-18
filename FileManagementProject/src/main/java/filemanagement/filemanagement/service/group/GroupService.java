package filemanagement.filemanagement.service.group;

import filemanagement.filemanagement.domain.Group;
import filemanagement.filemanagement.domain.User;

import java.util.List;

public interface GroupService {

    void createAGroup(String groupName, User user);
    List<Group> getGroupsByUserId(Long userId);

    Group getGroupById(Long groupId);
}
