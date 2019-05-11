package fr.univtlse3.m2dl.magnetrade.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Setter for property 'userRepository'.
     * @param userRepository value for 'userRepository'
     */
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Getter for property 'userRepository'.
     * @return value
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * Method to create or update a user.
     * @param user user to create/update
     * @return the user created/updated
     */
    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Method to delete a user.
     * @param idUser id of the user to delete
     */
    public void deleteUser(long idUser) {
        userRepository.deleteById(idUser);
    }

    /**
     * Method to find a user.
     * @param idUser id of the user to find.
     * @return user find
     */
    public Optional<User> findUserById(long idUser) {
        return userRepository.findById(idUser);
    }

    /**
     * Method to find all users.
     * @return all users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
