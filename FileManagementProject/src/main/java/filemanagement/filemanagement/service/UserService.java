package filemanagement.filemanagement.service;

import filemanagement.filemanagement.domain.User;

public interface UserService {
    void saveUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);

}
