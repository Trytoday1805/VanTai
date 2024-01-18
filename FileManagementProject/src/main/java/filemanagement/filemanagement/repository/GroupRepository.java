package filemanagement.filemanagement.repository;

import filemanagement.filemanagement.domain.Group;
import filemanagement.filemanagement.domain.GroupUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupRepository {
    @PersistenceContext
    private EntityManager em;

    public void saveGroup(GroupUser group){
        em.persist(group);
    }

    public List<Group> getGroupsByUserId(Long userId){
        List<Group> groups = em.createQuery("SELECT g from GroupUser g where g.user.id = :userId", Group.class).setParameter("userId", userId).getResultList();
        return groups;
    }

    public Group getById(Long groupId){
        Group group = em.find(Group.class, groupId);
        return group;
    }
}
