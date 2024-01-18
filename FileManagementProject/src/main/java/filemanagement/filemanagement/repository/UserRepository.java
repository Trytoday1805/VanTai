package filemanagement.filemanagement.repository;

import filemanagement.filemanagement.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findById(Long id){
        return em.find(User.class, id);
    }


    public Optional<User> findByEmail(String email){

        List<User> users =  em.createQuery("SELECT u FROM User u WHERE u.email= :email", User.class).setParameter("email",email).getResultList();
        return users.stream().findAny();
    }
}
