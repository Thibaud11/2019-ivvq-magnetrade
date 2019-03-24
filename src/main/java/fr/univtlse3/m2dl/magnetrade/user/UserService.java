package fr.univtlse3.m2dl.magnetrade.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null");

        } else {
            return userRepository.save(user);
        }
    }

}
