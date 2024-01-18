package filemanagement.filemanagement.controller;

import filemanagement.filemanagement.domain.Group;
import filemanagement.filemanagement.domain.User;
import filemanagement.filemanagement.service.UserService;
import filemanagement.filemanagement.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @GetMapping("/groups")
    public String createGroupForm(Model model, Principal principal){
        // lay danh sach nhom
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        List<Group> groups = groupService.getGroupsByUserId(user.getId());

        // hien thi danh sach
        model.addAttribute("groups", groups);
        model.addAttribute("group", new Group());
        return "group";
    }

    @PostMapping("/groups/new")
    public String createGroup(@ModelAttribute("group") Group group, Principal principal) {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        groupService.createAGroup(group.getGroupName(), user);
        return "redirect:/groups";
    }

    @GetMapping("/groups/{groupId}")
    public String group(@PathVariable("groupId") Long groupId, Model model){
        Group group = groupService.getGroupById(groupId);
        model.addAttribute("files", group.getSharedFileInGroupList());
        return "FileGroup";
    }
}
