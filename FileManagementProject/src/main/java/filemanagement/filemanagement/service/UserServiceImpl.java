package filemanagement.filemanagement.service;

import filemanagement.filemanagement.domain.User;
import filemanagement.filemanagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    public UserServiceImpl(UserRepository userRepository){
        this.repository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {

        Optional<User> op =  repository.findByEmail(email);
        if(op.isPresent()){
            return op.get();
        }
        else{
            return null;
        }
    }

}
