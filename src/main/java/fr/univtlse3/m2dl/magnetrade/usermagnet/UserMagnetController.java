package fr.univtlse3.m2dl.magnetrade.usermagnet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userMagnet")
public class UserMagnetController {

    private UserMagnetService userMagnetService;

    @Autowired
    public UserMagnetController(UserMagnetService userMagnetService) {
        this.userMagnetService = userMagnetService;
    }

    public UserMagnetController() {
    }

    public void setUserMagnetService(UserMagnetService userMagnetService) {
        this.userMagnetService = userMagnetService;
    }

    public UserMagnetService getUserMagnetService() {
        return userMagnetService;
    }

    /**
     * Method to create a usermagnet in the back-end
     *
     * @param userMagnet the usermagnet to create
     */
    @PostMapping({"/save","/edit"})
    public UserMagnet createUserMagnet(@RequestBody UserMagnet userMagnet) {
        return userMagnetService.saveUserMagnet(userMagnet);
    }

    @GetMapping("read/{userMagnetId}")
    public ResponseEntity<UserMagnet> findUserMagnetById(@PathVariable("userMagnetId") long userMagnetId){
        UserMagnet m = userMagnetService.findUserMagnetById(userMagnetId);
        if(m == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(m, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserMagnet(@PathVariable long id){
        userMagnetService.deleteUserMagnet(id);

    }

}