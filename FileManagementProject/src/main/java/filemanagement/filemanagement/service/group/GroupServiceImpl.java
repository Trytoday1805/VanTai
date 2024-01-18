package filemanagement.filemanagement.service.group;

import filemanagement.filemanagement.domain.Group;
import filemanagement.filemanagement.domain.GroupUser;
import filemanagement.filemanagement.domain.User;
import filemanagement.filemanagement.repository.GroupRepository;
import filemanagement.filemanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService{
    private GroupRepository groupRepository;

//    private UserRepository userRepository;


    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void createAGroup(String groupName, User user) {
        Group newGroup = new Group();
        newGroup.setGroupName(groupName);

        GroupUser groupUser = new GroupUser();
        groupUser.setGroupUser(newGroup);
        groupUser.setUser(user);


        groupRepository.saveGroup(groupUser);
    }

    @Override
    public List<Group> getGroupsByUserId(Long userId) {
        return groupRepository.getGroupsByUserId(userId);
    }

    @Override
    public Group getGroupById(Long groupId) {
        Group group= groupRepository.getById(groupId);
        return group;
    }


}
