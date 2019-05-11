package fr.univtlse3.m2dl.magnetrade.usermagnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMagnetService {

    private UserMagnetRepository userMagnetRepository;

    @Autowired
    public void setUserMagnetRepository(UserMagnetRepository userMagnetRepository) {
        this.userMagnetRepository = userMagnetRepository;
    }

    public UserMagnetRepository getUserMagnetRepository() {
        return userMagnetRepository;
    }

    public UserMagnet saveUserMagnet(UserMagnet userMagnet) {
        if (userMagnet == null) {
            throw new IllegalArgumentException("UserMagnet can not be null");

        } else {
            return userMagnetRepository.save(userMagnet);
        }
    }

    public void deleteUserMagnet(long idUserMagnet){
        userMagnetRepository.deleteById(idUserMagnet);
    }

    public UserMagnet findUserMagnetById(long idUserMagnet){
        if(userMagnetRepository.findById(idUserMagnet).isPresent()){
            return userMagnetRepository.findById(idUserMagnet).get();
        }else{
            return null;
        }
    }

}
